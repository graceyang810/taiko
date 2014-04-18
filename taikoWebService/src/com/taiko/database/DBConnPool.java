package com.taiko.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnPool {
	
	private Connection conn;
	
	private Context context;
	private DataSource ds;
	
	public DBConnPool(){
		try{
			InitialContext initc = new InitialContext();
//			if(initc == null)
//				throw new Exception("No Context");
			
			context = (Context) initc.lookup("java:comp/env");
			ds = (DataSource)context.lookup("jdbc/DBWater");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		try{
			if(ds != null){
				conn = ds.getConnection();
				}
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeConn(){
		try{
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
