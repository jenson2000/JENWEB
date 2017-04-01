package com.jen.sen.commons;

/**
 * 声明所有需要声明名字的Spring bean的名字, 声明的规则为接口类名，并且类名的第一个字母为小写。
 * filename BeanConstant.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
public class BeanConstant {

	/* =========== DAO 声明实例名称========================================= */
	

	/* ============ 1.系统管理Dao============ */
	/** 用户管理Dao */
	public static final String I_USER_DAO = "iUserDao";
	public static final String I_ROLE_DAO = "iRoleDao";
	public static final String I_SYSTEM_DAO = "iSystemDao";

	/* =========== Service 声明实例名称========================================= */
	/** 用户管理Service */
	public static final String I_USER_SERVICE = "iUserService";
	public static final String I_Role_SERVICE = "iRoleService";
	public static final String I_SYSTEM_SERVICE = "iSystemService";
	/* =========== 1.系统管理Service============ */

	
}
