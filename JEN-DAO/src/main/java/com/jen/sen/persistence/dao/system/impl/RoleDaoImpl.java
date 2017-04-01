package com.jen.sen.persistence.dao.system.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.jen.sen.commons.BeanConstant;
import com.jen.sen.persistence.core.dao.impl.FrameCenterDaoImpl;
import com.jen.sen.persistence.core.dao.tools.PageDT;
import com.jen.sen.persistence.dao.system.IRoleDao;
import com.jen.sen.persistence.pojo.system.TRoles;
import com.jen.sen.untils.Util;

/**
 * 
 * filename RoleDaoImpl.java company jen
 * 
 * @author jenson
 * @email jenson2000@sina.com
 */

@Repository(BeanConstant.I_ROLE_DAO)
public class RoleDaoImpl extends FrameCenterDaoImpl<TRoles> implements IRoleDao {

	@Override
	public List<TRoles> queryPage(Map<String, Object> parms, PageDT page) {
		List<Object> values = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("select * from t_roles where 1=1 ");

		if (!Util.isNullOrEmpty(parms.get("roleName"))) {
			sql.append(" and role_name like ? ");
			values.add("%" + parms.get("roleName") + "%");
		}

		if (!Util.isNullOrEmpty(parms.get("roleStatus"))) {
			sql.append(" and status = ? ");
			values.add("" + parms.get("roleStatus"));
		}

		List<TRoles> ls = this.queryPage(TRoles.class, sql.toString(), values.toArray(), page);
		return ls;
	}

	@Override
	public int queryRolesCount(Map<String, Object> parms) {
		List<Object> values = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer("select count(1) from t_roles where 1=1 ");
		if (!Util.isNullOrEmpty(parms.get("roleName"))) {
			sql.append(" and role_name like ? ");
			values.add("%" + parms.get("roleName") + "%");
		}

		if (!Util.isNullOrEmpty(parms.get("roleStatus"))) {
			sql.append(" and status = ? ");
			values.add("" + parms.get("roleStatus"));
		}
		int count = this.queryPageCountParas(sql.toString(), values.toArray());
		return count;
	}

	@Override
	public void updateRolestate(String ids) {
		String hql = "update TRoles t set t.status=0 where t.roleId IN (:ids)";
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);

		query.setParameterList("ids", Util.String2Object(ids));
		query.executeUpdate();
	}

	@Override
	public void saveOrupdateRoles(TRoles tu) {
		this.saveOrUpdate(tu);
	}

	@Override
	public void saveOrupdateRoleAndRight(String rightids, TRoles tu) {
		String sql = "delete from t_role_right where role_id=?";

		String sql1 = "insert into t_role_right(role_id,right_id,create_userid,create_time,update_userid,update_time) "
				+ "values(?,?,?,now(),?,now())";

		if (Util.isNullOrEmpty(rightids)) {
			this.saveOrUpdate(tu);
			this.exeSql(sql, new Object[] { tu.getRoleId() });
		} else {
			Object stray[] = Util.String2Object(rightids);
			this.saveOrUpdate(tu);
			this.exeSql(sql, new Object[] { tu.getRoleId() });
			for (Object str : stray) {
				this.exeSql(sql1,
						new Object[] { tu.getRoleId(), str.toString(), tu.getCreateUserid(), tu.getUpdateUserid() });
			}

		}

	}

	@Override
	public List<TRoles> queryRoleByUserID(Long Userid) {
		List<Object> values = new ArrayList<Object>();

		StringBuffer sql = new StringBuffer(
				" select a.* from t_roles a ," + " t_user_roles b where a.role_id=b.role_id and b.user_id=? ");
		values.add("" + Userid);

		List<TRoles> ls = this.query(TRoles.class, sql.toString(), values.toArray());
		return ls;
	}

	@Override
	public void saveOrupdateRoleByUserId(Long userid, String ids) {
		String sql1 = "delete from t_user_roles where user_id=? ";
		this.exeSql(sql1, new Object[] { userid });

		if (ids.length() > 0) {
			Object ob[] = Util.String2Object(ids);
			for (Object id : ob) {

				String sql2 = "insert into t_user_roles(user_id,role_id,create_userid,create_time,update_userid,update_time) "
						+ " values (?,?,?,now(),?,now())";

				this.exeSql(sql2, new Object[] { userid, Long.valueOf(id.toString()), -1L, -1L });
			}
		}

	}

	@Override
	public List<Map<String, Object>> queryRightTree() {
		String sql = "select a.right_id,right_code,right_parent_id,right_level,a.right_name,a.right_url,a.perms from t_right a where a.status=1";
		return this.findSqllistMap(sql, null);
	}

	@Override
	public List<Map<String, Object>> queryRightTreeById(Long roleID) {
		String sql = "select a.right_id from t_role_right a,t_right b where a.right_id=b.right_id and b.status=1 and a.role_id=?";

		return this.findSqllistMap(sql, new Object[] { roleID });
	}

}
