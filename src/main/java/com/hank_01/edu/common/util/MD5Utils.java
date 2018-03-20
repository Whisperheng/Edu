package com.hank_01.edu.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

	public static String string2MD5(String inStr) throws NoSuchAlgorithmException {
		
		MessageDigest md5 = MessageDigest.getInstance("MD5");
        
        char[] cs = inStr.toCharArray();
        byte[] bs = new byte[cs.length];

        for (int i = 0; i < cs.length; i++){
            bs[i] = (byte) cs[i];
        }
        
        byte[] md5Bytes = md5.digest(bs);
        
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        
        return hexValue.toString();
	}
}
