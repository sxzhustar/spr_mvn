package org.bluedon.lightweb.frame.dao;

import java.util.List;

import org.bluedon.lightweb.frame.entity.SysResource;
import org.bluedon.lightweb.frame.entity.SysUser;

public interface ISysResourceDao{

	public List<SysResource> findByUser(SysUser user);
}
