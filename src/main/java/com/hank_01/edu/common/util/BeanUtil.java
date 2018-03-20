package com.hank_01.edu.common.util;

import org.apache.commons.beanutils.BeanUtils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Bean工具类
 * 
 */
public final class BeanUtil {

	/**
	 * 属性描述解析缓存
	 */
	private static final Map<Class<?>, WeakReference<Map<String, PropertyDescriptor>>> classPropDescCache = Collections
			.synchronizedMap(new WeakHashMap<Class<?>, WeakReference<Map<String, PropertyDescriptor>>>());

	/**
	 * 属性定义解析缓存
	 */
	private static final Map<Class<?>, WeakReference<Map<String, Field>>> classFieldCache = Collections
			.synchronizedMap(new WeakHashMap<Class<?>, WeakReference<Map<String, Field>>>());

	/**
	 * 将source对象的属性值拷贝至target对象的同名属性中
	 * 
	 * @param source
	 * @param target
	 * @param ignoredProps
	 * @return
	 */
	public static <T> T copyProperties(Object source, T target, String... ignoredProps) {
		return copyProperties(CopyPropModes.STANDARD, source, target, ignoredProps);
	}

	/**
	 * 将source对象的属性值拷贝至target对象的同名属性中
	 * 
	 * @param mode
	 *            模式: 1-source对象属性值不为null，2-target对象属性值未设置，其他-所有均拷贝
	 * @param source
	 * @param target
	 * @param ignoredProps
	 * @return
	 * @see me.andpay.ti.util.BeanUtil.CopyPropModes
	 */
	public static <T> T copyProperties(int mode, Object source, T target, String... ignoredProps) {
		if (source == null || target == null) {
			return target;
		}

		Map<String, PropertyDescriptor> srcPropDescMap = getPropDescMap(source.getClass());
		Map<String, PropertyDescriptor> targetPropDescMap = getPropDescMap(target.getClass());
		for (Map.Entry<String, PropertyDescriptor> srcPropDescEntry : srcPropDescMap.entrySet()) {
			if (ArrayUtil.contains(ignoredProps, srcPropDescEntry.getKey())) {
				continue;
			}

			Method readMethod = srcPropDescEntry.getValue().getReadMethod();
			if (readMethod == null) {
				continue;
			}

			PropertyDescriptor targetPropDesc = targetPropDescMap.get(srcPropDescEntry.getKey());
			if (targetPropDesc == null || targetPropDesc.getWriteMethod() == null) {
				continue;
			}

			try {
				boolean doSet = true;
				Object srcValue = readMethod.invoke(source);

				switch (mode) {
				case CopyPropModes.SOURCE_VALUE_NOT_NULL:
					doSet = (srcValue != null);
					break;

				case CopyPropModes.TARGET_VALUE_NOT_SET:
					Method targetReadMethod = targetPropDesc.getReadMethod();
					if (targetReadMethod != null) {
						Object targetValue = targetReadMethod.invoke(target);
						doSet = (targetValue == null
								|| targetValue.equals(ObjectUtil.defaultValue(targetPropDesc.getPropertyType())));
					}
					break;
				}

				if (doSet) {
					targetPropDesc.getWriteMethod().invoke(target, srcValue);
				}
			} catch (Exception e) {
				throw new RuntimeException("Write property value error, propName=" + srcPropDescEntry.getKey(), e);
			}
		}

		return target;
	}

	/**
	 * 获得class的propName对应的属性Get方法
	 * 
	 * @param clazz
	 * @param propName
	 * @return
	 */
	public static Method getReadMethod(Class<?> clazz, String propName) {
		if (clazz == null || propName == null) {
			return null;
		}

		PropertyDescriptor propDesc = getPropDescMap(clazz).get(propName);
		return (propDesc != null ? propDesc.getReadMethod() : null);
	}

	/**
	 * 获取属性描述集(class属性默认忽略)
	 * 
	 * @param clazz
	 * @param ignoredProps
	 *            忽略的属性名
	 * @return
	 */
	public static Map<String, PropertyDescriptor> getPropertyDescriptors(Class<?> clazz, String... ignoredProps) {
		if (clazz == null) {
			return null;
		}

		Map<String, PropertyDescriptor> propDescMap = new LinkedHashMap<String, PropertyDescriptor>(
				getPropDescMap(clazz));
		if (ArrayUtil.isNotEmpty(ignoredProps)) {
			for (String ignoredProp : ignoredProps) {
				propDescMap.remove(ignoredProp);
			}
		}

		return propDescMap;
	}

