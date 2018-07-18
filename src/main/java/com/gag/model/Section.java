package com.gag.model;

public class Section {
	private int id;
	private String name;

	public Section(String name) {
		this.name = name;
	}

	public Section(int id, String name) {
		this(name);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
}
