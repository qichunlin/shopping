package com.lanqiao.shopping.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class demo{
	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final static String url ="jdbc:oracle:thin:@119.23.211.14:1521:orcl";
	final static String username = "Legend";
	final static String password = "Legend";

	public static Connection getConn(){
		Connection conn = null;
		try{
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(url,username,password);
			System.out.println("ss");
			String sql = "select * from sysOrgan";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				//System.out.println(rs.getXXX("XX"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		getConn();
	}

}
