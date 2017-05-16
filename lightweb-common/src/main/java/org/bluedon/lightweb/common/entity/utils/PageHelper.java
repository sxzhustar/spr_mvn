package org.bluedon.lightweb.common.entity.utils;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.bluedon.lightweb.common.utils.web.ContextHolderUtil;

public class PageHelper implements Serializable {

	private static final long serialVersionUID = 3945863339244744481L;
	
	/**
	 * 一页记录数
	 */
	private int pageSize;
	/**
	 * 第几页
	 */
	private int pageNo;
	
	public PageHelper() {
		super();
		this.pageSize = getRows();
		this.pageNo = getPage();
	}
	
	public Integer getPage() {
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
	
	public Integer getRows() {
		HttpServletRequest request = ContextHolderUtil.getRequest();
		Integer rows = request.getParameter("limit") == null ? 10
				: Integer.parseInt(request.getParameter("limit"));
		return rows;
	}

	public int getFirstResult() {
		return (this.pageNo -1) * this.pageSize;
	}
	
	public int getMaxResults() {
		return this.pageSize;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
