package org.bluedon.lightweb.frame.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_sys_role_resource")
public class SysRoleResource implements Serializable{

	private static final long serialVersionUID = 1L;

 	/**
 	* 主键
 	*/
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
   	private Integer id;
 	/**
 	* 角色id
 	*/
	@Column(name="role_id")
   	private Integer roleId;
 	/**
 	* 资源id
 	*/
 	@Column(name="resource_id")
   	private Integer resourceId;


   	public void setId(Integer id){
   		this.id = id;
   	}

   	public Integer getId(){
		return id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRowId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}


}

