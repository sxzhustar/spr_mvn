package org.bluedon.lightweb.common.entity.utils;

import java.io.Serializable;

/**
 * ajax 交互传递的JSON数据
 */
public class JsonData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean success = false;

	private String msg = "";

	private Object obj = null;
	
	public JsonData(){
		
	}
	
	public JsonData(boolean success,String msg,Object obj){
		this.success=success;
		this.msg=msg;
		this.obj=obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}
