package com.taiko.model;

import com.google.gson.annotations.Expose;

public class Score {

	@Expose
	private int score;
	
	public Score(int s){
		this.score = s;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
