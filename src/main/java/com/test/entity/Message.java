package com.test.entity;

public class Message {

	private Integer id;
	
	private String mess;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}
	
	public void printMessage() {
		System.out.println(this.getId()+","+this.getMess());
	}
	
}
