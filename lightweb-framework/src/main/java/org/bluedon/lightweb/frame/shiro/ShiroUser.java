package org.bluedon.lightweb.frame.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.bluedon.lightweb.frame.entity.SysUser;

public class ShiroUser {

	/**
	 * 超级管理员角色
	 */
	private static final Integer ROLE_TYPE_SUPERADMIN = 1;
	/**
	 * 管理员
	 */
	private static final Integer ROLE_TYPE_ADMIN = 2;
	/**
	 * 发布方
	 */
	private static final Integer ROLE_TYPE_PUBLISHER = 3;
	/**
	 * 普通成员
	 */
	private static final Integer ROLE_TYPE_MEMBER = 4;
	
	/**
	 * 获取用户
	 * @return
	 */
	public static SysUser getUser() {
		Subject subject = SecurityUtils.getSubject();
		SysUser user = (SysUser)subject.getPrincipal();
		return user;
	}
	
	/**
	 * 获取用户ID
	 * @return
	 */
	public static Integer getUserId() {
		SysUser user = getUser();
		if(user != null){
			return user.getId();
		}
		return null;
	}
	
	/**
	 * 获取角色类型
	 * @return
	 */
	public static Integer getRoleType() {
		Integer roleType;
		Subject subject = SecurityUtils.getSubject();
		if(subject.hasRole("1")){
			roleType = ROLE_TYPE_SUPERADMIN;
		}else if(subject.hasRole("2")){
			roleType = ROLE_TYPE_ADMIN;
		}else if(subject.hasRole("3")){
			roleType = ROLE_TYPE_PUBLISHER;
		}else{
			roleType = ROLE_TYPE_MEMBER;
		}
		return roleType;
	}
	
	public static boolean isSuperAdmin() {
		return getRoleType() == 1 ? true : false;
	}
}
