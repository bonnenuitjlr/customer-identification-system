package com.cmcc.identification.vo;

import java.io.Serializable;

public class Response  implements Serializable{
	
	private static final long serialVersionUID = 6522000182214939259L;
	
	private int code;
	private String msg;
	private Object data;
	private String isfullview;
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public Object getData() {
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public String getIsfullview() {
		return isfullview;
	}
	
	public void setIsfullview(String isfullview) {
		this.isfullview = isfullview;
	}
}
