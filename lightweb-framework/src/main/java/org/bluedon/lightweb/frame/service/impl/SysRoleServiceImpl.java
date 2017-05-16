package org.bluedon.lightweb.frame.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bluedon.lightweb.frame.dao.ISysRoleDao;
import org.bluedon.lightweb.frame.dao.ISysRoleResourceDao;
import org.bluedon.lightweb.frame.entity.SysRole;
import org.bluedon.lightweb.frame.entity.SysRoleResource;
import org.bluedon.lightweb.frame.service.ISysRoleService;
import org.bluedon.lightweb.frame.shiro.UserRealmImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements ISysRoleService {

	@Autowired
	private ISysRoleResourceDao roleResourceDao;
	
	@Autowired
	private UserRealmImpl customRealm;
	
	@Autowired
	private ISysRoleDao roleDao;
	
	@Override
	public void setRoleResources(SysRoleResource[] resources) {
		if (null != resources && resources.length > 0) {
			Integer roleId = resources[0].getRoleId();
			roleResourceDao.deleteByRoleId(roleId);
			for (SysRoleResource roleResource : resources) {
				roleResourceDao.save(roleResource);
			}
			customRealm.clearCached();
		}
	}

	@Override
	public List<String> findRolesByUserId(Integer id) {
		List<SysRole> list = roleDao.findRolesByUserId(id);
		List<String> rList = new ArrayList<>();
		if(list != null){
			for (SysRole role : list) {
				rList.add(role.getRoleType().toString());
			}
		}
		return rList;
	}
}
