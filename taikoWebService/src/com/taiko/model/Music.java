package com.taiko.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Music {

	private int song_id;
	private String song_name;
	private int song_level;
//	private String length;

	public StringBuffer readFile(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
//			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				buffer.append(tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return buffer;
	}

	public Music(int id, String name, int difficulty) {
		this.song_id = id;
		this.song_name = name;
		this.song_level = difficulty;
//		this.path = url;
	}

	public int getSong_id() {
		return song_id;
	}

	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}

	public String getSong_name() {
		return song_name;
	}

	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}

	public int getSong_level() {
		return song_level;
	}

	public void setSong_level(int song_level) {
		this.song_level = song_level;
	}
}
