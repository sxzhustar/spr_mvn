package org.bluedon.lightweb.frame.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bluedon.lightweb.common.entity.utils.PageHelper;
import org.bluedon.lightweb.frame.dao.impl.BaseDao;
import org.bluedon.lightweb.frame.service.IBaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("baseService")
@Transactional
public class BaseServiceImpl<T> implements IBaseService<T>{

	@Resource(name="baseDao")
	protected BaseDao<T> dao;
	
	@Override
	public Serializable save(T o) {
		return dao.save(o);
	}

	@Override
	public void delete(T o) {
		dao.delete(o);
	}

	@Override
	public void update(T o) {
		dao.update(o);
	}

	@Override
	public void saveOrUpdate(T o) {
		dao.saveOrUpdate(o);
	}

	@Override
	public Integer deleteIds(String entity, String ids) {
		return dao.deleteIds(entity, ids);
	}

	@Override
	public T selectByPrimaryKey(Class<T> entity,String id) {
		return dao.selectByPrimaryKey(entity, id);
	}

	@Override
	public List<T> findListByPage(Class<T> entity,Map<String, Object> map,PageHelper page) {
		return dao.findListByPage(entity,map, page);
	}

	@Override
	public List<T> selectByEntry(Class<T> entity, Map<String, Object> map) {
		return dao.selectByEntry(entity, map);
	}

	@Override
	public List<T> findListAll(Class<T> cls) {
		return dao.find(cls, null);
	}

}
