package com.taiko.model;

import com.google.gson.annotations.Expose;

public class MusicSoundURL {

	@Expose
	private String soundURL;
	
	public MusicSoundURL(String s){
		this.soundURL = s;
	}
}
