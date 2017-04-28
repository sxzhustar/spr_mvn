package org.bluedon.lightweb.frame.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="t_sys_user")
public class SysUser implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true)
	private Integer id;
	
	@Column(name="user_name",length=50)
	private String userName;
	
	@Column(name="account")
	private String account;
	
	@Column(name="password",length=50)
	private String password;
	
	@Column(name="email",length=50)
	private String email;
	
	@Column(name="mobile_phone")
	private String mobilePhone;
	
	@Column(name="reg_ip")
	private String regIp;
	
	@Column(name="reg_time")
	private Date regIime;
	
	@Column(name="last_login_time")
	private Date lastLoginTime;
	
	@Column(name="last_login_err_time")
	private Date lastLoginErrTime;
	
	@Column(name="last_login_ip")
	private String lastLoginIp;
	
	@Column(name="id_number")//身份证
	private String idNumber;
	
	@Column(name="login_err_times")
	private Integer loginErrTimes;
	
	@Column(name="user_type")
	private Integer userType;
	
	public SysUser(){}
	
	public SysUser(String userName,String psd){
		this.userName = userName;
		this.password = psd;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public Date getRegIime() {
		return regIime;
	}

	public void setRegIime(Date regIime) {
		this.regIime = regIime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastLoginErrTime() {
		return lastLoginErrTime;
	}

	public void setLastLoginErrTime(Date lastLoginErrTime) {
		this.lastLoginErrTime = lastLoginErrTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Integer getLoginErrTimes() {
		return loginErrTimes;
	}

	public void setLoginErrTimes(Integer loginErrTimes) {
		this.loginErrTimes = loginErrTimes;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

}
