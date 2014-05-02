package com.taiko.model;

import com.google.gson.annotations.Expose;

public class ID {

	@Expose
	int id;
	
	public ID(int i){
		this.id = i;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ID() {
		// TODO Auto-generated constructor stub
	}
}
