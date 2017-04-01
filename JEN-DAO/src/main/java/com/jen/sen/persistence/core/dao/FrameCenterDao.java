package com.jen.sen.persistence.core.dao;

import java.util.List;
import java.util.Map;

import com.jen.sen.persistence.core.dao.tools.PageDT;

/**
 * 数据库DAO公共类接口。
 * filename FrameCenterDao.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
public interface FrameCenterDao<T> {

	public abstract void save(T domain);
	
	public abstract void saveOrUpdate(T domain);

	public abstract void update(T domain);

	public abstract void delete(T domain);
	
	/**
	 * 
	 * desc：get方式查询，执行hsql语句，根据主键返回对象
	 * @param classType
	 * @param id
	 * @return T
	 */
	@SuppressWarnings("rawtypes")
	public abstract T get(Class classType, Long id);
	
	
	
	/**
	 * 
	 * desc：根据SQL语句翻页查询返回对象。
	 * @param clas
	 * @param  sql
	 * @param  paras
	 * @param  page
	 * @return List<T>
	 */
	public List<T> queryPage(Class<T> clas,String sql,Object[] paras,PageDT page);
	
	/**
	 * 根据SQL语句查询返回对象。
	 * 
	 * @author jenson
	 * @param clas
	 * @param sql
	 * @param paras
	 * @return
	 * @since 0.1_2013-1-22
	 */
	public List<T> query(Class<T> clas, String sql, Object[] paras);

	/**
	 * load方式获取对象（延迟加载）
	 * @param classType
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public abstract T load(Class classType, Long id);
	
	/**
	 * get方式通过主键获取对象
	 * @param entityClass
	 * @param id
	 * @return
	 */
	@SuppressWarnings("hiding")
	public <T> T findById(Class<T> entityClass, Long id);

	

	/**HQL查询批量数据更新
	 * 无返回值，执行hql
	 * 
	 * @author jenson
	 * */
	public void exeHql(String hql, Object[] objects);

	/**
	 * 获取所有对象
	 * @param  entityClass
	 * @return List<T>
	 */
	@SuppressWarnings("hiding")
	public <T> List<T> findAll(Class<T> entityClass);
	
	/**
	 * sql查询
	 * @param  sql
	 * @param  objects
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String, Object>> findSqllistMap(String sql, Object[] objects);
	
	/**
	 * 条件查询总记录数
	 * @param  sql
	 * @param  paras
	 * @return Integer
	 */
	public Integer queryPageCountParas(String sql, Object[] paras);


}
