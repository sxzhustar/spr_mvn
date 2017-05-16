package org.bluedon.lightweb.frame.dao;

import java.util.List;
import java.util.Map;

import org.bluedon.lightweb.common.entity.utils.PageHelper;
import org.bluedon.lightweb.frame.entity.SysUser;

public interface ISysUserDao extends IBaseDao<SysUser>{
	
	public List<SysUser> getAllUsers();
	
	public SysUser getUserByName(String userName);
	
	public List<SysUser> findListByPage(PageHelper page);
	
	public boolean findIsExist(Map<String, Object> map);
}
