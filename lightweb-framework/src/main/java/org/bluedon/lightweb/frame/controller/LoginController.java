package org.bluedon.lightweb.frame.controller;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.bluedon.lightweb.frame.entity.SysResource;
import org.bluedon.lightweb.frame.entity.SysUser;
import org.bluedon.lightweb.frame.service.ISysResourceService;
import org.bluedon.lightweb.frame.service.IUserService;
import org.bluedon.lightweb.frame.shiro.IncorrectCaptchaException;
import org.bluedon.lightweb.frame.shiro.ShiroUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluedon.lightweb.common.utils.sys.CaptchaUtil;

@Controller
public class LoginController {

	Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	@Qualifier("userService")
	private IUserService userService;
	
	@Autowired
	@Qualifier("resourceService")
	private ISysResourceService resourceService;
	
	/**
	 * 登陆
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(name="/login")
	public String login(HttpServletRequest request,ModelMap model) {
		String url = request.getRequestURI();
		log.debug("loginController请求地址："+url);
		Subject subject = SecurityUtils.getSubject();
		if(subject.isAuthenticated()){
//			subject.logout();
		}
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		if(exceptionClassName!=null){
			if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
				model.addAttribute("warn","不存在此用户");
			}else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
				model.addAttribute("warn","用户名或密码错误");
			}else if (IncorrectCaptchaException.class.getName().equals(exceptionClassName)){
				model.addAttribute("warn","验证码错误");
			}else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
				model.addAttribute("warn","账户被禁用");
			}else {
				model.addAttribute("warn","登录异常");
			}
		}
		return "login";
	}
	
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
	}
	
	/**
	 * 验证码请求
	 * @param request
	 * @param response
	 */
	@RequestMapping("/loadPasskey")
	public void loadPasskey(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Pragram", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("image/jpeg");
		response.setDateHeader("Expires", 0);
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			String captcha = CaptchaUtil.outputVerifyImage(80, 30, outputStream, 4);
			request.getSession().setAttribute("CodeKey", captcha);
			
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	/**
	 * 验证成功转向地址
	 * @param map
	 * @return
	 */
	@RequestMapping("loadAuthorization/pass")
	public String loadResource(ModelMap map) {
		Subject subject = SecurityUtils.getSubject();
		SysUser user = (SysUser)subject.getPrincipal();
		List<SysResource> list = resourceService.findByUser(user);
		map.addAttribute("resList", list);
		return "layout/main";
	}
	
	/**
	 * 主页面
	 * @param map
	 * @return
	 */
	@RequestMapping("/workIndex")
	public String workIndex(ModelMap map){
		SysUser user = ShiroUser.getUser();
		map.addAttribute("user",user);
		return "layout/work_index";
	}
}
