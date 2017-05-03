package org.bluedon.lightweb.frame.dao.impl;

import java.util.List;

import org.bluedon.lightweb.frame.dao.IUserDao;
import org.bluedon.lightweb.frame.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends BaseDao<SysUser> implements IUserDao {

	@Override
	public List<SysUser> getAllUsers() {
		return find(SysUser.class, null);
	}
	
	@Override
	public SysUser getUserByName(String userName) {
		SysUser user = null;
		Object[] param = {userName};
		List<SysUser> list = find("from SysUser where account = ?", param);
		if(list != null && list.size() > 0){
			user = list.get(0);
		}
		return user;
	}

	

}
