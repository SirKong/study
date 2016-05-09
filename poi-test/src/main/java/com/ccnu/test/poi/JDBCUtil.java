package com.ccnu.test.poi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	
	private static String URL = "jdbc:mysql://localhost:3306/poitest";
	private static String USERNAME = "root";
	private static String PASSWORD = "ccnu";
	
	public JDBCUtil(){
		
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection connection,ResultSet resultSet){
		if(null!=resultSet){
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(null!=connection){
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
