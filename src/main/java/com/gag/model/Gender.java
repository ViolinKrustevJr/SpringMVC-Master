package com.gag.model;

public class Gender {

	private int id;
	private String type;
	
	public Gender(int id, String type) {
		this.id = id;
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public int getId() {
		return id;
	}
}
