package org.bluedon.lightweb.frame.shiro;

import org.apache.shiro.authc.AuthenticationException;

public class IncorrectCaptchaException extends AuthenticationException {

	private static final long serialVersionUID = -4557596335456348319L;

	public IncorrectCaptchaException(){};
	
	public IncorrectCaptchaException(String msg){
		super(msg);
	}
	
	public IncorrectCaptchaException(Throwable throwable) {
		super(throwable);
	}
}
