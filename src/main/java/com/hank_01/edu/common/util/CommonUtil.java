/*
package com.hank_01.edu.common.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.juqitech.zb.common.exception.ParameterValidationException;
import com.mifmif.common.regex.Generex;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

	private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

	public static String generateOID() {
		return new ObjectId().toString();
	}

	public static String getExceptionDetail(Throwable e) {

		StringBuffer msg = new StringBuffer("null");

		if (e != null) {
			msg = new StringBuffer("\\n");

			String message = e.toString();

			int length = e.getStackTrace().length;

			if (length > 0) {

				msg.append(message + "\n");

				for (int i = 0; i < length; i++) {

					msg.append("\t" + e.getStackTrace()[i] + "\n");

				}
			} else {

				msg.append(message);
			}

		}
		return msg.toString();

	}
	
	public static SimpleDateFormat getUniversalSDF(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm");
	}

	public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
			"g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
			"t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9" };

	public static boolean equals(Object obj1,Object obj2 ){

		if(obj1 == null && obj2 == null){
			return true;
		}else if(obj1 == null ||obj2 == null){
			return false;
		}else{
			return obj1.equals(obj2);
		}

	}

	*/
/**
	 * 根据用户的增长，会在后面增加数字防止重复
	 * 
	 * @return
	 *//*

	public static String generateReferCode() {
		StringBuffer shortBuffer = new StringBuffer();

		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			shortBuffer.append(chars[random.nextInt(23)]);
		}
		return shortBuffer.toString();
	}

	public static String generatePromotionCode() {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 6; ++i) {
			buffer.append(chars[random.nextInt(chars.length)]);
		}
		return buffer.toString().toUpperCase();
	}
	
	*/
/**
	 * 生成4位渠道码
	 *//*

	public static String generateChannelCode() {
		StringBuffer code = new StringBuffer();

		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			code.append(chars[random.nextInt(chars.length)]);
		}

		return code.toString().toUpperCase();
	}

	public static String generate5bitReferCode() {
		return RandomStringUtils.randomAlphanumeric(5);
	}

	public static String generate4bitReferCode() {
		return RandomStringUtils.randomNumeric(4);
	}

	public static String[] alphabet = new String[] { "a", "b", "c", "d", "e",
			"f", "g", "h", "i", "j", "k",  "m", "n",  "p", "q", "r",
			"s", "t", "u", "v", "w", "x", "y", "z" };

	public static String[] numbers = new String[] {  "1", "2", "3", "4",
			"5", "6", "7", "8", "9" };

	public static String generatePassword() {
		StringBuffer pwdBuffer = new StringBuffer();

		Random random = new Random();
		int numLength = random.nextInt(7) + 1;

		for (int i = 0; i < 8; ++i) {
			boolean isNum = random.nextBoolean();
			if (isNum && numLength > 0) {
				pwdBuffer.append(numbers[random.nextInt(numbers.length)]);
			} else {
				boolean isUpperCase = random.nextBoolean();
				String alp = alphabet[random.nextInt(alphabet.length)];
				if (isUpperCase) {
					pwdBuffer.append(alp.toUpperCase());
				} else {
					pwdBuffer.append(alp);
				}
			}
		}

		return pwdBuffer.toString();
	}

	public static String[] numerics = new String[] { "0", "1", "2", "3", "4",
			"5", "6", "7", "8", "9" };

	*/
/**
	 * 生成短信验证码
	 *//*

	public static String generateValidCode() {
		StringBuffer code = new StringBuffer();

		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			code.append(numerics[random.nextInt(10)]);
		}

		return code.toString();
	}
	

	public static String phoneRegex = "1(3|4|5|8|7)\\d{9}";
	private static Pattern cellphonePattern = Pattern.compile(phoneRegex);

	public static boolean validateCellPhone(String cellphone) {
		if(cellphone == null)
			return false;
		return cellphonePattern.matcher(cellphone).matches();
	}

	public static boolean validateIdCard(String idCardNumber)
	{
		boolean ret = false;
		  
		  Pattern idNumPattern = Pattern.compile("(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])");  
	      //通过Pattern获得Matcher  
	      Matcher idNumMatcher = idNumPattern.matcher(idCardNumber);  
	      //判断用户输入是否为身份证号  
	      if(idNumMatcher.matches()){  
	          System.out.println("您的出生年月日是：");  
	          //如果是，定义正则表达式提取出身份证中的出生日期  
	          Pattern birthDatePattern= Pattern.compile("\\d{6}(\\d{4})(\\d{2})(\\d{2}).*");//身份证上的前6位以及出生年月日  
	          //通过Pattern获得Matcher  
	          Matcher birthDateMather= birthDatePattern.matcher(idCardNumber);  
	          //通过Matcher获得用户的出生年月日  
	          if(birthDateMather.find()){  
	              String year = birthDateMather.group(1);  
	              String month = birthDateMather.group(2);  
	              String date = birthDateMather.group(3);  
	              //输出用户的出生年月日  
	              if (isNumeric(year)&&isNumeric(month)&isNumeric(date)){
	            	  int iyear = Integer.parseInt(year);
	            	  int imonth = Integer.parseInt(month);
	            	  int idate = Integer.parseInt(date);
	            	  ret = iyear<=2100&&iyear>=1900&&imonth<=12&&imonth>=1&&idate>=1&&idate<=31;
	              } else {
	            	  ret = false;
	              }
	              System.out.println(year+"年"+month+"月"+date+"日");   
	          }     
	          ret = ret&&true;
	      }else{  
	          //如果不是，输出信息提示用户  
	          System.out.println("您输入的并不是身份证号");  
	          ret = ret&&false;
	      }  
	      return ret;
	}

	*/
