package org.bluedon.lightweb.frame.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.bluedon.lightweb.common.entity.utils.PageHelper;
import org.hibernate.criterion.Criterion;

public interface IBaseDao<T> {

	public Serializable save(T o);
	
	public void delete(T o);
	
	public Integer deleteIds(String entity,String ids);
	
	public void update(T o);
	
	public void saveOrUpdate(T o);
	
	public List<T> find(String hql);
	
	public List<T> find(String hql,Object[] param);
	
	public List<T> find(String hql,List<Object> param);
	
	public List<T> find(String hql,Object[] param,Integer page,Integer rows);
	
	public List<T> find(String hql,List<Object> param,Integer page,Integer rows);
	
	public List<T> find(String hql,Object[] param,PageHelper page); 
	
	public List<T> find(String hql,List<Object> param,PageHelper page);
	
	public List<T> find(Class<T> cls,List<Criterion> list);
	
	public List<T> find(Class<T> cls,List<Criterion> list,Integer page,Integer rows);
	
	public T get(Class<T> c,Serializable id);
	
	public T get(String hql,Object[] param);
	
	public T get(String hql,List<Object> param);
	
	public Long count(String hql);
	
	public Long count(String hql,Object[] param);
	
	public Long count(String hql,List<Object> param);
	
	public T selectByPrimaryKey(Class<T> entity,String id);
	
	public List<T> findListByPage(Class<T> entity,Map<String, Object> map,PageHelper page);
	
}
