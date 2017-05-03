package org.bluedon.lightweb.frame.service;

import java.util.List;

import org.bluedon.lightweb.frame.entity.SysUser;

public interface IUserService{

	public List<SysUser> getAllUsers();
	
	public SysUser getUserByName(String name);
	
}
