package org.bluedon.lightweb.frame.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bluedon.lightweb.common.entity.utils.PageHelper;
import org.bluedon.lightweb.frame.dao.ISysUserDao;
import org.bluedon.lightweb.frame.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class SysUserDaoImpl extends BaseDao<SysUser> implements ISysUserDao {

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

	@Override
	public List<SysUser> findListByPage(PageHelper page) {
		return find("from SysUser", new Object[]{}, page);
	}

	@Override
	public boolean findIsExist(Map<String, Object> map) {
		StringBuilder builder = new StringBuilder("from SysUser where 1 = 1 ");
		for (Entry<String, Object> entry : map.entrySet()) {
			builder.append("and ").append(entry.getKey() + "=" + entry.getValue());
		}
		String hql = builder.toString();
		List<SysUser> list = find(hql);
		if(list == null || list.size()<1) 
			return false;
		else 
			return true;
	}

	

}
