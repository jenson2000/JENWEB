package com.jen.sen;

import com.jen.sen.untils.MD5Util;
import com.jen.sen.untils.SysDateTimeUtil;
import com.jen.sen.untils.Util;

public class TestMd5 {
	public static void main(String[] args) { 
		System.out.println("aaaaaaaaa:"+MD5Util.MD5("123456"));
		System.out.println("bbbbbbbbb:"+MD5Util.SHA256("123456"));
		System.out.println("ccccccccc:"+SysDateTimeUtil.getTimeStrBySeconds(30000));
		;
	}

}
