package org.bluedon.lightweb.frame.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.bluedon.lightweb.common.utils.web.NetWorkUtil;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected AuthenticationToken createToken(String username, String password, ServletRequest request,
			ServletResponse response) {
		HttpServletRequest hRequest = (HttpServletRequest)request;
		String ip = NetWorkUtil.getIpAddress(hRequest);
		String captcha = WebUtils.getCleanParam(request, "passKey");
		Boolean rememberMe = isRememberMe(request);
		return new CaptchaUsernamePasswordToken(username, password, rememberMe, captcha, ip);
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		WebUtils.getAndClearSavedRequest(request);
		return super.onLoginSuccess(token, subject, request, response);
	}
	
	
}
