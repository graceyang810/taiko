package com.taiko.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;


public class TableMusicOperator {

	private DBController db;
	private String sql;
	
	//查询语句 name\difficulty\soundURL\rhythmURL
//	public String selectMusicByID(int id){
//		sql = "select URL from MusicTable where ID = "+ id;
//		String url = null;
//		ResultSet rs = db.selectSQL(sql);
//		try{
//		rs.next();
//		url = rs.getString("URL");
//		}catch (SQLException e) {  
//            System.out.println("查询url数据库时出错：");  
//            e.printStackTrace();  
//        }
//		return url;
//	}
	public ResultSet selectMusicList(int level) {
		//查询难度等级小于当前等级的音乐
		sql = "select * from MusicTable where Difficulty between 0 and "+ level;
		ResultSet rs = db.selectSQL(sql);
		return rs;
	}
	
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
	
	public int selectMusicIDRandomly(){
		sql = "select count(*) from MusicTable";		
		int count = 0;
		ResultSet rs = db.selectSQL(sql);
		try{
			while(rs.next())
				count = rs.getInt(1);
		}catch (SQLException e) {  
            System.out.println("查询count数据库时出错：");  
            e.printStackTrace();  
        }
		Random random = new Random();
		return random.nextInt(count)+1;
	}
//	
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
