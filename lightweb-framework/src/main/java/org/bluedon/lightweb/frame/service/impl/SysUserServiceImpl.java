package org.bluedon.lightweb.frame.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bluedon.lightweb.common.entity.utils.PageHelper;
import org.bluedon.lightweb.frame.dao.ISysUserDao;
import org.bluedon.lightweb.frame.entity.SysUser;
import org.bluedon.lightweb.frame.service.ISysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements ISysUserService {

	@Resource
	private ISysUserDao userDao;
	
	public List<SysUser> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public SysUser getUserByName(String userName) {
		return userDao.getUserByName(userName);
	}

	@Override
	public List<SysUser> findListByPage(PageHelper page) {
		return userDao.findListByPage(page);
	}

	@Override
	public boolean findIsExist(String name, String type, String id) {
		Map<String, Object> map = new HashMap<>();
		name = "'" + name + "'";
		if(type.equals(SysUser.CHECK_TYPE_ACCOUNT)){
			map.put("account", name);
		}else if(type.equalsIgnoreCase(SysUser.CHECK_TYPE_EMAIL)){
			map.put("email", name);
		}else if(type.equalsIgnoreCase(SysUser.CHECK_TYPE_IDNUMBER)){
			map.put("idNumber", name);
		}else if(type.equalsIgnoreCase(SysUser.CHECK_TYPE_MOBILEPHONE)){
			map.put("mobilePhone", name);
		}
		if(id != null){
			map.put("id", id);
		}
		return userDao.findIsExist(map);
	}
	

}
