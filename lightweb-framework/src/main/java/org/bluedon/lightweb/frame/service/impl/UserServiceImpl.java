package org.bluedon.lightweb.frame.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.bluedon.lightweb.frame.dao.IUserDao;
import org.bluedon.lightweb.frame.entity.SysUser;
import org.bluedon.lightweb.frame.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<SysUser> implements IUserService {

	@Resource
	private IUserDao userDao;
	
	public List<SysUser> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public SysUser getUserByName(String userName) {
		return userDao.getUserByName(userName);
	}
	

}
