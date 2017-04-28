package org.bluedon.lightweb.frame.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.bluedon.lightweb.common.entity.utils.PageHelper;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository("baseDao")
public class BaseDao<T> {

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

	public void update(T o) {
		getCurrentSession().update(o);
	}

	public void saveOrUpdate(T o) {
		getCurrentSession().saveOrUpdate(o);
	}

	protected List<T> find(String hql) {
		return getCurrentSession().createQuery(hql).list();
	}

	protected List<T> find(String hql, Object[] param) {
		Query query = getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		return query.list();
	}
	
	protected List<T> findWithSql(String sql, Object[] param) {
		Query query = getCurrentSession().createSQLQuery(sql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	protected List<T> find(String hql, List<Object> param) {
		Query query = getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i, param.get(i));
			}
		}
		return query.list();
	}

	protected List<T> find(String hql, Object[] param, Integer page, Integer rows) {
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

	protected List<T> find(String hql, List<Object> param, Integer page, Integer rows) {
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

	protected List<T> find(String hql, Object[] param, PageHelper page) {
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

	protected List<T> find(String hql, List<Object> param, PageHelper page) {
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

	protected List<T> find(Class<T> cls, List<Criterion> list) {
		Criteria criteria = getCurrentSession().createCriteria(cls);
		if (list != null && list.size() > 0) {
			for (Criterion c : list) {
				criteria.add(c);
			}
		}
		return (List<T>) criteria.list();
	}

	protected List<T> find(Class<T> cls, List<Criterion> list, Integer page, Integer rows) {
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

	protected T get(Class<T> c, Serializable id) {
		return (T) getCurrentSession().get(c, id);
	}

	protected T get(String hql, Object[] param) {
		List<T> list = find(hql, param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	protected T get(String hql, List<Object> param) {
		List<T> list = find(hql, param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	protected Long count(String hql) {
		return (Long) getCurrentSession().createQuery(hql).uniqueResult();
	}

	protected Long count(String hql, Object[] param) {
		Query query = getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		return (Long) query.uniqueResult();
	}

	protected Long count(String hql, List<Object> param) {
		Query query = getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i, param.get(i));
			}
		}
		return (Long) query.uniqueResult();
	}

}
