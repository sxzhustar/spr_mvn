package org.bluedon.lightweb.common.entity.utils;

import javax.servlet.http.HttpServletRequest;

import org.bluedon.lightweb.common.utils.web.ContextHolderUtil;

public class PageUtil {

	public static Integer getPage() {
		HttpServletRequest request = ContextHolderUtil.getRequest();
		Integer offset = request.getParameter("offset") == null ? 0 
				: Integer.parseInt(request.getParameter("offset"));
		Integer rows = getRows();
		if(offset != 0){
			offset = offset / rows;
		}
		offset += 1;
		return offset;
	}
	
	public static Integer getRows() {
		HttpServletRequest request = ContextHolderUtil.getRequest();
		Integer rows = request.getParameter("limit") == null ? 10
				: Integer.parseInt(request.getParameter("limit"));
		return rows;
	}
}
