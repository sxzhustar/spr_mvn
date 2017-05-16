package org.bluedon.lightweb.frame.dao.impl;

import java.util.List;

import org.bluedon.lightweb.frame.dao.ISysRoleDao;
import org.bluedon.lightweb.frame.entity.SysRole;
import org.springframework.stereotype.Repository;

@Repository
public class SysRoleDaoImpl extends BaseDao<SysRole> implements ISysRoleDao {

	public List<SysRole> findRolesByUserId(Integer id){
		String sql = "select r.* from t_sys_role r left join t_sys_user_role u on r.role_type=u.role_id "
				+ "where u.user_id="+id;
		List<SysRole> list = findWithSql(sql, null, SysRole.class);
		return list;
	}
}
