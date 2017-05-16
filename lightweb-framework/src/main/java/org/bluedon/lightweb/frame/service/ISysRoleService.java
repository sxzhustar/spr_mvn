package org.bluedon.lightweb.frame.service;

import java.util.List;

import org.bluedon.lightweb.frame.entity.SysRole;
import org.bluedon.lightweb.frame.entity.SysRoleResource;

public interface ISysRoleService extends IBaseService<SysRole>{

	public void setRoleResources(SysRoleResource[] resources);
	
	public List<String> findRolesByUserId(Integer id);
}
