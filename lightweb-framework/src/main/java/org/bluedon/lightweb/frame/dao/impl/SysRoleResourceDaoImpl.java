package org.bluedon.lightweb.frame.dao.impl;

import org.bluedon.lightweb.frame.dao.ISysRoleResourceDao;
import org.bluedon.lightweb.frame.entity.SysRoleResource;
import org.springframework.stereotype.Repository;

@Repository
public class SysRoleResourceDaoImpl extends BaseDao<SysRoleResource> implements ISysRoleResourceDao {

	@Override
	public Integer deleteByRoleId(Integer roleId) {
		String hql = "delete from SysRoleResource rs where rs.roleId = ?";
		Integer count = executeHql(hql, new Object[]{roleId});
		return count;
	}

}
