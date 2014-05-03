package com.taiko.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableUserOperator {

	private DBController db;
	private String sql;

	// 插入数据库 新用户
	public int insertUser(String name, String password, String gender,
			String photo) {
		sql = "insert into UserTable(UserName,Password,Gender,PhotoURL) values('"
				+ name + "','" + password + "','" + gender + "','" + photo + "')";
		db.insertSQL(sql);
		sql = "SELECT LAST_INSERT_ID()";
		int id = 0;
		ResultSet rs = db.selectSQL(sql);
		try {
			while (rs.next())
				id = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("查询id数据库时出错：");
			e.printStackTrace();
		}
		return id;
	}

	// 查询语句 id、username\password\gender\exp\level\photo
//	public int selectUserID(String name) {
//		sql = "select ID from UserTable where UserName = '" + name + "'";
//		int id = 0;
//		ResultSet rs = db.selectSQL(sql);
//		try {
//			while (rs.next())
//				id = rs.getInt("ID");
//		} catch (SQLException e) {
//			System.out.println("查询id数据库时出错：");
//			e.printStackTrace();
//		}
//		return id;
//	}
		
	public String selectUserName(int id) {
		sql = "select UserName from UserTable where ID = " + id;
		String username = null;
		ResultSet rs = db.selectSQL(sql);
		try {
			while (rs.next())
				username = rs.getString("UserName");
		} catch (SQLException e) {
			System.out.println("查询username数据库时出错：");
			e.printStackTrace();
		}
		return username;
	}

	public String selectUserPassword(int id) {
		sql = "select Password from UserTable where ID = " + id;
		String password = null;
		ResultSet rs = db.selectSQL(sql);
		try {
			rs.next();
			password = rs.getString("Password");
		} catch (SQLException e) {
			System.out.println("查询password数据库时出错：");
			e.printStackTrace();
		}
		return password;
	}

	public String selectGender(int id) {
		sql = "select Gender from UserTable where ID = " + id;
		String gender = null;
		ResultSet rs = db.selectSQL(sql);
		try {
			rs.next();
			gender = rs.getString("Gender");
		} catch (SQLException e) {
			System.out.println("查询gender数据库时出错：");
			e.printStackTrace();
		}
		return gender;
	}

	public int selectUserExp(int id) {
		sql = "select ExperiencePoint from UserTable where ID = " + id;
		int exp = 0;
		ResultSet rs = db.selectSQL(sql);
		try {
			rs.next();
			exp = rs.getInt("ExperiencePoint");
		} catch (SQLException e) {
			System.out.println("查询exp数据库时出错：");
			e.printStackTrace();
		}
		return exp;
	}

	public int selectUserLevel(int id) {
		sql = "select Level from UserTable where ID = " + id;
		int level = 0;
		ResultSet rs = db.selectSQL(sql);
//		ResultSet rs = null;
//		rs = db.selectSQL(sql);
		try {
			rs.next();
			level = rs.getInt("Level");
		} catch (SQLException e) {
			System.out.println("查询level数据库时出错：");
			e.printStackTrace();
		}
		return level;
	}

	public String selectUserPhoto(int id) {
		sql = "select PhotoURL from UserTable where ID = " + id;
		String photo = null;
		ResultSet rs = db.selectSQL(sql);
		try {
			rs.next();
			photo = rs.getString("PhotoURL");
		} catch (SQLException e) {
			System.out.println("查询photo数据库时出错：");
			e.printStackTrace();
		}
		return photo;
	}

	// 删除用户
	public boolean deleteUser(int id) {
		sql = "delete from UserTable where id = " + id;
		db.deleteSQL(sql);
		return true;
	}

	// 更新数据库 passowrd\exp\level\photo
	public boolean updateUserPassword(int id, String value) {
		sql = "update UserTable set Password = " + value + " where ID = " + id;
		db.updateSQL(sql);
		return true;
	}

	public boolean updateUserExp(int id, int value) {
		sql = "update UserTable set ExperiencePoint = " + value
				+ " where ID = " + id;
		db.updateSQL(sql);
		return true;
	}

	public boolean updateUserLevel(int id, int value) {
		sql = "update UserTable set Level = " + value + " where ID = " + id;
		db.updateSQL(sql);
		return true;
	}

	public boolean updateUserPhoto(int id, String value) {
		sql = "update UserTable set PhotoURL = " + value + " where ID = " + id;
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
