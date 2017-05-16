package org.bluedon.lightweb.frame.dao;

import java.util.List;
import java.util.Map;

import org.bluedon.lightweb.frame.entity.SysResource;
import org.bluedon.lightweb.frame.entity.SysUser;

public interface ISysResourceDao extends IBaseDao<SysResource>{

	public List<SysResource> findByUser(SysUser user);
	
	public List<Map<String, Object>> findAuthorizationAll(Integer roleId);
	
	public List<SysResource> findAuthorizationOnly(Integer roleId,Integer pId);
	
	public SysResource selectByPrimaryKey(Integer id);
	
	public List<SysResource> selectListByParentId(Integer pId);
	
	public Integer deleteByIds(List<Integer> ids);
	
	public Integer deleteById(Integer id);
	
	public void saveAndCreateRes(SysResource po,boolean createButton);
 	//生成按钮资源
 	public void createButtonRes (SysResource po);
 	
 	public Long selectResourceReference(Integer resourceId);
}
