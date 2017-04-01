package com.jen.sen.untils;

import java.util.Random;

/**
 * 限机数产生工具。
 * filename RandDataFactory.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
public class RandDataFactory {

	/**
	 * 根据要产生随机数的位数，得到一个随机数字的字符串。
	 */
	public static String getRandData(int digit) {
		Random random = new Random();
		StringBuffer randDataSb = new StringBuffer();
		for (int i = 0; i < digit; i++) {
			random.setSeed(random.nextInt());
			randDataSb.append(random.nextInt(10));
		}
		return randDataSb.toString();
	}
}
