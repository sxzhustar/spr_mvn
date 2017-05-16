package org.bluedon.lightweb.common.utils.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * springMvc下获取ServletContext相关
 * @author star
 *
 */
public class ContextHolderUtil {

	public static HttpServletRequest getRequest(){
		ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		return request;
	}
	
	public static HttpSession getSession() {
		HttpServletRequest request = getRequest();
		return request.getSession();
	}
}
