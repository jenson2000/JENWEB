package com.jen.sen.shiro;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;

import com.jen.sen.commons.Constant;
import com.jen.sen.service.system.admin.ISystemService;
import com.jen.sen.untils.Util;
import com.jen.sen.web.vo.system.UserVo;


/**
 * 
 * title：shiro 认证
 * desc：自定义shiro realm
 * @author jenson
 * @email jenson2000@sina.com
 */
public class UserShiroRealm extends AuthorizingRealm {

	@Resource
	private ISystemService iSystemService;
	/***
	 * 权限验证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		UserVo user = (UserVo)principals.getPrimaryPrincipal();
		Long userId = user.getUserId();
		Set<String> permsSet = new HashSet<String>();

		//userid=1获取所有权限(菜单)
		if(userId == 1){
			permsSet= iSystemService.findAllPerms();
		}else{
			permsSet= iSystemService.findPermsByUserId(userId);
		}		
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		return info;

	}
	/***
	 * 登录验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        //查询用户信息
        UserVo user = iSystemService.findByUserName(username);
        //账号不存在
        if(Util.isNullOrEmpty(user.getUserId())) {
            throw new UnknownAccountException("账号不存在");
        }
       
        //密码错误
        else if(!password.equals(user.getUserPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        
        //账号锁定
        else  if(user.getStatus() == 0){
        	throw new LockedAccountException("账号无效,请联系管理员");
        }
        ShiroUtils.setSessionAttribute(Constant.I_SHIRO_SESSION_USER, user);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password,new SimpleByteSource(Constant.I_SHIRO_SALT), getName());
        return info;
	}
	
	 /** 
     * 更新用户授权信息缓存. 
     */  
    public void clearCachedAuthorizationInfo(Object principal) { 
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());  
        clearCachedAuthorizationInfo(principals);

    }  
  
    /** 
     * 清除所有用户授权信息缓存. 
     */  
    public void clearAllCachedAuthorizationInfo() {  
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();  
        if (cache != null) {  
            for (Object key : cache.keys()) {  
                cache.remove(key);  
            }  
        }  
    }  
	

}
