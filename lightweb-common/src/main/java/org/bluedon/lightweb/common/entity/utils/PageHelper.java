package org.bluedon.lightweb.common.entity.utils;

import java.io.Serializable;

public class PageHelper implements Serializable {

	private static final long serialVersionUID = 3945863339244744481L;
	
	private int pageCount;
	private int rowCount;
	private int pageSize;
	private int page;
	private int startIndex;
	private int endIndex;
	
	public int getFirstResult() {
		return (this.page -1) * this.pageSize;
	}
	
	public int getMaxResults() {
		return this.pageSize;
	}
	
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}
