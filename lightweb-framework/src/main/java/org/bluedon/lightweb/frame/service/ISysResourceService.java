package org.bluedon.lightweb.frame.service;

import java.util.List;

import org.bluedon.lightweb.frame.entity.SysResource;
import org.bluedon.lightweb.frame.entity.SysUser;

public interface ISysResourceService {

	public List<SysResource> findByUser(SysUser user);
}
