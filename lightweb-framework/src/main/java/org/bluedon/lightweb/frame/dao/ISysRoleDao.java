package org.bluedon.lightweb.frame.dao;

import java.util.List;

import org.bluedon.lightweb.frame.entity.SysRole;

public interface ISysRoleDao extends IBaseDao<SysRole> {

	public List<SysRole> findRolesByUserId(Integer id);
}
