package com.jen.sen;

import com.jen.sen.untils.JenConfigUtil;

public class TestConfig {

	public static void main(String[] args) {
		String s= JenConfigUtil.getStringValue("redis.host");
		System.out.println(s);

	}

}
