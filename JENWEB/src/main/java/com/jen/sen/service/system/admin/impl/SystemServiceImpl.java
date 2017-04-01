package com.jen.sen.service.system.admin.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jen.sen.commons.BeanConstant;
import com.jen.sen.exception.AjaxException;
import com.jen.sen.persistence.dao.system.ISystemDao;
import com.jen.sen.persistence.pojo.system.TUser;
import com.jen.sen.service.system.admin.ISystemService;
import com.jen.sen.untils.SpringBeanTools;
import com.jen.sen.untils.Util;
import com.jen.sen.web.component.MenuTreeComponent;
import com.jen.sen.web.vo.system.UserVo;

/**
 * 
 * filename SystemServiceImpl.java company jen
 * 
 * @author jenson
 * @email jenson2000@sina.com
 */
@Service(value = BeanConstant.I_SYSTEM_SERVICE)
public class SystemServiceImpl implements ISystemService {
	@Resource
	private ISystemDao iSystemDao;

	@Override
	@Cacheable(value = "sysmenu", key = "'SystemServiceImpl_' + #userId")
	public List<MenuTreeComponent> findMenuTreeByUser(Long userId) throws AjaxException {

		// 获取当前用户下面的所有菜单,(去掉上一级),已转换实体
		List<MenuTreeComponent> lssubtree = new ArrayList<MenuTreeComponent>();
		// 顶级菜单
		List<MenuTreeComponent> lsfirst = new ArrayList<MenuTreeComponent>();
		// 获取当前用户下面的所有菜单,map对象
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();

		// 最终返回的menu集合
		List<MenuTreeComponent> lsmenu = new ArrayList<MenuTreeComponent>();
		if (userId == 1) {
			ls = iSystemDao.findMenuTreeAll();
		} else {
			ls = iSystemDao.findMenuTreeByUser(userId);
		}
		for (Map<String, Object> ma : ls) {
			MenuTreeComponent treeComponent = new MenuTreeComponent();
			treeComponent.setRightId(Long.valueOf(ma.get("right_id").toString()));
			treeComponent.setRightCode(ma.get("right_code").toString());
			treeComponent.setRightParentId(Util.isNullOrEmptyToLong(
					ma.get("right_parent_id") == null ? "0" : ma.get("right_parent_id").toString()));
			treeComponent.setRightOrder(Integer.valueOf(ma.get("right_order").toString()));
			treeComponent.setRightLevel(Integer.valueOf(ma.get("right_level").toString()));
			treeComponent.setRightName(ma.get("right_name").toString());
			treeComponent.setRightUrl(
					Util.isNullToEmptyStr(ma.get("right_url") == null ? "" : ma.get("right_url").toString()));
			treeComponent.setRightImg(
					Util.isNullToEmptyStr(ma.get("right_img") == null ? "" : ma.get("right_img").toString()));
			treeComponent.setPerms(Util.isNullToEmptyStr(ma.get("perms") == null ? "" : ma.get("perms").toString()));

			// 获取顶级菜单
			if (treeComponent.getRightLevel() == 1) {
				lsfirst.add(treeComponent);
			} else {
				// 获取除顶部菜单外的菜单
				lssubtree.add(treeComponent);
			}
		}

		// 循环顶级菜单
		for (MenuTreeComponent mt : lsfirst) {
			List<MenuTreeComponent> submenuls = this.subMenu(lssubtree, mt.getRightId());
			mt.setSubMenus(submenuls);
			lsmenu.add(mt);

		}

		return lsmenu;
	}

	// 迭代获得子菜单
	public List<MenuTreeComponent> subMenu(List<MenuTreeComponent> lssubtree, Long rightid) {
		List<MenuTreeComponent> submenuls = new ArrayList<MenuTreeComponent>();
		// 获取下一级对象
		for (MenuTreeComponent mc : lssubtree) {
			if (mc.getRightParentId().equals(rightid)) {
				List<MenuTreeComponent> submenulssec = this.subMenu(lssubtree, mc.getRightId());
				mc.setSubMenus(submenulssec);
				submenuls.add(mc);
			}

		}

		return submenuls;

	}

