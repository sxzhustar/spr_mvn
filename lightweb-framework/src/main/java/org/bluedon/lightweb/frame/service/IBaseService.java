package org.bluedon.lightweb.frame.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.bluedon.lightweb.common.entity.utils.PageHelper;


public interface IBaseService<T> {

	public Serializable save(T t);
	
	public void delete(T t);
	
	public Integer deleteIds(String entity,String ids);
	
	public void update(T t);
	
	public void saveOrUpdate(T t);
	
	public T selectByPrimaryKey(Class<T> entity,String id);
	
	public List<T> selectByEntry(Class<T> entity,Map<String, Object> map);
	
	public List<T> findListByPage(Class<T> entity,Map<String, Object> map,PageHelper page);
	
	public List<T> findListAll(Class<T> entity);
}
