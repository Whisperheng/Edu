package com.hank_01.edu.common.util;

import java.util.Collection;

/**
 * 集合工具
 * 
 * @author hyq
 */
public final class CollectionUtil {

	/**
	 * 获得集合对象包含的对象数量
	 * 
	 * @param collection
	 *            集合
	 * @return 集合大小，如果collection为null，则返回0
	 */
	public static int size(Collection<?> collection) {
		return (collection == null ? 0 : collection.size());
	}

	/**
	 * 判断集合对象是否为null或空
	 * 
	 * @param collection
	 *            集合
	 * @return 是否为null或空
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return (collection == null || collection.isEmpty());
	}

	/**
	 * 判断集合对象是否不为null或空
	 * 
	 * @param collection
	 *            集合
	 * @return 是否不为null或空
	 */
	public static boolean isNotEmpty(Collection<?> collection) {
		return (collection != null && !collection.isEmpty());
	}

	/**
	 * 默认无参构造函数
	 */
	private CollectionUtil() {
	}
}
