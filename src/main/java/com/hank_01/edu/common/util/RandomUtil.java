package com.hank_01.edu.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtil {
	private static Random random = new Random(System.currentTimeMillis());

	public static BigDecimal randomDouble(int integer, int scale, BigDecimal minValue, BigDecimal maxValue) {
		if (minValue == null) {
			minValue = BigDecimal.ZERO;
		}

		if (maxValue == null) {
			maxValue = new BigDecimal("10000.00");
		}

		long factor = 1;
		for (int i = 0; i < (integer - scale); i++) {
			factor = factor * 10;
		}

		if (maxValue.doubleValue() == minValue.doubleValue()) {
			return minValue;
		}

		double r = (Math.abs(random.nextDouble()) * factor) % (maxValue.doubleValue() - minValue.doubleValue());
		r = r + minValue.doubleValue();

		BigDecimal rval = new BigDecimal(r);
		rval = rval.setScale(scale, BigDecimal.ROUND_HALF_UP);

		return rval;
	}

	public static int randomInteger(BigDecimal minValue, BigDecimal maxValue) {
		if (minValue == null) {
			minValue = BigDecimal.ZERO;
		}

		if (maxValue == null) {
			maxValue = new BigDecimal("1000000000");
		}

		if (maxValue.intValue() == minValue.intValue()) {
			return minValue.intValue();
		}

		int r = Math.abs(random.nextInt()) % (maxValue.intValue() - minValue.intValue());
		r = r + minValue.intValue();
		return r;
	}

	public static short randomShort(BigDecimal minValue, BigDecimal maxValue) {
		if (minValue == null) {
			minValue = BigDecimal.ZERO;
		}

		if (maxValue == null) {
			maxValue = new BigDecimal("10000");
		}

		if (maxValue.intValue() == minValue.intValue()) {
			return (short) minValue.intValue();
		}

		int r = Math.abs(random.nextInt()) % (maxValue.intValue() - minValue.intValue());
		r = r + minValue.intValue();
		return (short) r;
	}

	public static boolean randomBoolean() {
		return random.nextBoolean();
	}

	public static char randomChar() {
		int r = randomInteger(BigDecimal.ZERO, new BigDecimal("25"));
		try {
			byte c = (byte) ("a".getBytes("US-ASCII")[0] + (byte) r);
			byte[] bytes = new byte[1];
			bytes[0] = c;
			String str = new String(bytes, "US-ASCII");
			return str.charAt(0);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static byte randomByte() {
		return (byte) randomInteger(BigDecimal.ZERO, new BigDecimal("255"));
	}

	public static String randomString(Integer min, Integer max) {
		if (min == null) {
			min = 0;
		}

		if (max == null) {
			max = 6;
		}

		StringBuffer sb = new StringBuffer();
		int l = randomInteger(new BigDecimal(min), new BigDecimal(max));
		for (int i = 0; i < l; i++) {
			sb.append(randomChar());
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		while (true) {
			Integer r = randomInteger(new BigDecimal("0"), new BigDecimal("99999999"));
			if (list.contains(r)) {
				System.out.println("size:" + list.size() + ",repeat:" + r);
			} else {
				list.add(r);
			}

		}
	}
}
