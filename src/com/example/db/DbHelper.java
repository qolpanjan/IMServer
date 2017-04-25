package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbHelper {
	
	String Driver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://127.0.0.1:3306/missu";
	String user ="root";
	String password ="123456";
	Connection conn = null;
	
	public Connection getConn(){
		
		try{
			Class.forName(Driver);
			conn = DriverManager.getConnection(url, url, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	
	
	

}
