package com.hank_01.edu.common.util;

import java.text.DecimalFormat;

public class LocationCodeUtil {

	private final static DecimalFormat LOCATION_CODE_FORMATTER = new DecimalFormat(
			"00");

	private final static String LOCATION_CODE_PLACEHOLDER = "__";

	public static String formatCode(int code) {
		return LOCATION_CODE_FORMATTER.format(code);
	}

	public static String getLocationOIDTemplate(String provinceCode,
			String cityCode, String districtCode) {
		StringBuffer stream = new StringBuffer();

		if (provinceCode == null) {
			provinceCode = LOCATION_CODE_PLACEHOLDER;
		}
		if (cityCode == null) {
			cityCode = LOCATION_CODE_PLACEHOLDER;
		}
		if (districtCode == null) {
			districtCode = LOCATION_CODE_PLACEHOLDER;
		}
		stream.append(provinceCode).append(cityCode).append(districtCode);

		return stream.toString();
	}

}
