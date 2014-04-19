package com.taiko.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableWaitingRoomOperator {

	private DBController db;
	private String sql;
	
	public int countWaitingRoom(){
		sql = "select count(*) from waitingroomtable";
		ResultSet rs = db.selectSQL(sql);
		int count =0;
		try{
			while(rs.next())
				count = rs.getInt(1);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return count;
	}
	
	public ResultSet selectRoomByOrder(int order){
		sql = "select * from waitingroomtable";
		ResultSet rs = db.selectSQL(sql);
		for (int i = 0; i < order; i++)
			try {
				rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return rs;
	}
	
	
	public ResultSet selectRoomByHost(int hostID){
		sql = "select * from waitingroomtable where host = "+ hostID;
		ResultSet rs = db.selectSQL(sql);
		return rs;
	}
	
	public boolean deleteRoomByHost(int hostID){
		sql = "delete from waitingroomtable where host = "+ hostID;
		db.deleteSQL(sql);
		return true;
	}
	
	public boolean insertRoom(int hostID){
		sql = "insert into waitingroomTable(host) values ("+ hostID +");";
		db.insertSQL(sql);
		return true;
	}
	
	public boolean updateGuest(int host, int guest) {
		sql = "update WaitingRoomTable set guest = " + guest
				+ " where host = " + host;
		db.updateSQL(sql);
		return true;
	}
	
	public boolean updateMusic(int host, int musicID) {
		sql = "update WaitingRoomTable set musicID = " + musicID
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
