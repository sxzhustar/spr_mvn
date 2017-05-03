package org.bluedon.lightweb.frame.dao.impl;

import java.util.List;

import org.bluedon.lightweb.frame.dao.ISysResourceDao;
import org.bluedon.lightweb.frame.entity.SysResource;
import org.bluedon.lightweb.frame.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository("resourceDao")
public class SysResourceDaoImpl extends BaseDao<SysResource> implements ISysResourceDao {

	public List<SysResource> findByUser(SysUser user){
		String hql ="SELECT s.*  FROM t_sys_resource s "
				+ "INNER JOIN t_sys_role_resource r on s.id=r.resource_id "
				+ "INNER JOIN t_sys_user_role u on u.role_id = r.role_id "
				+ "WHERE u.user_id=?";
		return findWithSql(hql,new Integer[]{user.getId()});
	}
}
