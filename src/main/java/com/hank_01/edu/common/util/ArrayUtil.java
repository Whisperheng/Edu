package com.hank_01.edu.common.util;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * 数组工具
 * 
 * @author hyq
 *
 */
public final class ArrayUtil {
	/**
	 * 是否是数组类型，为null或其他类型返回false
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isArray(Object obj) {
		if (obj == null) {
			return false;
		}

		return obj.getClass().isArray();
	}

	/**
	 * 获得数组对象包含的元素数量
	 * 
	 * @param array
	 *            数组
	 * @return 数组大小，如果array为null，则返回0
	 */
	public static int length(Object[] array) {
		return (array == null ? 0 : array.length);
	}

	/**
	 * 获得数组对象包含的元素数量
	 * 
	 * @param array
	 *            数组
	 * @return 数组大小，如果array为null，则返回0
	 */
	public static int length(int[] array) {
		return (array == null ? 0 : array.length);
	}

	/**
	 * 获得所有数组对象包含的元素总数量
	 * 
	 * @param arrays
	 *            数组集
	 * @return 数组集大小，如果arrays为null，则返回0
	 */
	public static int length(Object[]... arrays) {
		int len = 0;
		if (arrays != null) {
			for (Object[] array : arrays) {
				if (array != null) {
					len += array.length;
				}
			}
		}

		return len;
	}

	/**
	 * 获得所有数组对象包含的元素总数量
	 * 
	 * @param arrays
	 *            数组集
	 * @return 数组集大小，如果arrays为null，则返回0
	 */
	public static int length(int[]... arrays) {
		int len = 0;
		if (arrays != null) {
			for (int[] array : arrays) {
				if (array != null) {
					len += array.length;
				}
			}
		}

		return len;
	}

