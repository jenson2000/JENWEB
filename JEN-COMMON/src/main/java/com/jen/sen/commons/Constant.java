package com.jen.sen.commons;

import java.math.BigDecimal;
import com.jen.sen.untils.JenConfigUtil;

/**
 * 声明所有常量。
 * filename Constant.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */

public class Constant {
	
	/** 资源路径 */
	public static final String STATIC_RESOURCES_BASEPATH ="http://localhost:8080/JEN-STATIC/";
	/*** SHIRO密码加密使用的salt*/
	public static final String I_SHIRO_SALT = "jenson";
	
	public static final String I_SHIRO_SESSION_USER = "user";
	
	// ============ 1.异常相关常量===========================================
	/** 没有异常 */
	public static final int EXCEPTION_STATUS_NOERROR = 0;
	/** 有业务异常 */
	public static final int EXCEPTION_STATUS_BIZ_ERROR = 1;
	/** 系统内部错误异常 */
	public static final int EXCEPTION_STATUS_SYSTEM_ERROR = 2;
	/** 系统内部错误异常的消息内容 */
	public static final String SYSTEM_AJAX_Code = "40011";
	public static final String SYSTEM_WEB_Code = "40012";
	public static final String SYSTEM_ERROR_INFO = "对不起，您的操作出现了问题，请联系客服了解情况!!";
	public static final String SYSTEM_SUCCESS_INFO = "您的操作已成功!";
	


	// ============ 2.数据库相关常量 ================================

	/** POJO对象回收站标志(数据库记录删除标志):正常 */
	public static final Short RECYCLE_SIGN_NORMAL = 0;
	/** POJO对象回收站标志(数据库记录删除标志):删除 */
	public static final Short RECYCLE_SIGN_DELETE = 1;
	// ============ 3.系统参数 ================================
	public static final int page_lenght = 10;//翻页每页记录数目
}
