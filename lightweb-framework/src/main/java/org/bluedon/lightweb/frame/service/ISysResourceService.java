package org.bluedon.lightweb.frame.service;

import java.util.List;

import org.bluedon.lightweb.common.entity.utils.PageHelper;
import org.bluedon.lightweb.frame.entity.SysResource;
import org.bluedon.lightweb.frame.entity.SysUser;

public interface ISysResourceService extends IBaseService<SysResource> {

	public List<SysResource> findByUser(SysUser user);
	
	public List<SysResource> findListByPage(PageHelper page,SysResource vo);
	
	public List<SysResource> findAuthorizationAll(Integer roleId);
	
	public List<SysResource> findAuthorizationOnly(Integer roleId,Integer pId);
	
	public SysResource selectByPrimaryKey(Integer id);
	
	public List<SysResource> selectListByParentId(Integer pId);
	
	public Integer deleteByIds(List<Integer> ids);
	
	public Integer deleteById(Integer id);
	
	public void saveAndCreateRes(SysResource po,boolean createButton);
}