	/**
	 * 判断数组对象是否为null或空
	 * 
	 * @param array
	 *            数组
	 * @return 是否为null或空
	 */
	public static boolean isEmpty(Object[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * 判断数组对象是否为null或空
	 * 
	 * @param array
	 *            数组
	 * @return 是否为null或空
	 */
	public static boolean isEmpty(int[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * 判断数组对象是否不为null或空
	 * 
	 * @param array
	 *            数组
	 * @return 是否不为null或空
	 */
	public static boolean isNotEmpty(Object[] array) {
		return (array != null && array.length > 0);
	}

	/**
	 * 判断数组对象是否不为null或空
	 * 
	 * @param array
	 *            数组
	 * @return 是否不为null或空
	 */
	public static boolean isNotEmpty(int[] array) {
		return (array != null && array.length > 0);
	}

	/**
	 * 判断数组是否包含指定对象
	 * 
	 * @param array
	 *            数组
	 * @param target
	 *            对象
	 * @return 是否包含
	 */
	public static boolean contains(Object[] array, Object target) {
		if (isNotEmpty(array)) {
			for (Object obj : array) {
				if ((target == null && obj == null) || (target != null && target.equals(obj))) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 判断数组是否包含指定整数
	 * 
	 * @param array
	 *            数组
	 * @param target
	 *            目标整数
	 * @return 是否包含
	 */
	public static boolean contains(int[] array, int target) {
		if (isNotEmpty(array)) {
			for (int obj : array) {
				if (target == obj) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 判断数组是否包含指定对象集中任何一个对象
	 * 
	 * @param array
	 *            数组
	 * @param targets
	 *            对象集
	 * @return 是否包含
	 */
	public static boolean containsAny(Object[] array, Object... targets) {
		if (isNotEmpty(array) && isNotEmpty(targets)) {
			for (Object target : targets) {
				if (contains(array, target)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 判断数组是否包含指定对象集中任何一个整数
	 * 
	 * @param array
	 *            数组
	 * @param targets
	 *            整数集
	 * @return 是否包含
	 */
	public static boolean containsAny(int[] array, int... targets) {
		if (isNotEmpty(array) && isNotEmpty(targets)) {
			for (int target : targets) {
				if (contains(array, target)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 判断数组是否包含指定对象集中所有对象
	 * 
	 * @param array
	 *            数组
	 * @param targets
	 *            对象集
	 * @return 是否包含
	 */
	public static boolean containsAll(Object[] array, Object... targets) {
		if (isEmpty(array) || isEmpty(targets)) {
			return false;
		}

		for (Object target : targets) {
			if (contains(array, target) == false) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 判断数组是否包含指定整数集中所有整数
	 * 
	 * @param array
	 *            数组
	 * @param targets
	 *            整数集
	 * @return 是否包含
	 */
	public static boolean containsAll(int[] array, int... targets) {
		if (isEmpty(array) || isEmpty(targets)) {
			return false;
		}

		for (int target : targets) {
			if (contains(array, target) == false) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 根据指定下标从给定的数组中获得越界
	 * 
	 * @param <T>
	 *            元素类型
	 * @param array
	 *            数组
	 * @param index
	 *            下标, 负数代表从最后一个元素开始倒数，负无穷大时认为下标越界
	 * @return 元素
	 */
	public static <T> T getElement(T[] array, int index) {
		return getElement(array, index, null);
	}

	/**
	 * 根据指定下标从给定的数组中获得越界
	 * 
	 * @param array
	 *            数组
	 * @param index
	 *            下标, 负数代表从最后一个元素开始倒数，负无穷大时认为下标越界
	 * @return 整数
	 */
	public static int getElement(int[] array, int index) {
		return getElement(array, index, 0);
	}

	/**
	 * 根据指定下标从给定的数组中获得数据，数组为空或下标越界时返回默认元素
	 * 
	 * @param <T>
	 *            元素类型
	 * @param array
	 *            数组
	 * @param index
	 *            下标, 负数代表从最后一个元素开始倒数，负无穷大时认为下标越界
	 * @param defElement
	 *            默认元素
	 * @return 元素
	 */
	public static <T> T getElement(T[] array, int index, T defElement) {
		if (isEmpty(array) || index >= array.length) {
			return defElement;
		}

		if (index < 0) {
			index = array.length + index;
		}

		return (index >= 0 ? array[index] : defElement);
	}

	/**
	 * 根据指定下标从给定的数组中获得整数，数组为空或下标越界时返回默认整数
	 * 
	 * @param array
	 *            数组
	 * @param index
	 *            下标, 负数代表从最后一个元素开始倒数，负无穷大时认为下标越界
	 * @param defElement
	 *            默认整数
	 * @return 整数
	 */
	public static int getElement(int[] array, int index, int defElement) {
		if (isEmpty(array) || index >= array.length) {
			return defElement;
		}

		if (index < 0) {
			index = array.length + index;
		}

		return (index >= 0 ? array[index] : defElement);
	}

	/**
	 * 在根据给定下标从数组中取出最多指定数量的整数
	 * 
	 * @param array
	 *            数组
	 * @param offset
	 *            起始下标(偏移), 负数代表从最后一个元素开始倒数，负无穷大时位置为0
	 * @param length
	 *            最大记录数，正数，当起始下标加length超过数组长度时，length等于数组长度减起始下标
	 * @return 取出的整数数组
	 */
	public static int[] getElements(int[] array, int offset, int length) {
		if (array == null) {
			return null;

		} else if (array.length == 0 || offset >= array.length || length <= 0) {
			return new int[0];
		}

		if (offset < 0) {
			offset = Math.max(0, array.length + offset);
		}

		if (offset + length > array.length) {
			length = array.length - offset;
		}

		int[] result = new int[length];
		System.arraycopy(array, offset, result, 0, length);

		return result;
	}

	/**
	 * 返回包含目标数组指定范围[start, length)内整数的数组
	 * 
	 * @param array
	 *            数组
	 * @param start
	 *            起始下标(包含), 负数代表从最后一个元素开始倒数，负无穷大时位置为0，正无穷大位置为length
	 * @return 取出的整数数组
	 */
	public static int[] subArray(int[] array, int start) {
		return subArray(array, start, 0);
	}

	/**
	 * 返回包含目标数组指定范围[start, end)内整数的数组
	 * 
	 * @param array
	 *            数组
	 * @param start
	 *            起始下标(包含), 负数代表从最后一个元素开始倒数，负无穷大和零时位置为0，正无穷大时位置为length
	 * @param end
	 *            结束下标(不包含), 负数代表从最后一个元素开始倒数，负无穷大时位置为0，零和正无穷大位置为length
	 * @return 取出的整数数组
	 */
	public static int[] subArray(int[] array, int start, int end) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return new int[0];
		}

		if (start < 0) {
			start = Math.max(0, array.length + start); // 负无穷大取0
		}

		if (end <= 0) {
			end = array.length + end;
		}

		end = Math.min(end, array.length); // 正无穷大取length

		if (start >= array.length || end > array.length || start >= end) {
			return new int[0];
		}

		int[] result = new int[end - start];
		System.arraycopy(array, start, result, 0, end - start);

		return result;
	}

	/**
	 * 将指定数组集连接成一个数组
	 * 
	 * @param arrays
	 * @return
	 */
	public static Object[] concat(Object[]... arrays) {
		if (arrays == null) {
			return null;
		}

		Object[] newArray = new Object[length(arrays)];
		for (int i = 0, offset = 0; i < arrays.length; i++) {
			if (arrays[i] != null) {
				System.arraycopy(arrays[i], 0, newArray, offset, arrays[i].length);
				offset += arrays[i].length;
			}
		}

		return newArray;
	}

	/**
	 * 将指定数组集连接成一个数组
	 * 
	 * @param arrays
	 * @return
	 */
	public static int[] concat(int[]... arrays) {
		if (arrays == null) {
			return null;
		}

		int[] newArray = new int[length(arrays)];
		for (int i = 0, offset = 0; i < arrays.length; i++) {
			if (arrays[i] != null) {
				System.arraycopy(arrays[i], 0, newArray, offset, arrays[i].length);
				offset += arrays[i].length;
			}
		}

		return newArray;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] toArray(Collection<T> c, Class<?> componentType) {
		T[] array = (T[]) Array.newInstance(componentType, c.size());
		return c.toArray(array);
	}

	/**
	 * 默认无参构造函数
	 */
	private ArrayUtil() {
	}
}
