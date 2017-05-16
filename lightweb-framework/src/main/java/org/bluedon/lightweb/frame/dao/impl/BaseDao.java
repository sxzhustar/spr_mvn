package org.bluedon.lightweb.frame.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.bluedon.lightweb.common.entity.utils.PageHelper;
import org.bluedon.lightweb.frame.dao.IBaseDao;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository("baseDao")
public class BaseDao<T> implements IBaseDao<T> {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Serializable save(T o) {
		return getCurrentSession().save(o);
	}

	public void delete(T o) {
		getCurrentSession().delete(o);
	}
	
	public Integer deleteIds(String entity,String sids) {
		String[] ids = sids.split(",");
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<ids.length;i++){
			builder.append(Integer.valueOf(ids[i])).append(",");
		}
		builder.deleteCharAt(builder.capacity());
		String where = builder.toString();
		String hql = "delete from ? where id in(?)";
		Integer count = executeHql(hql, new Object[]{entity,where});
		return count;
	}

	public void update(T o) {
		getCurrentSession().update(o);
	}

	public void saveOrUpdate(T o) {
		getCurrentSession().saveOrUpdate(o);
	}

	public List<T> find(String hql) {
		return getCurrentSession().createQuery(hql).list();
	}

	public List<T> find(String hql, Object[] param) {
		Query query = getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		return query.list();
	}
	
	public List<T> findWithSql(String sql, Object[] param,Class<T> cls) {
		List<T> list = null;
		try {
			Query query = getCurrentSession().createSQLQuery(sql).addEntity(cls);
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					query.setParameter(i, param[i]);
				}
			}
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Map<String, Object>> findWithSqlMulTbMulFields(String sql, Object[] param) {
		List<Map<String, Object>> list = null;
		try {
			SQLQuery query = getCurrentSession().createSQLQuery(sql);
			if (param != null && param.length > 0) {
				for (int i = 0; i < param.length; i++) {
					query.setParameter(i, param[i]);
				}
			}
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<T> find(String hql, List<Object> param) {
		Query query = getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i, param.get(i));
			}
		}
		return query.list();
	}

	public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query query = getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		return query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public List<T> find(String hql, List<Object> param, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query query = getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i, param.get(i));
			}
		}
		return query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	public List<T> find(String hql, Object[] param, PageHelper page) {
		Query query = getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		query.setFirstResult(page.getFirstResult());
		query.setMaxResults(page.getMaxResults());
		return query.list();
	}

	public List<T> find(String hql, List<Object> param, PageHelper page) {
		Query query = getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i, param.get(i));
			}
		}
		query.setFirstResult(page.getFirstResult());
		query.setMaxResults(page.getMaxResults());
		return query.list();
	}

	public List<T> find(Class<T> cls, List<Criterion> list) {
		Criteria criteria = getCurrentSession().createCriteria(cls);
		if (list != null && list.size() > 0) {
			for (Criterion c : list) {
				criteria.add(c);
			}
		}
		return (List<T>) criteria.list();
	}

	public List<T> find(Class<T> cls, List<Criterion> list, Integer page, Integer rows) {
		Criteria criteria = getCurrentSession().createCriteria(cls);
		if (list != null && list.size() > 0) {
			for (Criterion c : list) {
				criteria.add(c);
			}
		}
		criteria.setFirstResult((page - 1) * rows);
		criteria.setMaxResults(rows);
		return (List<T>) criteria.list();
	}
	
	public List<T> find(Class<T> cls, List<Criterion> list, PageHelper page) {
		Criteria criteria = getCurrentSession().createCriteria(cls);
		if (list != null && list.size() > 0) {
			for (Criterion c : list) {
				criteria.add(c);
			}
		}
		criteria.setFirstResult(page.getFirstResult());
		criteria.setMaxResults(page.getMaxResults());
		return (List<T>) criteria.list();
	}

	public T get(Class<T> c, Serializable id) {
		return (T) getCurrentSession().get(c, id);
	}

	public T get(String hql, Object[] param) {
		List<T> list = find(hql, param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public T get(String hql, List<Object> param) {
		List<T> list = find(hql, param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public Long count(String hql) {
		return (Long) getCurrentSession().createQuery(hql).uniqueResult();
	}

	public Long count(String hql, Object[] param) {
		Query query = getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		return (Long) query.uniqueResult();
	}
	
	public Long countSql(String hql, Object[] param) {
		Query query = getCurrentSession().createSQLQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		return ((BigDecimal)query.uniqueResult()).longValue();
	}

	public Long count(String hql, List<Object> param) {
		Query query = getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i, param.get(i));
			}
		}
		return (Long) query.uniqueResult();
	}
	
	public Integer executeHql(String hql) {
		return this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	public Integer executeHql(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}

	public Integer executeHql(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.executeUpdate();
	}

	public T selectByPrimaryKey(Class<T> entity,String id) {
		return get(entity, id);
	}
	
	public List<T> findListByPage(Class<T> entity,Map<String, Object> map,PageHelper page) {
		Criteria criteria = getCurrentSession().createCriteria(entity);
		if(map != null){
			for (Entry<String, Object> entry : map.entrySet()) {
				Criterion criterion = Restrictions.eq(entry.getKey(), entry.getValue());
				criteria.add(criterion);
			}
		}
		if(page != null){
			criteria.setFirstResult(page.getFirstResult());
			criteria.setMaxResults(page.getMaxResults());
		}
		return criteria.list();
	}

	public List<T> selectByEntry(Class<T> entity, Map<String, Object> map) {
		List<T> list = findListByPage(entity, map, null);
		return list;
	}

}
