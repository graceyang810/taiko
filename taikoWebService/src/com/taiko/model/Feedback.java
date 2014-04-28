package com.taiko.model;

import com.google.gson.annotations.Expose;

public class Feedback {

	@Expose
	boolean feedback;

	public boolean isFeedback() {
		return feedback;
	}

	public void setFeedback(boolean feedback) {
		this.feedback = feedback;
	}
	
	public Feedback(boolean f){
		this.feedback = f;
	}
}
