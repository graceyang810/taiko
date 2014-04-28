package com.taiko.model;

import com.google.gson.annotations.Expose;

public class Response {

	@Expose
	int resp;
	
	public int getResp() {
		return resp;
	}

	public void setResp(int resp) {
		this.resp = resp;
	}

	public Response(int r){
		this.resp = r;
	}
}
