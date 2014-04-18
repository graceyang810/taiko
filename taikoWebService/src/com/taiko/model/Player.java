package com.taiko.model;


public class Player{

	private int id;
	private String name;
	private String sex;
	private String avatar;
	private int level;
	
	//id,name,gender,photo,level
	public Player(int id, String name, String gender, String photo, int level) {
		this.id = id;
		this.name = name;
		this.sex = gender;
		this.level = level;
		this.avatar = photo;

	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

//	public Player() {
//		// TODO Auto-generated constructor stub
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


}
