package com.taiko.utility;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Message extends ArrayList<Object> {
	@Expose
	private final static Gson gson = new GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation().create();


	public void addInfo(Object o){
		add(o);
	}

	public String toJson() {
		return gson.toJson(this);
	}

	// public void fromJson(){
	// gson.f
	// }
	public String toString() {
		return gson.toJson(this);
	}

}
