package com.jen.sen.shiro;



import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.SimpleByteSource;

import com.jen.sen.commons.Constant;
import com.jen.sen.web.vo.system.UserVo;

/**
 * Shiro工具类,获取session,Subjec,UserVo等
 * filename ShiroUtils.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
public class ShiroUtils {
	
	

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static UserVo getUserVo() {
		return (UserVo)SecurityUtils.getSubject().getPrincipal();
	}

	public static Long getUserId() {
		return getUserVo().getUserId();
	}
	
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static boolean isLogin() {
		return SecurityUtils.getSubject().getPrincipal() != null;
	}

	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	/***验证码***/
	public static String getKaptcha(String key) {
		String kaptcha = getSessionAttribute(key).toString();
		getSession().removeAttribute(key);
		return kaptcha;
	}
	/**
	 * shiro加密算法,与appli***-shiro**.xml中加密算法一致,保证登录验证密码和加密方式一致,solt为jenson
	 * @param str
	 * @return
	 * jenson.wang
	 */
	public static String getpwdShiro(String str) {
		//String salt=new SecureRandomNumberGenerator().nextBytes().toHex();
		return new SimpleHash("SHA-256",str,new SimpleByteSource(Constant.I_SHIRO_SALT),2).toHex();
	}
	
	
	

}