/**
	 * 生成随机手机号
	 *//*

	public static String generateRandomCellPhoneNumber() {
		Generex generex = new Generex(phoneRegex);
		return generex.random(11, 11);
	}

	public static String getChineseBooleanValue(String value) {
		boolean boolvalue = false;
		String[] rets = { "1", "y", "yes", "true" };
		for (String ret : rets) {
			if (ret.equalsIgnoreCase(value)) {
				boolvalue = true;
				break;
			}
		}
		if (boolvalue) {
			return "是";
		} else {
			return "否";
		}
	}

	public static boolean getBool(String bool) {
		boolean ret = false;
		String[] trues = { "1", "yes", "y", "是", "true" };
		for (String t : trues) {
			if (t.equalsIgnoreCase(bool)) {
				ret = true;
				break;
			}
		}
		return ret;
	}
	
	public static String getCapitalizedBooleanText(boolean bool) {
		
		return bool?"Y":"N";
	}
	
	public static String getPassFailedText(boolean result){
		return result?"succeeded":"failed";
	}

	public static String decodeString(String str) {
		if (str == null) {
			return null;
		}
		try {
			return URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static String[] decodeString(String[] str) {
		if (str == null) {
			return null;
		}
		try {
			int size = str.length;
			String[] val = new String[size];
			for (int i = 0; i < size; ++i) {
				if (str[i] != null) {
					val[i] = URLDecoder.decode(str[i], "utf-8");
				} else {
					val[i] = null;
				}
			}
			return val;
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static Float formatNumber(int digit, String value) {
		BigDecimal bd = new BigDecimal(value);
		return bd.setScale(digit, RoundingMode.HALF_UP).floatValue();
	}

	public static String composeURL(String prefix, String surffix) {
		if (!StringUtils.endsWith(prefix, "/")
				&& !StringUtils.startsWith(surffix, "/")) {
			return prefix + "/" + surffix;
		} else {
			return prefix + surffix;
		}
	}

	public static int getOddCount(int start, int end) {
		int count = 0;
		for (int i = start; i <= end; i++) {
			if (i % 2 != 0) {
				count++;
			}
		}
		return count;
	}

	public static int getEvenCount(int start, int end) {
		int count = 0;
		for (int i = start; i <= end; i++) {
			if (i % 2 == 0) {
				count++;
			}
		}
		return count;
	}

	public static String getUserOIDFromRequest() {
		String userOID = "";
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		userOID = attr.getRequest().getParameter("userOID");
		return userOID;
	}
//	public static String getOpOIDFromRequest() {
//		String userOID = "";
//		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
//				.currentRequestAttributes();
//		userOID = attr.getRequest().getParameter("opOID");
//		return userOID;
//	}

	public static String getOrderOIDFromRequest() {
		String userOID = "";
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		userOID = attr.getRequest().getParameter("orderOID");
		return userOID;
	}

	public static boolean isNumeric(String str) {
		if (StringUtils.isEmpty(str)) {
			return false;
		}
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}

	public static String getQRCodeStringFormat(String url, int height,
			int width) throws Exception {
		int BLACK = 0xFF000000;
		int WHITE = 0xFFFFFFFF;
		String format = "png";
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.MARGIN, 0);
		BitMatrix bitMatrix = qrCodeWriter.encode(url,
				BarcodeFormat.QR_CODE, width, height, hints);
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, bitMatrix.getWidth(), bitMatrix.getWidth());
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < bitMatrix.getWidth(); i++) {
            for (int j = 0; j < bitMatrix.getWidth(); j++) {
                if (bitMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(image, format, out);
		String base64content = Base64.encodeBase64String(out.toByteArray());
		return "data:image/" + format + ";base64," + base64content;
	}

	public static int compareVersion(String srcVersion, String tarVersion) {
		String[] srcVersions = srcVersion.split("\\.");
		String[] tarVersions = tarVersion.split("\\.");

		if (srcVersions.length != 3 || tarVersions.length != 3) {
			throw new ParameterValidationException("Wrong version format");
		}

		for (int i = 0; i < 3; ++i) {
			int src = Integer.parseInt(srcVersions[i]);
			int tar = Integer.parseInt(tarVersions[i]);
			if (src > tar) {
				return 1;
			} else if (src < tar) {
				return -1;
			}
		}
		return 0;
	}
	
//	private static String[] datePattern = {"M/dd/yyyy", "dd.M.yyyy", "M/dd/yyyy hh:mm:ss a",
//			"dd.M.yyyy hh:mm:ss a", "dd.MMM.yyyy", "dd-MMM-yyyy", "yyyy-MM-dd"};
	
	private static String[] datePattern = {"yyyy-MM-dd"};
	
	public static boolean isValidDate(String strDate){
		if (StringUtils.isEmpty(strDate)){
			return false;
		}
		boolean ret =false;
		try {
			DateUtils.parseDate(strDate, datePattern);
			ret= true;
		} catch (ParseException e) {
			
		}
		return ret;
	}
	
	public static Date getDate(String strDate){
		if (StringUtils.isEmpty(strDate)){
			return null;
		}
		Date date = null;
		try {
			date = DateUtils.parseDate(strDate, datePattern);
		} catch (ParseException e) {
			
		}
		return date;
	}
	
	public static String escopeSql(String sql){
		if (sql == null){
			return null;
		}
		return StringUtils.replace(sql, "'", "''");
	}

	public static String getClientIP(HttpServletRequest request) {
		String ip = filterRealIp(request.getHeader("x-forwarded-for"));
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static void hideCellphone(Map<String, Object> entity ){
		String cellphone = entity.get("cellphone")+"";
		if (!StringUtils.isEmpty(cellphone)&&cellphone.length()==11){
			cellphone = cellphone.substring(0,3) + "****" + cellphone.substring(7, cellphone.length());
		}
		entity.put("cellphone", cellphone);
	}

	public static String hideCellphone(String cellphone){
		if (!StringUtils.isEmpty(cellphone)&&cellphone.length()==11){
			cellphone = cellphone.substring(0,3) + "****" + cellphone.substring(7, cellphone.length());
		}
		return cellphone;
	}

	public static String filterRealIp(String ipString){
		if(StringUtils.isEmpty(ipString)){
			return null;
		}
		String[] ips = ipString.split(",");
		for(String ip:ips){
			if(isIP(ip.trim())){
				return ip.trim();
			}
		}
		return null;
	}

	public static boolean isIP(String addr)
	{
		if(addr.length() < 7 || addr.length() > 15 || "".equals(addr))
		{
			return false;
		}
		*/
