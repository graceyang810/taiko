package com.taiko.model;

import com.google.gson.annotations.Expose;

public class MusicInfoURL {

	@Expose
	private String soundURL;
	@Expose
	private String rhythmURL;
	
	public MusicInfoURL(String s,String r){
		this.soundURL = s;
		this.rhythmURL = r;
	}
}
