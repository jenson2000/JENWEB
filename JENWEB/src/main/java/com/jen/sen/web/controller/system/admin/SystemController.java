package com.jen.sen.web.controller.system.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jen.sen.exception.AjaxException;
import com.jen.sen.service.system.admin.ISystemService;
import com.jen.sen.shiro.ShiroUtils;
import com.jen.sen.untils.Util;
import com.jen.sen.web.component.MenuTreeComponent;
import com.jen.sen.web.component.ajaxComponent;
import com.jen.sen.web.vo.system.UserVo;

@Controller
@RequestMapping(value = "system/admin")
public class SystemController {

	@Resource
	private ISystemService iSystemService;

	@ResponseBody
	@RequestMapping(value = "/menuTree")
	public ajaxComponent<MenuTreeComponent> menuTree(HttpServletRequest request) throws AjaxException {
		ajaxComponent<MenuTreeComponent> ajaxComponent = new ajaxComponent<MenuTreeComponent>();

		List<MenuTreeComponent> ls = iSystemService.findMenuTreeByUser(ShiroUtils.getUserId());
		ajaxComponent.setData(ls);
		return ajaxComponent;
	}

	@ResponseBody
	@RequestMapping(value = "/headPage")
	public String headPage(HttpServletRequest request) throws AjaxException {

		UserVo uv = ShiroUtils.getUserVo();
		return uv.getUserAccount();
	}

	/**
	 * 更新某用户授权缓存,如地址 xxx/clsPermsCacheByUserID?userID=111;
	 * 
	 * @param request
	 * @return
	 * @throws AjaxException
	 */

	@ResponseBody
	@RequestMapping(value = "/clsPermsCacheByUserID")
	public ajaxComponent<MenuTreeComponent> clsPermsCacheByUserID(HttpServletRequest request) throws AjaxException {
		Long userid = Util.getParameterLong(request,"userID");
		ajaxComponent<MenuTreeComponent> ajaxComponent = new ajaxComponent<MenuTreeComponent>();
		iSystemService.findClsAllPerms();
		iSystemService.findClsPermsByUserId(userid);
		iSystemService.findClsMenuTreeByUser(userid);
		ajaxComponent.setMsg("按用户缓存Perms清除完成");
		return ajaxComponent;
	}

	/**
	 * 清除所有用户授权的缓存
	 * 
	 * @param request
	 * @return
	 * @throws AjaxException
	 */
	@ResponseBody
	@RequestMapping(value = "/clsAllPermsCache")
	public ajaxComponent<MenuTreeComponent> clsPermsAllCache(HttpServletRequest request) throws AjaxException {
		ajaxComponent<MenuTreeComponent> ajaxComponent = new ajaxComponent<MenuTreeComponent>();
		String ss = iSystemService.findClsAllCache();
		ajaxComponent.setMsg(ss);
		return ajaxComponent;
	}

}