	@Override
	@Cacheable(value = "sysmenu", key = "'SystemServiceImpl_' + #userName")
	public UserVo findByUserName(String userName) throws AjaxException {

		UserVo uv = new UserVo();
		TUser tu = iSystemDao.findByUserName(userName);
		uv.toVo(tu);
		return uv;
	}

	@Override
	@Cacheable(value = "sysmenu", key = "'SystemServiceImpl_findAllPerms'")
	public Set<String> findAllPerms() throws AjaxException {
		return iSystemDao.findAllPerms();
	}

	@Override
	@Cacheable(value = "sysmenu", key = "'SystemServiceImpl_findAllPerms_' + #Userid")
	public Set<String> findPermsByUserId(Long Userid) throws AjaxException {
		return iSystemDao.findPermsByUserId(Userid);
	}

	// 更新所有授权缓存
	@Override
	@CachePut(value = "sysmenu", key = "'SystemServiceImpl_findAllPerms'")
	public Set<String> findClsAllPerms() throws AjaxException {
		return iSystemDao.findAllPerms();
	}

	// 按用户更新授权缓存
	@Override
	@CachePut(value = "sysmenu", key = "'SystemServiceImpl_findAllPerms_' + #Userid")
	public Set<String> findClsPermsByUserId(Long Userid) throws AjaxException {
		return iSystemDao.findPermsByUserId(Userid);
	}

	@Override
	@CachePut(value = "sysmenu", key = "'SystemServiceImpl_' + #userId")
	public List<MenuTreeComponent> findClsMenuTreeByUser(Long userId) throws AjaxException {

		// 获取当前用户下面的所有菜单,(去掉上一级),已转换实体
		List<MenuTreeComponent> lssubtree = new ArrayList<MenuTreeComponent>();
		// 顶级菜单
		List<MenuTreeComponent> lsfirst = new ArrayList<MenuTreeComponent>();
		// 获取当前用户下面的所有菜单,map对象
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();

		// 最终返回的menu集合
		List<MenuTreeComponent> lsmenu = new ArrayList<MenuTreeComponent>();
		if (userId == 1) {
			ls = iSystemDao.findMenuTreeAll();
		} else {
			ls = iSystemDao.findMenuTreeByUser(userId);
		}
		for (Map<String, Object> ma : ls) {
			MenuTreeComponent treeComponent = new MenuTreeComponent();
			treeComponent.setRightId(Long.valueOf(ma.get("right_id").toString()));
			treeComponent.setRightCode(ma.get("right_code").toString());
			treeComponent.setRightParentId(Util.isNullOrEmptyToLong(
					ma.get("right_parent_id") == null ? "0" : ma.get("right_parent_id").toString()));
			treeComponent.setRightOrder(Integer.valueOf(ma.get("right_order").toString()));
			treeComponent.setRightLevel(Integer.valueOf(ma.get("right_level").toString()));
			treeComponent.setRightName(ma.get("right_name").toString());
			treeComponent.setRightUrl(
					Util.isNullToEmptyStr(ma.get("right_url") == null ? "" : ma.get("right_url").toString()));
			treeComponent.setRightImg(
					Util.isNullToEmptyStr(ma.get("right_img") == null ? "" : ma.get("right_img").toString()));
			treeComponent.setPerms(Util.isNullToEmptyStr(ma.get("perms") == null ? "" : ma.get("perms").toString()));

			// 获取顶级菜单
			if (treeComponent.getRightLevel() == 1) {
				lsfirst.add(treeComponent);
			} else {
				// 获取除顶部菜单外的菜单
				lssubtree.add(treeComponent);
			}
		}

		// 循环顶级菜单
		for (MenuTreeComponent mt : lsfirst) {
			List<MenuTreeComponent> submenuls = this.subMenu(lssubtree, mt.getRightId());
			mt.setSubMenus(submenuls);
			lsmenu.add(mt);

		}

		return lsmenu;
	}

	@Override
	@CacheEvict(value = "sysmenu", allEntries = true)
	public String findClsAllCache() throws AjaxException {
		return "所有授权缓存清除成功";
	}

}
