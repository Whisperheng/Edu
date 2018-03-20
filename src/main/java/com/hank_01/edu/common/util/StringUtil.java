package com.hank_01.edu.common.util;

import java.util.Map;
import java.util.regex.Pattern;

public class StringUtil {
	private static final String CHAR_ARRAY = "abcdef1234567890";

	/**
	 * 字符串处理方式 - 左侧
	 */
	private static final int LEFT = -1;

	/**
	 * 字符串处理方式 - 右侧
	 */
	private static final int RIGHT = 1;

	/**
	 * 字符串处理方式 - 两侧
	 */
	private static final int BOTH = 2;

	/**
	 * 空字符串
	 */
	public static final String EMPTY = "";

	/**
	 * 0字符串
	 */
	public static final String ZERO = "0";

	// 生成随机字符串
	public static String randomStr(int length) {
		StringBuilder str = new StringBuilder();
		int totalLength = CHAR_ARRAY.length();
		while (length-- > 0) {
			str.append(CHAR_ARRAY.charAt((int) (Math.random() * totalLength)));
		}
		return str.toString();
	}

	public static boolean isEmpty(final CharSequence seq) {
		return seq == null || seq.length() == 0;
	}

	public static boolean isNotEmpty(final CharSequence seq) {
		return !isEmpty(seq);
	}

	public static boolean isEmpty(final Map<String, Object> map, final String key) {
		if (map == null || map.size() == 0) {
			return true;
		}
		Object value = map.get(key);
		return isEmpty(value == null ? "" : value.toString());
	}

	public static boolean isNotEmpty(final Map<String, Object> map, final String key) {
		return !isEmpty(map, key);
	}

	/**
	 * 返回非null的字符串
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static String parseString(Map<String, Object> map, String key) {
		if (map == null || map.size() == 0) {
			return "";
		}
		Object value = map.get(key);
		return value == null ? "" : value.toString();
	}

	public static String joinWith(String split, Object[] objs) {
		if (objs.length <= 0) {
			return "";
		}
		Object first = objs[0];
		String string = first + "";

		for (int i = 1; i < objs.length; i++) {
			Object o = objs[i];
			string += split + o;
		}
		return string;
	}

	public static String[] stringToStringArray(String originString) {
		return originString.split(",");
	}

	public static String stringsToString(String[] strings) {
		if (strings.length <= 0) {
			return "";
		}
		String first = strings[0];
		String retString = first;

		for (int i = 1; i < strings.length; i++) {
			String string = strings[i];
			retString += "," + string;
		}
		return retString;
	}

	public static Boolean isCellphone(String cellphone) {
		String phoneRegex = "1(3|4|5|8|7)\\d{9}";
		Pattern cellphonePattern = Pattern.compile(phoneRegex);
		return isEmpty(cellphone) ? false : cellphonePattern.matcher(cellphone).matches();
	}

	public static String toUpperFristChar(String string) {
		char[] charArray = string.toCharArray();
		charArray[0] -= 32;
		return String.valueOf(charArray);
	}

	public static Boolean checkAccount(String tenantAccount) {
		String accountRegex = "^[0-9a-zA-Z_]{1,}$";
		Pattern accountPattern = Pattern.compile(accountRegex);

		return isEmpty(tenantAccount) ? false : accountPattern.matcher(tenantAccount).matches();
	}

	public static String leftPad(String msg, String padStr, int totalLen) {
		return pad(msg, padStr, totalLen, LEFT);
	}

	/**
	 * 尝试对指定字符串进行指定方式的填充，当遇到填充padStr时超出len长度时终止填充并返回当前填充结果
	 * 
	 * @param msg
	 * @param padStr
	 * @param totalLen
	 * @param padMode
	 * @return
	 */
	private static String pad(String msg, String padStr, int totalLen, int padMode) {
		if (isEmpty(padStr) || totalLen <= 0 || (msg != null && msg.length() >= totalLen)) {
			return msg;
		}

		StringBuilder str = new StringBuilder(defaultString(msg));

		out: while (str.length() + padStr.length() * Math.abs(padMode) <= totalLen) {
			switch (padMode) {
			case LEFT: // 左填充
				str.insert(0, padStr);
				break;

			case RIGHT: // 右填充
				str.append(padStr);
				break;

			case BOTH: // 双向填充
				str.insert(0, padStr).append(padStr);
				break;

			default: // 未知填充方式
				break out;
			}
		}

		return str.toString();
	}

	/**
	 * 默认字符串
	 * 
	 * @param str
	 *            字符串
	 * @return 如果str为null, 则返回空字符串
	 */
	public static String defaultString(String str) {
		return defaultString(str, EMPTY);
	}

	/**
	 * 默认字符串
	 * 
	 * @param str
	 *            字符串
	 * @param defVal
	 *            默认字符串
	 * @return 如果str为null, 则返回defVal
	 */
	public static String defaultString(String str, String defVal) {
		return (str == null ? defVal : str);
	}

	/**
	 * 去除两端空白字符后是否是空字符串
	 * 
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	public static boolean isBlank(String str) {
		return (str == null || str.trim().length() == 0);
	}

	/**
	 * 去除两端空白字符后是否不是空字符串
	 * 
	 * @param str
	 *            字符串
	 * @return boolean
	 */
	public static boolean isNotBlank(String str) {
		return (str != null && str.trim().length() > 0);
	}

	/**
	 * 颠倒字符串中字符的顺序
	 * 
	 * @param str
	 * @return
	 */
	public static String reverse(String str) {
		if (isBlank(str)) {
			return str;
		}

		return new StringBuilder(str).reverse().toString();
	}
}
