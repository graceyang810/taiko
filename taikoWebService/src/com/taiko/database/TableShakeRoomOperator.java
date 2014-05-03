package com.taiko.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableShakeRoomOperator {

	private DBController db;
	private String sql;
	
	public boolean checkGuest(int guestID){
		sql = "select guest from shakeroomtable where guest = " + guestID;
		ResultSet rs = db.selectSQL(sql);
		try {
			if(rs.next())
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean checkHost(int hostID){
		sql = "select host from shakeroomtable where host = " + hostID;
		ResultSet rs = db.selectSQL(sql);
		try {
			if(rs.next())
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
	public int selectGuest(int hostID) {
		sql = "select Guest from shakeroomtable where host = " + hostID;
		int guest = 0;
		ResultSet rs = db.selectSQL(sql);
		try {
			rs.next();
			guest = rs.getInt("guest");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return guest;
	}
	
	public int selectHost(int guestID) {
		sql = "select Host from shakeroomtable where guest = " + guestID;
		int host = 0;
		ResultSet rs = db.selectSQL(sql);
		try {
			rs.next();
			host = rs.getInt("host");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return host;
	}
	
	public int selectMusic(int hostID) {
		sql = "select MusicID from shakeroomtable where host = " + hostID;
		int music = 0;
		ResultSet rs = db.selectSQL(sql);
		try {
			rs.next();
			music = rs.getInt("MusicID");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return music;
	}
	
	public int selectFeedback(int hostID) {
		sql = "select Feedback from shakeroomtable where host = " + hostID;
		int feedback = -1;
		ResultSet rs = db.selectSQL(sql);
		try {
			rs.next();
			feedback = rs.getInt("feedback");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return feedback;
	}
	
	public boolean deleteRoomByHost(int hostID){
		sql = "delete from shakeroomtable where host = "+ hostID;
		db.deleteSQL(sql);
		return true;
	}
	
	public boolean insertRoom(int hostID){
		sql = "insert into shakeroomtable(host) values ("+ hostID +")";
		db.insertSQL(sql);
		return true;
	}
	
	public boolean updateGuest(int host, int guest) {
		sql = "update shakeroomtable set guest = " + guest
				+ " where host = " + host;
		db.updateSQL(sql);
		return true;
	}
	
	public boolean updateMusic(int host, int musicID) {
		sql = "update shakeroomtable set musicID = " + musicID
				+ " where host = " + host;
		db.updateSQL(sql);
		return true;
	}
	
	public boolean updateFeedback(int host, int feedback) {
		sql = "update shakeroomtable set feedback = " + feedback
				+ " where host = " + host;
		db.updateSQL(sql);
		return true;
	}
	
	public void connectDB() {
		db = new DBController();
		db.connect();
	}

	public void disconnectDB() {
		db.disconnect();
	}
}
