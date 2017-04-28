package org.bluedon.lightweb.frame.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="t_sys_role_resource")
public class SysRoleResource implements Serializable{

	private static final long serialVersionUID = 1L;

 	/**
 	* 主键
 	*/
	@Id
   	private Integer id;
 	/**
 	* 角色id
 	*/
	@JoinColumn(name="role_id")
   	private Integer rowId;
 	/**
 	* 资源id
 	*/
 	@JoinColumn(name="resource_id")
   	private Integer resourceId;


   	public void setId(Integer id){
   		this.id = id;
   	}

   	public Integer getId(){
		return id;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}


}

