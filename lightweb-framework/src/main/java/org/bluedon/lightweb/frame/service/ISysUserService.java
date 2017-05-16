package org.bluedon.lightweb.frame.service;

import java.util.List;

import org.bluedon.lightweb.common.entity.utils.PageHelper;
import org.bluedon.lightweb.frame.entity.SysUser;

public interface ISysUserService extends IBaseService<SysUser>{

	public List<SysUser> getAllUsers();
	
	public SysUser getUserByName(String name);
	
	public List<SysUser> findListByPage(PageHelper page);
	
	public boolean findIsExist(String name,String type,String id);
	
}
