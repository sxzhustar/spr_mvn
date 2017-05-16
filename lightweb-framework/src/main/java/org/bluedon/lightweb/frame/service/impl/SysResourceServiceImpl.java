package org.bluedon.lightweb.frame.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.bluedon.lightweb.common.entity.utils.PageHelper;
import org.bluedon.lightweb.common.utils.common.ClassPropertiesMapUtil;
import org.bluedon.lightweb.frame.dao.ISysResourceDao;
import org.bluedon.lightweb.frame.entity.SysResource;
import org.bluedon.lightweb.frame.entity.SysUser;
import org.bluedon.lightweb.frame.service.ISysResourceService;
import org.bluedon.lightweb.frame.shiro.ShiroUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Transactional
@Service("resourceService")
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements ISysResourceService {

	@Resource(name="resourceDao")
	private ISysResourceDao resourceDao;
	
	@Override
	public List<SysResource> findByUser(SysUser user) {
		return resourceDao.findByUser(user);
	}

	@Override
	public List<SysResource> findListByPage(PageHelper page,SysResource vo) {
		Map<String, Object> map = ClassPropertiesMapUtil.toMap(vo);
		return resourceDao.findListByPage(SysResource.class, map, page);
	}

	@Override
	public SysResource selectByPrimaryKey(Integer id) {
		return resourceDao.selectByPrimaryKey(id);
	}

	@Override
	public Integer deleteByIds(List<Integer> ids) {
		//检查是否有引用和子节点,有则不删除
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < ids.size(); i++) {
			int id = ids.get(i);
			Long count = resourceDao.selectResourceReference(id);
			if(count < 1){
				list.add(id);
			}
		}
		return resourceDao.deleteByIds(list);
	}

	@Override
	public Integer deleteById(Integer id) {
		return resourceDao.deleteById(id);
	}

	@Override
	public List<SysResource> selectListByParentId(Integer pId) {
		return resourceDao.selectListByParentId(pId);
	}

	@Override
	public List<SysResource> findAuthorizationAll(Integer roleId) {
		List<Map<String, Object>> list = resourceDao.findAuthorizationAll(roleId);
		List<SysResource> rList = new ArrayList<>();
		if(list != null && list.size()>0){
			try {
				for(Map<String, Object> map:list){
					SysResource entity = new SysResource();
					ClassPropertiesMapUtil.toEntity(map, entity);
					rList.add(entity);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return rList;
	}

	@Override
	public void saveAndCreateRes(SysResource po, boolean createButton) {
		po.setCreateTime(new Date());
		po.setCreateUserId(ShiroUser.getUserId());
    	resourceDao.save(po);
    	if(createButton){
    		this.createButtonRes(po);
    	}
	}

	private void createButtonRes(SysResource po) {
		String permissionStr = po.getPermissionStr();
		String resName = null;
		if(!StringUtils.isEmpty(permissionStr)){
			int len = permissionStr.indexOf(":");
			if(len != -1){
				resName = permissionStr.substring(0, len);
			}
		}
		if(null == resName ){
			return;
		}
		SysResource res = new SysResource();
		res.setParentId(po.getId());
		res.setResourcePath("/");
		res.setLevel(po.getLevel() + 1);
		res.setCreateTime(new Date());
		res.setCreateUserId(ShiroUser.getUserId());
		res.setIsEnable(1);
		//添加
		res.setResourceName(po.getResourceName() + "-添加");
		res.setOrderNum(1);
		res.setPermissionStr(resName + ":add");
		res.setResourceDesc(po.getResourceName() + "添加操作");
		res.setId(null);
		res.setCreateTime(new Date());
		res.setCreateUserId(ShiroUser.getUserId());
		this.resourceDao.save(res);
		//删除
		res.setResourceName(po.getResourceName() + "-删除");
		res.setOrderNum(2);
		res.setPermissionStr(resName + ":remove");
		res.setResourceDesc(po.getResourceName() + "删除操作");
		res.setId(null);
		res.setCreateTime(new Date());
		res.setCreateUserId(ShiroUser.getUserId());
		this.resourceDao.save(res);
		//编辑
		res.setResourceName(po.getResourceName() + "-编辑");
		res.setOrderNum(3);
		res.setPermissionStr(resName + ":edit");
		res.setResourceDesc(po.getResourceName() + "编辑操作");
		res.setCreateTime(new Date());
		res.setCreateUserId(ShiroUser.getUserId());
		res.setId(null);
		this.resourceDao.save(res);
		//查看
		res.setResourceName(po.getResourceName() + "-查看");
		res.setOrderNum(4);
		res.setPermissionStr(resName + ":info");
		res.setResourceDesc(po.getResourceName() + "查看操作");
		res.setId(null);
		res.setCreateTime(new Date());
		res.setCreateUserId(ShiroUser.getUserId());
		this.resourceDao.save(res);
		
	}

	@Override
	public List<SysResource> findAuthorizationOnly(Integer roleId,Integer pId) {
		List<SysResource> list = resourceDao.findAuthorizationOnly(roleId,pId);
		return list;
	}

}
