package org.bluedon.lightweb.frame.dao;

import java.util.List;

import org.bluedon.lightweb.frame.entity.SysUser;

public interface IUserDao{
	
	public List<SysUser> getAllUsers();
	
	public SysUser getUserByName(String userName);
}