	/**
	 * 获取属性描述(class属性默认忽略)
	 * 
	 * @param clazz
	 * @param propName
	 * @return
	 */
	public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String propName) {
		if (clazz == null || propName == null) {
			return null;
		}

		return getPropDescMap(clazz).get(propName);
	}

	/**
	 * 获取属性名称集
	 * 
	 * @param clazz
	 * @param ignoredProps
	 *            忽略的字段名
	 * @return
	 */
	public static List<String> getPropertyNames(Class<?> clazz, String... ignoredProps) {
		if (clazz == null) {
			return null;
		}

		Map<String, PropertyDescriptor> propDescMap = getPropDescMap(clazz);

		List<String> propNames = new ArrayList<String>(propDescMap.size());
		for (Map.Entry<String, PropertyDescriptor> entry : propDescMap.entrySet()) {
			if (ArrayUtil.contains(ignoredProps, entry.getKey()) == false) {
				propNames.add(entry.getKey());
			}
		}

		return propNames;
	}

	/**
	 * 获取两个对象指定属性中属性值不同的属性集合，如果两个对象任意一个为null，返回空集合
	 * 
	 * @param obj1
	 * @param obj2
	 * @param propNamesToCompare
	 *            为null或空数组，则比较两对象同名属性；如果指定属性名在两个对象的任意一个中不存在，则被忽略
	 * @return
	 */
	public static List<String> getPropertyNamesWithDiffValue(Object obj1, Object obj2, String... propNamesToCompare) {
		List<String> propsNames = new ArrayList<String>();

		if (obj1 == null || obj2 == null || obj1 == obj2) {
			return propsNames;
		}

		// 打造对象1的属性名与属性表述信息的映射关系
		Map<String, PropertyDescriptor> propDefMap1 = BeanUtil.getPropertyDescriptors(obj1.getClass());

		// 如果对象2与对象1的类型相同，则使用相同的映射关系
		Map<String, PropertyDescriptor> propDefMap2 = propDefMap1;
		if (obj1.getClass() != obj2.getClass()) {
			// 不同则解析
			propDefMap2 = BeanUtil.getPropertyDescriptors(obj2.getClass());
		}

		// 如果未指定属性名，则比较所有属性(选取任何一个对象的属性映射作为参考)
		if (ArrayUtil.isEmpty(propNamesToCompare)) {
			propNamesToCompare = propDefMap1.keySet().toArray(new String[propDefMap1.size()]);
		}

		// 遍历所有属性，比较属性值
		for (String propName : propNamesToCompare) {
			PropertyDescriptor pd1 = propDefMap1.get(propName);
			PropertyDescriptor pd2 = propDefMap2.get(propName);

			if (pd1 == null || pd1.getReadMethod() == null || pd2 == null || pd2.getReadMethod() == null) {
				continue;
			}

			try {
				// 如果不一致则记录
				if (ObjectUtil.isNotEqual(pd1.getReadMethod().invoke(obj1), pd2.getReadMethod().invoke(obj2))) {
					propsNames.add(propName);
				}
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}

		return propsNames;
	}

	/**
	 * 获取Class的属性描述集
	 * 
	 * @param clazz
	 * @return
	 */
	private static Map<String, PropertyDescriptor> getPropDescMap(Class<?> clazz) {
		Map<String, PropertyDescriptor> propDescMap = getPropDescMapFromCache(clazz);
		if (propDescMap != null) {
			return propDescMap;
		}

		propDescMap = new TreeMap<String, PropertyDescriptor>();
		try {
			for (PropertyDescriptor pd : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
				if ("class".equals(pd.getName()) == false) {
					propDescMap.put(pd.getName(), pd);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("Analyse property desc error, class=" + clazz, e);
		}
		classPropDescCache.put(clazz, new WeakReference<Map<String, PropertyDescriptor>>(propDescMap));

		return propDescMap;
	}

	/**
	 * 从缓存中获取属性描述
	 * 
	 * @param clazz
	 * @return
	 */
	private static Map<String, PropertyDescriptor> getPropDescMapFromCache(Class<?> clazz) {
		WeakReference<Map<String, PropertyDescriptor>> propDescRef = classPropDescCache.get(clazz);

		return (propDescRef != null ? propDescRef.get() : null);
	}

	/**
	 * 获取字段定义集，保留子类中与父类同名的字段定义
	 * 
	 * @param clazz
	 * @param ignoredFields
	 *            忽略的字段名
	 * @return
	 */
	public static Map<String, Field> getFields(Class<?> clazz, String... ignoredFields) {
		if (clazz == null) {
			return null;
		}

		Map<String, Field> fieldMap = new LinkedHashMap<String, Field>(getFieldMap(clazz));
		if (ArrayUtil.isNotEmpty(ignoredFields)) {
			for (String ignoredField : ignoredFields) {
				fieldMap.remove(ignoredField);
			}
		}

		return fieldMap;
	}

	/**
	 * 获取Class的字段定义集
	 * 
	 * @param clazz
	 * @return
	 */
	private static Map<String, Field> getFieldMap(Class<?> clazz) {
		Map<String, Field> fieldMap = getFieldMapFromCache(clazz);
		if (fieldMap != null) {
			return fieldMap;
		}

		fieldMap = new TreeMap<String, Field>();

		do {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (fieldMap.containsKey(field.getName()) == false) {
					fieldMap.put(field.getName(), field);
				}
			}
			clazz = clazz.getSuperclass();
		} while (clazz != null);

		classFieldCache.put(clazz, new WeakReference<Map<String, Field>>(fieldMap));

		return fieldMap;
	}

	/**
	 * 获取obj的字段定义
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getField(Object obj, String fieldName) {
		if (obj == null || fieldName == null) {
			return null;
		}

		Class<?> clazz = null;
		if (obj instanceof Class) {
			clazz = ((Class<?>) obj);
		} else {
			clazz = obj.getClass();
		}

		Field f = null;
		try {
			f = clazz.getDeclaredField(fieldName);
		} catch (Exception e) {
		}

		if (f == null) {
			return getField(clazz.getSuperclass(), fieldName);
		}

		return f;
	}

	/**
	 * 从缓存中获取字段定义集
	 * 
	 * @param clazz
	 * @return
	 */
	private static Map<String, Field> getFieldMapFromCache(Class<?> clazz) {
		WeakReference<Map<String, Field>> fieldRef = classFieldCache.get(clazz);

		return (fieldRef != null ? fieldRef.get() : null);
	}

	/**
	 * 获取指定方法对象
	 * 
	 * @param obj
	 * @param methodName
	 * @return
	 */
	public static Method getMethod(Object obj, String methodName) {
		return getMethod(obj, methodName, null);
	}

	/**
	 * 获取指定Method
	 * 
	 * @param obj
	 * @param methodName
	 * @param paraTypes
	 * @return
	 */
	public static Method getMethod(Object obj, String methodName, Class<?>[] paraTypes) {
		if (obj == null || methodName == null) {
			return null;
		}

		Class<?> clazz = null;
		if (obj instanceof Class) {
			clazz = ((Class<?>) obj);
		} else {
			clazz = obj.getClass();
		}

		Method m = null;
		if (paraTypes != null) {
			try {
				m = clazz.getDeclaredMethod(methodName, paraTypes);
			} catch (Exception e) {
			}
		} else {
			for (Method method : clazz.getDeclaredMethods()) {
				if (method.getName().equals(methodName)) {
					m = method; // first method by name
					break;
				}
			}
		}

		if (m == null) {
			return getMethod(clazz.getSuperclass(), methodName, paraTypes);
		}

		return m;
	}

	/**
	 * 获取指定属性值
	 * 
	 * @param obj
	 * @param propName
	 * @return
	 */
	public static Object getPropValue(Object obj, String propName) {
		PropertyDescriptor propDesc = getPropDescMap(obj.getClass()).get(propName);
		if (propDesc != null) {
			try {
				Method readMethod = propDesc.getReadMethod();
				if (readMethod != null) {
					return readMethod.invoke(obj);
				}
			} catch (Exception e) {
				throw new RuntimeException(String.format("Get property value error, name=%s, obj=%s", propName, obj),
						e);
			}
		}

		return null;
	}

	/**
	 * 根据方法和对象获取值
	 * 
	 * @param method
	 * @param o
	 * @return
	 */
	public static Object getValue(Method method, Object o) {
		try {
			return method.invoke(o);
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}
		return null;
	}

	/**
	 * 获取指定属性值，当调用抛出异常时返回null
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static <T> T getFieldValueQuietly(Object obj, String fieldName) {
		try {
			return getFieldValue(obj, fieldName);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 获取指定属性值，当调用抛出异常时返回null
	 * 
	 * @param obj
	 * @param field
	 * @return
	 */
	public static <T> T getFieldValueQuietly(Object obj, Field field) {
		try {
			return getFieldValue(obj, field);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 获取指定属性值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static <T> T getFieldValue(Object obj, String fieldName) {
		if (obj == null || fieldName == null) {
			return null;
		}

		Field f = getField(obj, fieldName);
		if (f == null) {
			throw new RuntimeException("No such field, obj=" + obj + ", fieldName=" + fieldName);
		}

		return getFieldValue(obj, f);
	}

	/**
	 * 获取指定属性值
	 * 
	 * @param obj
	 * @param field
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getFieldValue(Object obj, Field field) {
		if (obj == null || field == null) {
			return null;
		}

		if (field.isAccessible() == false) {
			field.setAccessible(true);
		}

		try {
			return (T) field.get(obj);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e.getMessage(), e.getCause());
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(), e.getCause());
		}
	}

	private BeanUtil() {
	}

	/**
	 * 属性拷贝模式类
	 * 
	 * @author alex
	 */
	public static final class CopyPropModes {
		/**
		 * 拷贝模式 - 标准全部拷贝
		 */
		public static final int STANDARD = 0;

		/**
		 * 拷贝模式 - source对象属性值不为null
		 */
		public static final int SOURCE_VALUE_NOT_NULL = 1;

		/**
		 * 拷贝模式 - target对象属性值未设置(为对应类型的默认值)
		 */
		public static final int TARGET_VALUE_NOT_SET = 2;

		private CopyPropModes() {
		}
	}

	/**
	 * 对象转MAP模式类
	 * 
	 * @author alex
	 */
	public static final class AsMapModes {
		/**
		 * 模式 - 全部属性
		 */
		public static final int STANDARD = 0;

		/**
		 * 模式 - 属性值不为null
		 */
		public static final int PROP_VALUE_NOT_NULL = 1;

		private AsMapModes() {
		}
	}

	/**
	 * 将Bean转换为Map，包含标注指定注解的属性集
	 * 
	 * @param obj
	 * @param annos
	 * @return
	 */
	public static Map<String, Object> asMapWithAnnos(Object obj,
			@SuppressWarnings("unchecked") Class<? extends Annotation>... annos) {
		return asMapWithAnnos(AsMapModes.STANDARD, obj, annos);
	}

	/**
	 * 将Bean转换为Map，包含指定属性集
	 * 
	 * @param mode
	 *            模式: 1-忽略value为null的属性，其他-保留所有属性
	 * @param obj
	 * @param annos
	 * @return
	 */
	public static Map<String, Object> asMapWithAnnos(int mode, Object obj,
			@SuppressWarnings("unchecked") Class<? extends Annotation>... annos) {
		if (obj == null) {
			return null;
		}

		Map<String, Object> propWithAnnoMap = new LinkedHashMap<String, Object>();
		if (ArrayUtil.isEmpty(annos)) {
			return propWithAnnoMap;
		}

		nextField: for (Map.Entry<String, Field> entry : getFieldMap(obj.getClass()).entrySet()) {
			for (Class<? extends Annotation> anno : annos) {
				Field field = entry.getValue();
				if (field.isAnnotationPresent(anno)) {
					Object val = getFieldValue(obj, field);

					boolean doPut = true;
					switch (mode) {
					case AsMapModes.PROP_VALUE_NOT_NULL:
						doPut = (val != null);
						break;
					}

					if (doPut) {
						propWithAnnoMap.put(field.getName(), val);
					}

					continue nextField;
				}
			}
		}

		return propWithAnnoMap;
	}

	@SuppressWarnings("unchecked")
	public static <T> T convert2Object(Map<String, Object> map, Class<T> clazz) {
		if (MapUtil.isEmpty(map)) {
			return null;
		}
		Object object;
		try {
			object = clazz.newInstance();
			BeanUtils.populate(object, map);
		} catch (Exception e) {
			throw new RuntimeException("Convert to object error, class=" + clazz, e);
		}
		return (T) object;
	}
}
