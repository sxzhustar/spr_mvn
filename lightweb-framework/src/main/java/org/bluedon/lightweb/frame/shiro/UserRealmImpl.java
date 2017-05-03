package org.bluedon.lightweb.frame.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.bluedon.lightweb.frame.entity.SysUser;
import org.bluedon.lightweb.frame.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value="userRealImpl")
public class UserRealmImpl extends AuthorizingRealm {

	Logger log = LoggerFactory.getLogger(UserRealmImpl.class);
	
	@Autowired
	private IUserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		log.info("=======验证方法被调用==========");
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) arg0;
		String userName = token.getUsername();
		String psd = String.valueOf(token.getPassword());
		String captcha = token.getCaptcha();

		//验证验证码是否正确
		Session session = SecurityUtils.getSubject().getSession();
		String sessionCaptcha = null;
		Object object = session.getAttribute("CodeKey");
		if(object instanceof String){
			sessionCaptcha = (String)object;
		}
		if(sessionCaptcha == null || sessionCaptcha != null && !sessionCaptcha.equalsIgnoreCase(captcha)){
			throw new IncorrectCaptchaException("验证码错误");
		}
		//验证用户名密码
		SysUser user = userService.getUserByName(userName);
		if(user == null){
			//返回null，shiro会抛出UnknownAccountException
			return null;
		}else{
			return new SimpleAuthenticationInfo(user, psd, getName());
		}
	}
}
