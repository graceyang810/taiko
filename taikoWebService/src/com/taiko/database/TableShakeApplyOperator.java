package com.taiko.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TableShakeApplyOperator {

	private DBController db;
	private String sql;

	public boolean checkApply(int id){
		sql = "select * from shakeapplytable where id = " + id;
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
	
	public ResultSet selectApplyList(int id) {
		//查询当前玩家的shaketime
		sql = "select shaketime from shakeapplytable where id = " + id;
		Timestamp time = null;
		ResultSet rs = db.selectSQL(sql);
		try {
			rs.next();
			time = rs.getTimestamp("shaketime");
		} catch (SQLException e) {
			System.out.println("查询shaketime数据库时出错：");
			e.printStackTrace();
		}
		//查询当前玩家前3秒与后3秒之内的玩家
		sql = "select id from shakeapplytable where shakeTime between DATE_SUB("
				+ time + ",INTERVAL 3 SECOND) and ATE_ADD(" + time
				+ ",INTERVAL 3 SECOND)";
		rs = db.selectSQL(sql);
		return rs;
	}

	public boolean deleteApply(int id) {
		sql = "delete from shakeapplytable where id = " + id;
		db.deleteSQL(sql);
		return true;
	}

	public boolean insertApply(int id) {
		Timestamp time = new Timestamp(System.currentTimeMillis());
		sql = "insert into shakeapplytable(id,shaketime) values (" + id + "','" + time + "');";
		db.insertSQL(sql);
		return true;
	}

	public boolean updateTime(int id, Timestamp time) {
		sql = "update shakeapplytable set shakeTime = " + time + " where id = "
				+ id;
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
