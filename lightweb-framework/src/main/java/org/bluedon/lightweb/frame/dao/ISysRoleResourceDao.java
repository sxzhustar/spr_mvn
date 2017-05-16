package org.bluedon.lightweb.frame.dao;

import org.bluedon.lightweb.frame.entity.SysRoleResource;

public interface ISysRoleResourceDao extends IBaseDao<SysRoleResource> {

	public Integer deleteByRoleId(Integer roleId);
}
