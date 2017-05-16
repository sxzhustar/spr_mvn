package org.bluedon.lightweb.frame.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="t_sys_user_role")
public class SysUserRole implements Serializable{

	private static final long serialVersionUID = 1L;

 	/**
 	* 主键
 	*/
	@Id
	@Column(name="id",nullable=false,unique=true)
   	private Integer id;
 	/**
 	* 用户id
 	*/
	@JoinColumn(name="user_id")
   	private Integer userId;
 	/**
 	* 角色id
 	*/
	@JoinColumn(name="role_id")
   	private Integer roleId;


   	public void setId(Integer id){
   		this.id = id;
   	}

   	public Integer getId(){
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}


}

