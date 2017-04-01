package com.jen.sen.persistence.dao.system.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jen.sen.commons.BeanConstant;
import com.jen.sen.persistence.core.dao.impl.FrameCenterDaoImpl;
import com.jen.sen.persistence.dao.system.ISystemDao;
import com.jen.sen.persistence.pojo.system.TRight;
import com.jen.sen.persistence.pojo.system.TUser;

/**
 * 
 * filename SystemDaoImpl.java company jen
 * 
 * @author jenson
 * @email jenson2000@sina.com
 */
@Repository(BeanConstant.I_SYSTEM_DAO)
public class SystemDaoImpl extends FrameCenterDaoImpl<TUser> implements ISystemDao {

	@Override
	/**
	 * 获取所有菜单
	 */
	public List<Map<String, Object>> findMenuTreeAll() {
		String sql1 = "select distinct b.right_id,b.right_code,b.right_parent_id,b.right_order,b.right_level,b.right_name,b.right_url,b.right_img,b.perms "
				+ " from t_right b where b.status=1  ";

		return this.findSqllistMap(sql1, null);
	}

	@Override
	/**
	 * 根据用户获取菜单
	 */
	public List<Map<String, Object>> findMenuTreeByUser(Long userId) {
		String sql1 = "select distinct b.right_id,b.right_code,b.right_parent_id,b.right_order,b.right_level,b.right_name,b.right_url,b.right_img "
				+ " from t_role_right a,t_right b,t_user_roles c,t_roles d where a.right_id=b.right_id and c.role_id=a.role_id"
				+ " and c.role_id=d.role_id and b.status=1 and d.status=1 and c.user_id=?";

		return this.findSqllistMap(sql1, new Object[] { userId });
	}

	@Override
	public TUser findByUserName(String userName) {
		String sql = "select * from t_user where user_account='" + userName + "' ";
		return (TUser) this.getCurrentSession().createSQLQuery(sql).addEntity(TUser.class).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> findAllPerms() {
		String sql = "select distinct(a.perms) from t_right a where a.perms is not null and a.perms!=''";
		Set<String> permsSet = new HashSet<String>();

		List<String> ls = this.getCurrentSession().createSQLQuery(sql).list();

		for (String pe : ls) {
			permsSet.add(pe);
		}
		return permsSet;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<String> findPermsByUserId(Long Userid) {
		String sql = "select distinct(a.perms) from t_right a,t_role_right b,t_user_roles c "
				+ " where a.right_id=b.right_id and b.role_id=c.role_id and a.perms is not null and a.perms!='' and c.user_id="
				+ Userid;
		Set<String> permsSet = new HashSet<String>();

		List<String> ls = this.getCurrentSession().createSQLQuery(sql).list();

		for (String pe : ls) {
			permsSet.add(pe);
		}
		return permsSet;

	}

}
