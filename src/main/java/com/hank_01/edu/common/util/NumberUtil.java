package com.hank_01.edu.common.util;

/**
 * 数字工具
 * 
 * @author hyq
 *
 */
public final class NumberUtil {
	/**
	 * 是否是纯数字，可以包含负号
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isDigits(String number) {
		return (number != null && number.matches("-?\\d+"));
	}

	/**
	 * 将字符串转换成Long
	 * 
	 * @param number
	 * @param defNum
	 * @return
	 */
	public static Long parseLong(String number, Long defNum) {
		try {
			return (number != null ? Long.valueOf(number) : defNum);
		} catch (Exception ex) {
			return defNum;
		}
	}
}
