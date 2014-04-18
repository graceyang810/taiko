package com.taiko.database;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TableMusicOperator {

	private DBController db;
	private String sql;
	
	//查询语句 name\difficulty\soundURL\rhythmURL
	public String selectMusicName(int id){
		sql = "select MusicName from MusicTable where ID = "+ id;
		String name = null;
		ResultSet rs = db.selectSQL(sql);
		try{
		rs.next();
		name = rs.getString("MusicName");
		}catch (SQLException e) {  
            System.out.println("查询name数据库时出错：");  
            e.printStackTrace();  
        }
		return name;
	}
	
	public int selectMusicDifficulty(int id){
		sql = "select Difficulty from MusicTable where ID = "+id;
		int difficulty = 0;
		ResultSet rs = db.selectSQL(sql);
		try{
		rs.next();
		difficulty = rs.getInt("Difficulty");
		}catch (SQLException e) {  
            System.out.println("查询difficulty数据库时出错：");  
            e.printStackTrace();  
        }
		return difficulty;
	}
	
	public String selectMusicSound(int id){
		sql = "select SoundURL from MusicTable where ID = "+id;
		String sound = null;
		ResultSet rs = db.selectSQL(sql);
		try{
		rs.next();
		sound = rs.getString("SoundURL");
		}catch (SQLException e) {  
            System.out.println("查询sound数据库时出错：");  
            e.printStackTrace();  
        }
		return sound;
	}
	
	public String selectMusicRhythm(int id){
		sql = "select RhythmURL from MusicTable where ID = "+id;
		String rhythm = null;
		ResultSet rs = db.selectSQL(sql);
		try{
		rs.next();
		rhythm = rs.getString("RhythmURL");
		}catch (SQLException e) {  
            System.out.println("查询rhythm数据库时出错：");  
            e.printStackTrace();  
        }
		return rhythm;
	}
		
	public void connectDB(){
		db = new DBController();
		db.connect();
	}
	
	public void disconnectDB(){
		db.disconnect();
	}
}
