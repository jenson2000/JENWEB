package com.jen.sen.untils;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 得到spring容器中的bean。
 * filename SpringBeanTools.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
public class SpringBeanTools {
	private static WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();

	public static Object getBean(String beanID) {
		return wac.getBean(beanID);
	}

}
