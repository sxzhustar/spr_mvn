package org.bluedon.lightweb.frame.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="t_sys_resource")
public class SysResource implements Serializable{

	private static final long serialVersionUID = 1L;

 	/**
 	* 主键
 	*/
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
   	private Integer id;
 	/**
 	* 上级id
 	*/
	@Column(name="parent_id")
   	private Integer parentId;
 	/**
 	* 资源名称
 	*/
	@Column(name="resource_name")
   	private String resourceName;
 	/**
 	* 资源路径
 	*/
	@Column(name="resource_path")
   	private String resourcePath;
 	/**
 	* 资源图标
 	*/
	@Column(name="resource_icon")
   	private String resourceIcon;
 	/**
 	* 层级（1-系统,2-模块, 3-菜单，4-按钮）
 	*/
	@Column(name="level")
   	private Integer level;
 	/**
 	* 资源描述
 	*/
	@Column(name="resource_desc")
   	private String resourceDesc;
 	/**
 	* 权限字符串
 	*/
	@Column(name="permission_str")
   	private String permissionStr;
 	/**
 	* 排序
 	*/
	@Column(name="order_num")
   	private Integer orderNum;
 	/**
 	* 是否启用
 	*/
	@Column(name="is_enable")
   	private Integer isEnable;
 	/**
 	* 创建人id
 	*/
	@Column(name="create_user_id")
   	private Integer createUserId;
 	/**
 	* 创建时间
 	*/
	@Column(name="create_time")
   	private java.util.Date createTime;
 	/**
 	* 修改人id
 	*/
	@Column(name="modify_user_id")
   	private Integer modifyUserId;
 	/**
 	* 修改时间
 	*/
	@Column(name="modify_time")
   	private java.util.Date modifyTime;
   	
   	@Transient
   	@Column(name="role_id")
   	private Integer roleId;


   	public void setId(Integer id){
   		this.id = id;
   	}

   	public Integer getId(){
		return id;
	}

   	public void setParentId(Integer parentId){
   		this.parentId = parentId;
   	}

   	public Integer getParentId(){
		return parentId;
	}

   	public void setResourceName(String resourceName){
   		this.resourceName = resourceName;
   	}

   	public String getResourceName(){
		return resourceName;
	}

   	public void setResourcePath(String resourcePath){
   		this.resourcePath = resourcePath;
   	}

   	public String getResourcePath(){
		return resourcePath;
	}

   	public void setResourceIcon(String resourceIcon){
   		this.resourceIcon = resourceIcon;
   	}

   	public String getResourceIcon(){
		return resourceIcon;
	}

   	public void setLevel(Integer level){
   		this.level = level;
   	}

   	public Integer getLevel(){
		return level;
	}

   	public void setResourceDesc(String resourceDesc){
   		this.resourceDesc = resourceDesc;
   	}

   	public String getResourceDesc(){
		return resourceDesc;
	}

   	public void setPermissionStr(String permissionStr){
   		this.permissionStr = permissionStr;
   	}

   	public String getPermissionStr(){
		return permissionStr;
	}

   	public void setOrderNum(Integer orderNum){
   		this.orderNum = orderNum;
   	}

   	public Integer getOrderNum(){
		return orderNum;
	}

   	public void setIsEnable(Integer isEnable){
   		this.isEnable = isEnable;
   	}

   	public Integer getIsEnable(){
		return isEnable;
	}

   	public void setCreateUserId(Integer createUserId){
   		this.createUserId = createUserId;
   	}

   	public Integer getCreateUserId(){
		return createUserId;
	}

   	public void setCreateTime(java.util.Date createTime){
   		this.createTime = createTime;
   	}

   	public java.util.Date getCreateTime(){
		return createTime;
	}

   	public void setModifyUserId(Integer modifyUserId){
   		this.modifyUserId = modifyUserId;
   	}

   	public Integer getModifyUserId(){
		return modifyUserId;
	}

   	public void setModifyTime(java.util.Date modifyTime){
   		this.modifyTime = modifyTime;
   	}

   	public java.util.Date getModifyTime(){
		return modifyTime;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}

