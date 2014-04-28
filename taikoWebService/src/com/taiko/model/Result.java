package com.taiko.model;

public class Result {
	private int id;
	private int score;
	private int perfect;
	private int cool;
	private int miss;
	private int combo;
	
	public Result(int id, int score, int perfect, int cool, int miss, int combo){
		this.id = id;
		this.score = score;
		this.perfect = perfect;
		this.cool = cool;
		this.miss = miss;
		this.combo = combo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getPerfect() {
		return perfect;
	}
	public void setPerfect(int perfect) {
		this.perfect = perfect;
	}
	public int getCool() {
		return cool;
	}
	public void setCool(int cool) {
		this.cool = cool;
	}
	public int getMiss() {
		return miss;
	}
	public void setMiss(int miss) {
		this.miss = miss;
	}
	public int getCombo() {
		return combo;
	}
	public void setCombo(int combo) {
		this.combo = combo;
	}
	
	
}
