package com.taiko.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableResultOperator {
	private DBController db;
	private String sql;

	public int selectValue(int id, String field) {
		sql = "select " + field + " from resulttable where id = " + id;
		int value = 0;
		ResultSet rs = db.selectSQL(sql);
		try {
//			while (rs.next())
			rs.next();
			value = rs.getInt(field);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}

	public boolean updateResult(int id, int score, int perfect, int cool,
			int miss, int combo, int status) {
		sql = "update resulttable set score = " + score + ", perfect = "
				+ perfect + ", cool = " + cool + ", miss = " + miss
				+ ", combo =" + combo+ ", status =" + status + " where id = " + id;
		db.updateSQL(sql);
		return true;
	}

	public boolean uptateValue(int id, String field, int value) {
		sql = "update resulttable set " + field + " = " + value
				+ " where id = " + id;
		db.updateSQL(sql);
		return true;
	}

	public boolean insertResult(int id) {
		sql = "insert into resulttable(id) values (" + id + ")";
		db.insertSQL(sql);
		return true;
	}
	
	public boolean deleteResult(int id) {
		sql = "delete from resulttable where id = " + id;
		db.deleteSQL(sql);
		return true;
	}
	
	public int selectStatus(int id) {
		sql = "select Status from resulttable where id = " + id;
		int status = 0;
		ResultSet rs = db.selectSQL(sql);
		try {
			rs.next();
			status = rs.getInt("feedback");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean updateStatus(int id, int Status) {
		sql = "update resulttable set Status = " + Status
				+ " where id = " + id;
		db.updateSQL(sql);
		return true;
	}
//	public ResultSet selectResult(int id){
//		sql = "select * from resulttable where id = " + id;
//		ResultSet rs = db.selectSQL(sql);
//		return rs;
//	}
	
	public void connectDB() {
		db = new DBController();
		db.connect();
	}

	public void disconnectDB() {
		db.disconnect();
	}

}
