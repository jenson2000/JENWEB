package com.jen.sen.service.system.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jen.sen.commons.BeanConstant;
import com.jen.sen.commons.Constant;
import com.jen.sen.exception.AjaxException;
import com.jen.sen.persistence.core.dao.tools.PageDT;
import com.jen.sen.persistence.dao.system.IUserDao;
import com.jen.sen.persistence.pojo.system.TUser;
import com.jen.sen.service.system.user.IUserService;
import com.jen.sen.web.vo.system.UserVo;

@Service(value = BeanConstant.I_USER_SERVICE)

public class UserServiceImpl implements IUserService {
	@Resource
	private IUserDao iUserDaoimpl;

	@Override
	@Cacheable(value = "sysuser", key = "'UserServiceImpl_' + #userid")
	public UserVo findUser(Long userid) throws AjaxException {
		UserVo userVo = new UserVo();
		TUser tu = new TUser();
		tu = iUserDaoimpl.findById(TUser.class, userid);
		userVo.toVo(tu);

		return userVo;
	}

	@Override
	public List<UserVo> findUser() throws AjaxException {
		List<UserVo> userVoList = new ArrayList<UserVo>();
		List<TUser> list = iUserDaoimpl.findAll(TUser.class);
		for (TUser tu : list) {
			UserVo userVo = new UserVo();
			userVo.toVo(tu);
			userVoList.add(userVo);
		}

		return userVoList;
	}

	@Override
	public List<UserVo> findUserPage(Map<String, Object> parms, PageDT page) throws AjaxException {

		List<TUser> ls = iUserDaoimpl.queryPage(parms, page);

		List<UserVo> VoList = new ArrayList<UserVo>();
		for (TUser ts : ls) {
			UserVo uv = new UserVo();
			uv.toVo(ts);
			VoList.add(uv);
		}

		return VoList;
	}

	@Override
	public int findUserPageCount(Map<String, Object> parms) throws AjaxException {
		int count = iUserDaoimpl.queryUserCount(parms);
		return count;
	}

	@Override
	@CacheEvict(value = "sysuser", allEntries = true)
	public void updateUserState(String ids) throws AjaxException {
		iUserDaoimpl.updateUserstate(ids);

	}

	@Override
	@CachePut(value = "sysuser", key = "'UserServiceImpl_' + #vo.userId")
	public void saveOrupdateUser(UserVo vo) throws Exception {
		iUserDaoimpl.saveOrupdateUser(vo.toPojo());
	}

}
