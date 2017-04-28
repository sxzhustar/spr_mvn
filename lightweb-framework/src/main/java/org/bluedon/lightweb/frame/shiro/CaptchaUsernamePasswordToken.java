package org.bluedon.lightweb.frame.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class CaptchaUsernamePasswordToken extends UsernamePasswordToken {
	private static final long serialVersionUID = 1L;
	
	private String captcha;
	
	private String ip;

	public CaptchaUsernamePasswordToken(){}
	
	public CaptchaUsernamePasswordToken(String username,String password,Boolean remember,String captcha,String ip){
		super(username, password, remember);
		this.captcha = captcha;
		this.ip = ip;
	}
	
	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
