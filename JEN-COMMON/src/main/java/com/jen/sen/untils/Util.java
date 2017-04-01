package com.jen.sen.untils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

/**
 * 工具类
 * filename Util.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
public class Util {

	/**
	 * 字符串是否为空或null值。
	 * isNullOrEmpty(null)返回 true;
	 * isNullOrEmpty("")返回 true;
	 * isNullOrEmpty(" ")返回 true;
	 * @author jenson
	 * @param str
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(String str) {
		return isNull(str) || isEmpty(str);
	}

	/**
	 * 字符串为空或null值返回0。
	 * isNullOrEmpty(null)返回 0;
	 * isNullOrEmpty("")返回 0;
	 * isNullOrEmpty(" ")返回 0;
	 * @author jenson
	 * @param str
	 * @return Integer
	 */
	public static Integer isNullOrEmptyToInteger(String str) {
		if (isNull(str) || isEmpty(str)) {
			return Integer.valueOf(0);
		} else {
			return Integer.valueOf(str.trim());
		}
	}

	/**
	 * 将10进制数转换成二进制字符串。
	 * 
	 * @author jenson
	 * @param num
	 * @return
	 */
	public static char[] tenTOTwo(int num) {
		String binNum = "";
		while (num >= 2) {
			binNum += (num % 2) + "";
			num = (int) (num / 2);
		}
		binNum += num % 2;
		char[] binChar = binNum.toCharArray();
		char temp;
		for (int i = 0; i < Math.ceil(binChar.length / 2); i++) {
			temp = binChar[i];
			binChar[i] = binChar[(binChar.length - 1) - i];
			binChar[binChar.length - 1] = temp;
		}
		return binChar;
	}

	/**
	 * 获取bit值位置值。
	 * 
	 * @author jenson
	 * @param bits
	 * @param num
	 * @return boolean
	 */
	public static boolean getBitValue(char[] bits, int num) {
		if (!isNull(bits) && bits.length >= num) {
			String bit = String.valueOf(bits);
			int chr_index = Math.abs(num - bits.length + 1);
			char chr = bit.charAt(chr_index);
			String str = String.valueOf(chr);
			if (str.equals("0")) {
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	/**
	 * 字符串为空或null值返回0。
	 * isNullOrEmpty(null)返回 0;
	 * isNullOrEmpty("")返回 0;
	 * isNullOrEmpty(" ")返回 0;
	 * @author jenson
	 * @param str
	 * @return Long
	 */
	public static Long isNullOrEmptyToLong(String str) {
		if (isNull(str) || isEmpty(str)) {
			return Long.valueOf(0);
		} else {
			return Long.valueOf(str.trim());
		}
	}

	/**
	 * 字符串为空或null值返回0。
	 * isNullOrEmpty(null)返回 0;
	 * isNullOrEmpty("")返回 0;
	 * isNullOrEmpty(" ")返回 0;
	 * @author jenson
	 * @param str
	 * @return Short
	 */
	public static Short isNullOrEmptyToShort(String str) {
		if (isNull(str) || isEmpty(str)) {
			return Short.valueOf((short) 0);
		} else {
			return Short.valueOf(str.trim());
		}
	}

	/**
	 * 判断对象是否为null。
	 * isNullOrEmpty(null)返回 true;
	 * isNullOrEmpty("")返回true;
	 * isNullOrEmpty(" ")返回 true;
	 * @author jenson
	 * @param obj
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null || obj.toString().trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断对象是否为null。
	 * 
	 * @author jenson
	 * @param obj
	 * @return boolean
	 */
	public static boolean isNull(Object obj) {
		return obj == null || obj.equals("null");
	}

	/**
	 * 字符串是否为空。
	 * isEmpty("")返回 true
	 * isEmpty(" ")返回 true
	 * @author jenson
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		return str != null && str.trim().equals("");
	}

	/**
	 * 将字符类型的值为null的转换为空的字符串("")。
	 * 
	 * @author jenson
	 * @param str
	 * @return String
	 */
	public static String isNullToEmptyStr(String str) {
		if (str == null) {
			return "";
		} else {
			return str;
		}
	}

	public static BigDecimal isNullToZero(BigDecimal b) {
		if (isNullOrEmpty(b)) {
			return BigDecimal.ZERO;
		} else {
			return b;
		}
	}

	public static String isNullToStrZero(String b) {
		if (isNullOrEmpty(b)) {
			return "0";
		} else {
			return b;
		}
	}

	/**
	 * 获得字符串中某个关键词第一次出现的位置。
	 * @author jenson
	 * @param str  字符串
	 * @param keyWordRegex 关键词的正则表达式
	 * @return int 关键词第一次出现位置的索引
	 */
	public static int getKeyWordsFirstPosition(String str, String keyWordRegex) {
		Assert.hasText(str);
		Assert.hasText(keyWordRegex);
		int index = 0;
		Pattern pattern = Pattern.compile(keyWordRegex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			index = matcher.start();
			return index;
		}
		return index;
	}


	/**
	 * 根据指定偏移量和指定长度,截取字符串。
	 * 
	 * @author jenson
	 * @param str
	 * @param offset
	 * @param len
	 * @return
	 */
	public static String getSubString(String str, int offset, int len) {
		if (str.length() <= len) {
			len = str.length();
		}
		if ((len + offset) < str.length()) {
			return str.substring(offset, (len + offset));
		} else {
			return str.substring(offset, str.length() - offset);
		}
	}

	/**
	 * 根据指定偏移量,截取字符串。
	 * 
	 * @author jenson
	 * @param str
	 * @param offset
	 * @return
	 * @since 1.0_2016-10-24
	 */
	public static String getSubString(String str, int offset) {
		if (offset < str.length()) {
			return str.substring(offset, str.length());
		} else {
			return "";
		}
	}

	/**
	 * 获取某个子串在字符串中出现的次数。
	 * 
	 * @author jenson
	 * @param sourceStr
	 *            字符串
	 * @param subStr
	 *            子串
	 * @return
	 * @since 0.1_2016-12-21
	 */
	public static int getAppearCountByStr(String sourceStr, String subStr) {
		int index = 0;
		int count = 0;
		while ((index = sourceStr.indexOf(subStr, index)) != -1) {
			count++;
			index += subStr.length();
		}
		return count;
	}

	
	public static boolean isMobileNO(String mobiles) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("^((1[3-9][0-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * String转换为object数组,如Str=1,2,3,4,5
	 * 
	 * @author jenson.wang
	 * 
	 */
	public static Object[] String2Object(String str) {
		String[] strr = str.split(",");
		Object array[] = new Object[strr.length];
		for (int i = 0; i < strr.length; i++) {
			array[i] = Long.parseLong(strr[i]);
		}
		return array;
	}

	// 序列化,对象转化为byte数组
	public static byte[] serialize(Object o) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			ObjectOutputStream outo = new ObjectOutputStream(out);
			outo.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return out.toByteArray();
	}

	// 反序列化,byte数组转化为对象
	public static Object deserialize(byte[] b) {
		ObjectInputStream oin;
		try {
			oin = new ObjectInputStream(new ByteArrayInputStream(b));
			try {
				return oin.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * tontroller中获取request里的字符类型参数
	 * 
	 * @param  req
	 * @param  strParaName
	 * @return String
	 */
	public static String getParameterString(HttpServletRequest req, String strParaName) {
		String str = req.getParameter(strParaName);

		if (isNullOrEmpty(str)) {
			str = "";
		}
		return str.trim();
	}

	/**
	 * tontroller中获取request里的Long类型参数,空为-1
	 * 
	 * @param  req
	 * @param  strParaName
	 * @return Long
	 */
	public static Long getParameterLong(HttpServletRequest req, String strParaName) {
		String str = req.getParameter(strParaName);
		Long i = -1L;

		if (!isNullOrEmpty(str)) {
			i = Long.parseLong(str);
		}

		return  i;			
	}

	/**
	 * tontroller中获取request里的int类型参数,空为-1
	 * @param @param req
	 * @param @param strParaName
	 * @param @return
	 * @return int
	 */
	public static int getParameterInt(HttpServletRequest req, String strParaName) {
		String str = req.getParameter(strParaName);
		int i = -1;

		if (!isNullOrEmpty(str)) {
			i = Integer.parseInt(str);
		}

		return i;
	}

}
