package com.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Andy
 * 数据库连接管理实体类
 */
public class DbUtil {
	
	private static String url = "jdbc:mysql://localhost:3306/student?useUnicode=true&characterEncoding=utf8";
	private static String user = "root";
	private static String password = "123456";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	static void close(ResultSet rs,Statement state,Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if(state != null){
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			state = null;
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}

	/**
	 * @param 
	 * 测试连接
	 */
	public static void main(String[] args) {
		System.out.println(DbUtil.getConnection());

	}

}