/**
		 * 判断IP格式和范围
		 *//*

		String rexp = "^((2[0-4]\\d|25[0-5]|[1-9]?\\d|1\\d{2})\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$";

		Pattern pat = Pattern.compile(rexp);

		Matcher mat = pat.matcher(addr);

		boolean ipAddress = mat.find();

		return ipAddress;
	}

	public static boolean isCrossDomain(HttpServletRequest request){

		String refer = request.getHeader("Referer");
		String url = request.getRequestURL().toString();

		return !getRequestHost(url).equals(getRequestHost(refer));
	}
	public static String getRequestHost(String url){
		if(url == null){
			return "";
		}
		Pattern p = Pattern.compile("(http://|https://)?([^/|:]*)",Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(url);
		return m.find()?(m.group(1)+m.group(2)):url;
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public static Double ceilNumeric(Object numeric){
		double ret = 0;
		if (numeric==null){
			return ret;
		}
		if (CommonUtil.isNumeric(numeric.toString())||numeric instanceof Number){
			ret = Double.parseDouble(numeric.toString());
		}
		return Math.ceil(ret);
	}
	
	public static String getLocalIP(){     
        InetAddress addr = null;     
        try {  
            addr = InetAddress.getLocalHost();  
        } catch (UnknownHostException e) {  
            e.printStackTrace();  
        }     
                  
        byte[] ipAddr = addr.getAddress();     
        String ipAddrStr = "";     
        for (int i = 0; i < ipAddr.length; i++) {     
            if (i > 0) {     
                ipAddrStr += ".";     
            }     
            ipAddrStr += ipAddr[i] & 0xFF;     
        }     
            return ipAddrStr;     
        }


	*/
/**
	 * 判断是否含有特殊字符
	 *
	 * @param str
	 * @return true为包含，false为不包含
	 *//*

	public static boolean isSpecialChar(String str) {
		String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.find();
	}


	public static Long parseLong(String str, Long defaultValue){
		if (StringUtils.isEmpty(str)){return defaultValue;}
		try {
			return Long.valueOf(str.trim());
		}catch (Exception e){
			return defaultValue;
		}
	}
	public static Long parseLong(String str){
		return parseLong(str, null);
	}
	public static Integer parseInt(String str){
		return parseInt(str, null);
	}

	public static Integer parseInt(String str, Integer defaultValue){
		if (StringUtils.isEmpty(str)){return defaultValue;}
		try {
			return Integer.valueOf(str.trim());
		}catch (Exception e){
			return defaultValue;
		}
	}

	public static boolean isSame(Long arg0, Long arg1){
		if (arg0==null&&arg1==null){return true;}
		if (arg0==null||arg1==null){return false;}
		return arg0.longValue() == arg1;
	}
}
*/
