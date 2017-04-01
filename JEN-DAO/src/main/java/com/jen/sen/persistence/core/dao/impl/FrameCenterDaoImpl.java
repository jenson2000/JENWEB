package com.jen.sen.persistence.core.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.jen.sen.persistence.core.dao.FrameCenterDao;
import com.jen.sen.persistence.core.dao.tools.PageDT;
import com.jen.sen.persistence.pojo.system.TUser;

/**
 * 数据库DAO公共类实现。
 * filename FrameCenterDaoImpl.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
public class FrameCenterDaoImpl<T> extends HibernateDaoSupport implements FrameCenterDao<T> {
	/** log工具实例 */
	private static final Logger log = LogManager.getLogger(FrameCenterDaoImpl.class);

	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public void save(T domain) {
		log.trace("saving " + domain.getClass().getName() + " instance");
		getHibernateTemplate().save(domain);
		log.trace("saving sucessfull" + domain.getClass().getName() + " instance");
	};

	public void saveOrUpdate(T domain) {
		log.trace("saveOrUpdating " + domain.getClass().getName() + " instance");
		getHibernateTemplate().saveOrUpdate(domain);
		log.trace("saveOrUpdating sucessfull " + domain.getClass().getName() + " instance");
	};

	public void update(T domain) {
		log.trace("updating " + domain.getClass().getName() + " instance");
		getHibernateTemplate().update(domain);
		log.trace("updating sucessfull " + domain.getClass().getName() + " instance");
	};

	public void delete(T domain) {
		log.trace("deleting " + getClass().getName() + " instance");
		getHibernateTemplate().delete(domain);
		log.trace("deleting sucessfull " + getClass().getName() + " instance");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T get(Class classType, Long id) {
		log.trace("getting " + classType.getName() + " instance");
		T t = (T) getHibernateTemplate().get(classType, id);
		log.trace("getting sucessfull " + classType.getName() + " instance");
		return t;
	};

	/**
	 * page 翻页类,page.getStart() 开始记录数,page.getLength()每页记录数
	 * 前提是记录数要先查出来,Start,RecordsFiltered要先赋值
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryPage(Class<T> clas, String sql, Object[] paras, PageDT page) {
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql).addEntity(clas);
		 
		if (paras != null && paras.length > 0) {
			int i = 0;
			for (Object tempPara : paras) {
				query.setParameter(i++, tempPara);
			}
		}		
 
		if (page != null) {
			if (page.getLength() <= -1) {
				query.setFirstResult(0);
				query.setMaxResults(page.getRecordsFiltered() - 1);
			} else {
				query.setFirstResult(page.getStart());
				if ((page.getRecordsFiltered() - page.getStart()) < page.getLength() && (page.getRecordsFiltered() - page.getStart() > -1)) {
					query.setMaxResults(page.getRecordsFiltered() - page.getStart());
				} else {
					query.setMaxResults(page.getLength());
				}
			}
		}
		return query.list();

	}

	@SuppressWarnings("unchecked")
	public List<T> query(Class<T> clas, String sql, Object[] paras) {
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql).addEntity(clas);
		if (paras != null && paras.length > 0) {
			int i = 0;
			for (Object tempPara : paras) {
				query.setParameter(i++, tempPara);
			}
		}
		return query.list();

	}

	@SuppressWarnings({ "unchecked" })
	public T load(@SuppressWarnings("rawtypes") Class classType, Long id) {
		return ((T) getCurrentSession().load(classType, id));
	}

	@Override
	public <T> T findById(Class<T> entityClass, Long id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	public void exeHql(String hql, Object[] objects) {
		Query query = this.getCurrentSession().createQuery(hql);
		for (int i = 0; i < objects.length; i++) {
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}
	
	/**
	 * 按sql语句执行update
	 */
	public void exeSql(String sql, Object[] paras) {
		
		SQLQuery query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if (paras != null && paras.length > 0) {
			int i = 0;
			for (Object tempPara : paras) {
				query.setParameter(i++, tempPara);
			}
		}
		query.executeUpdate();
		
	}

	/**
	 * 按条件查询sql的记录数
	 */
	public Integer queryPageCountParas(String sql, Object[] paras) {
		log.trace("countListByHqlAndParas " + getClass().getName());
		SQLQuery query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		if (paras != null && paras.length > 0) {
			int i = 0;
			for (Object tempPara : paras) {
				query.setParameter(i++, tempPara);
			}
		}
		log.trace("countListByHqlAndParas sucessfull " + getClass().getName());
		return Integer.parseInt(query.uniqueResult().toString());
	}

	/**
	 * 按条件查询sql的记录数
	 */

	@SuppressWarnings({ "unchecked", "hiding" })
	@Override
	public <T> List<T> findAll(Class<T> entityClass) {
		return getCurrentSession().createCriteria(entityClass).list();
	}
	
	/**sql查询
	 * 返回值，List<Map<String, Object>>
	 * 
	 * @author jenson
	 * */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findSqllistMap(String sql, Object[] objects){

		SQLQuery query =this.getCurrentSession().createSQLQuery(sql);

		if (objects != null && objects.length > 0) {

			int i = 0;
			for (Object tempPara : objects) {
				query.setParameter(i++, tempPara);
			}
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	
	@SuppressWarnings({ "unchecked" })
	public T getbysql(Class<T> clas,String sql) {
		SQLQuery query =this.getCurrentSession().createSQLQuery(sql);
		return (T) query.addEntity(clas).uniqueResult();

	};

	protected Session getCurrentSession() {
		return super.getHibernateTemplate().getSessionFactory().getCurrentSession();
	}
}
