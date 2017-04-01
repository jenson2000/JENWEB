package com.jen.sen.persistence.dao.system.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.jen.sen.commons.BeanConstant;
import com.jen.sen.persistence.core.dao.impl.FrameCenterDaoImpl;
import com.jen.sen.persistence.core.dao.tools.PageDT;
import com.jen.sen.persistence.dao.system.IUserDao;
import com.jen.sen.persistence.pojo.system.TUser;
import com.jen.sen.untils.Util;

/**
 * 
 * filename UserDaoImpl.java company jen
 * 
 * @author jenson
 * @email jenson2000@sina.com
 */

@Repository(BeanConstant.I_USER_DAO)
public class UserDaoImpl extends FrameCenterDaoImpl<TUser> implements IUserDao {

	@Override
	public List<TUser> queryPage(Map<String, Object> parms, PageDT page) {
		List<Object> values = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select * from t_user where 1=1 ");

		if (!Util.isNullOrEmpty(parms.get("userAccount"))) {
			sql.append(" and user_account like ? ");
			values.add("%" + parms.get("userAccount") + "%");
		}
		if (!Util.isNullOrEmpty(parms.get("userName"))) {
			sql.append(" and user_name like ? ");
			values.add("%" + parms.get("userName") + "%");
		}
		if (!Util.isNullOrEmpty(parms.get("screatetime"))) {
			sql.append(" and create_time >= ? ");
			values.add("" + parms.get("screatetime"));
		}
		if (!Util.isNullOrEmpty(parms.get("ecreatetime"))) {
			sql.append(" and create_time <= ? ");
			values.add("" + parms.get("ecreatetime"));
		}
		if (!Util.isNullOrEmpty(parms.get("userStatus"))) {
			sql.append(" and status = ? ");
			values.add("" + parms.get("userStatus"));
		}

		List<TUser> ls = this.queryPage(TUser.class, sql.toString(), values.toArray(), page);
		return ls;
	}

	@Override
	public int queryUserCount(Map<String, Object> parms) {
		List<Object> values = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("select count(1) from t_user where 1=1 ");
		if (!Util.isNullOrEmpty(parms.get("userAccount"))) {
			sql.append(" and user_account like ? ");
			values.add("%" + parms.get("userAccount") + "%");
		}
		if (!Util.isNullOrEmpty(parms.get("userName"))) {
			sql.append(" and user_name like ? ");
			values.add("%" + parms.get("userName") + "%");
		}
		if (!Util.isNullOrEmpty(parms.get("screatetime"))) {
			sql.append(" and create_time >= ? ");
			values.add("" + parms.get("screatetime"));
		}
		if (!Util.isNullOrEmpty(parms.get("ecreatetime"))) {
			sql.append(" and create_time <= ? ");
			values.add("" + parms.get("ecreatetime"));
		}
		if (!Util.isNullOrEmpty(parms.get("userStatus"))) {
			sql.append(" and status = ? ");
			values.add("" + parms.get("userStatus"));
		}

		int count = this.queryPageCountParas(sql.toString(), values.toArray());
		return count;
	}

	@Override
	public void updateUserstate(String ids) {
		String hql = "update TUser t set t.status=0 where t.userId IN (:ids)";
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);

		query.setParameterList("ids", Util.String2Object(ids));
		query.executeUpdate();
	}

	@Override
	public void saveOrupdateUser(TUser tu) {
		this.saveOrUpdate(tu);
	}

}
