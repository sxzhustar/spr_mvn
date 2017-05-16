package org.bluedon.lightweb.frame.shiro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.bluedon.lightweb.frame.entity.SysResource;
import org.bluedon.lightweb.frame.entity.SysRole;
import org.bluedon.lightweb.frame.entity.SysUser;
import org.bluedon.lightweb.frame.service.ISysResourceService;
import org.bluedon.lightweb.frame.service.ISysRoleService;
import org.bluedon.lightweb.frame.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRealmImpl extends AuthorizingRealm {

	Logger log = LoggerFactory.getLogger(UserRealmImpl.class);
	
	@Autowired
	private ISysUserService userService;
	
	@Autowired
	private ISysResourceService resourceService;
	
	@Autowired
	private ISysRoleService roleService;
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SysUser user = (SysUser)arg0.getPrimaryPrincipal();
		List<SysResource> list = resourceService.findByUser(user);
		List<String> permissions = new ArrayList<>();
		for(SysResource resource:list){
			String permission = resource.getPermissionStr();
			if(!StringUtils.isEmpty(permission)){
				permissions.add(permission);
			}
		}
		//授权资源
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addStringPermissions(permissions);
		//角色
		List<String> roles = roleService.findRolesByUserId(user.getId());
		authorizationInfo.addRoles(roles);
		//存session以供使用
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("roles", roles);
		return authorizationInfo;
	}

	/**
	 * 登陆验证
	 */
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
	
	/**
	 * 清除缓存
	 */
	public void clearCached(){
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
}
