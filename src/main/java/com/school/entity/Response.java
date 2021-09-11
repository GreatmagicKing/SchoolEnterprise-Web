package com.school.entity;

public class Response <T>{
	String state;
	String msg;
	T data;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Response(String state, String msg, T data) {
		this.state=state;
		this.msg=msg;
		this.data=data;
	}
}
