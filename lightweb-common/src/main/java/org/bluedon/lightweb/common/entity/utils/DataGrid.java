package org.bluedon.lightweb.common.entity.utils;

import java.util.ArrayList;
import java.util.List;

public class DataGrid {

	private int total;
	
	private List<?> rows = new ArrayList<>();
	
	public DataGrid(){}
	
	public DataGrid(int total,List<?> rows){
		this.total = total;
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
