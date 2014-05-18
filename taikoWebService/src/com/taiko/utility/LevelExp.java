package com.taiko.utility;

public class LevelExp {

	private int level;
	private int exp;
	
	public static int win = 1;
	public static int loose = 0;
	public static int dogfall = 2;
	public static int init = -1;
	
	public int transfer(int type){
		if(type == win)
			exp+=3;
		else if(type == dogfall)
			exp+=1;
		else
			exp-=1;
		if(exp>= level*10){
			exp-=level*10;
			level++;
		}else if(exp<0){
			if(level>1){
				level--;
				exp+=level*10;
			}
		}
		return level;
	}
	
	public LevelExp(int level, int exp){
		this.level = level;
		this.exp = exp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
}
