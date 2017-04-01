package com.jen.sen.untils;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 读取配置文件cofig.properties。
 * filename JenConfigUtil.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
public class JenConfigUtil {
	/** 日志工具 实例 */
	private static final Logger log = LogManager.getLogger(JenConfigUtil.class);

	/** 配置文件路径 */
	private static final String CONFIG_FILE = "/config.properties";

	/** 配置相关信息 */
	private static Properties properties = new Properties();


	static {
		InputStream in = null;
		try {
			in = JenConfigUtil.class.getResourceAsStream(CONFIG_FILE);
			properties.load(in);
		} catch (Exception e) {
			log.error("", e);
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}


	/**
	 * 根据KEY得到系统配置的字符串参数值。
	 * 
	 * @param key
	 *            配置key
	 * @return 配置value
	 * @since 0.1_2012-7-19
	 */
	public static String getStringValue(String key) {
		String stringValue = (String) properties.get(key);
		// log.debug(CONFIG_FILE + "[" + key + "]=" + stringValue);
		return stringValue;
	}

	/**
	 * 根据KEY得到系统配置的短整数参数值。
	 * 
	 * @param key
	 *            配置key
	 * @return 配置value
	 * @since 0.1_2012-7-24
	 */
	public static short getShortValue(String key) {
		return getShortValue(key, (short) 0);
	}

	/**
	 * 根据KEY得到系统配置的短整数参数值。
	 * 
	 * @param key
	 *            配置key
	 * @param defaultValue
	 *            当取值错误时的默认值
	 * @return 配置value
	 * @since 0.1_2012-8-27
	 */
	public static short getShortValue(String key, short defaultValue) {
		String stringValue = (String) properties.get(key);
		// log.debug(CONFIG_FILE + "[" + key + "]=" + stringValue);
		if (stringValue == null || stringValue.trim().length() == 0) {
			return defaultValue;
		}
		short shortValue = defaultValue;
		try {
			shortValue = Short.parseShort(stringValue);
		} catch (NumberFormatException e) {
			log.error("property[" + key + "] value error", e);
		}
		return shortValue;
	}

	/**
	 * 根据KEY得到系统配置的整数参数值。
	 * 
	 * @param key
	 *            配置key
	 * @return 配置value
	 * @since 0.1_2012-7-19
	 */
	public static int getIntValue(String key) {
		return getIntValue(key, 0);
	}

	/**
	 * 根据KEY得到系统配置的整数参数值。
	 * 
	 * @param key
	 *            配置key
	 * @param defaultValue
	 *            当取值错误时的默认值
	 * @return 配置value
	 * @since 0.1_2012-8-27
	 */
	public static int getIntValue(String key, int defaultValue) {
		String stringValue = (String) properties.get(key);
		// log.debug(CONFIG_FILE + "[" + key + "]=" + stringValue);
		if (stringValue == null || stringValue.trim().length() == 0) {
			return defaultValue;
		}
		int intValue = defaultValue;
		try {
			intValue = Integer.parseInt(stringValue);
		} catch (NumberFormatException e) {
			log.error("property[" + key + "] value error", e);
		}
		return intValue;
	}

	/**
	 * 根据KEY得到系统配置的整数参数值。
	 * 
	 * @param key
	 *            配置key
	 * @return 配置value
	 * @since 0.1_2012-7-19
	 */
	public static long getLongValue(String key) {
		return getLongValue(key, 0L);
	}

	/**
	 * 根据KEY得到系统配置的长整型参数值。
	 * 
	 * @param key
	 *            配置key
	 * @param defaultValue
	 *            当取值错误时的默认值
	 * @return 配置value
	 * @since 0.1_2012-8-27
	 */
	public static long getLongValue(String key, long defaultValue) {
		String stringValue = (String) properties.get(key);
		// log.debug(CONFIG_FILE + "[" + key + "]=" + stringValue);
		if (stringValue == null || stringValue.trim().length() == 0) {
			return defaultValue;
		}
		long longValue = defaultValue;
		try {
			longValue = Long.parseLong(stringValue);
		} catch (NumberFormatException e) {
			log.error("property[" + key + "] value error", e);
		}
		return longValue;
	}

	/**
	 * 根据KEY得到系统配置的布尔型参数值。
	 * 
	 * @param key
	 *            配置key
	 * @return 配置value
	 * @since 0.1_2012-8-28
	 */
	public static boolean getBooleanValue(String key) {
		return getBooleanValue(key, false);
	}

	/**
	 * 根据KEY得到系统配置的布尔型参数值。
	 * 
	 * @param key
	 *            配置key
	 * @param defaultValue
	 *            当取值错误时的默认值
	 * @return 配置value
	 * @since 0.1_2012-8-28
	 */
	public static boolean getBooleanValue(String key, boolean defaultValue) {
		String stringValue = (String) properties.get(key);
		// log.debug(CONFIG_FILE + "[" + key + "]=" + stringValue);
		if (stringValue == null || stringValue.trim().length() == 0) {
			return defaultValue;
		}
		if ("1".equalsIgnoreCase(stringValue) || "on".equalsIgnoreCase(stringValue) || "yes".equalsIgnoreCase(stringValue)
				|| "true".equalsIgnoreCase(stringValue)) {
			return true;
		}
		return false;
	}
}
