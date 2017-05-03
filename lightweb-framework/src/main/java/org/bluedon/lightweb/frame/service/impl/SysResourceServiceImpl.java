package org.bluedon.lightweb.frame.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.bluedon.lightweb.frame.dao.ISysResourceDao;
import org.bluedon.lightweb.frame.entity.SysResource;
import org.bluedon.lightweb.frame.entity.SysUser;
import org.bluedon.lightweb.frame.service.ISysResourceService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("resourceService")
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements ISysResourceService {

	@Resource(name="resourceDao")
	private ISysResourceDao resourceDao;
	
	@Override
	public List<SysResource> findByUser(SysUser user) {
		return resourceDao.findByUser(user);
	}

}
