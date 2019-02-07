package com.lanqiao.shopping.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库工具类 包括数据库的连接 关闭连接
 * 
 * @author legend
 *
 */
public class DBHelper {
	private static String driver;
	private static String url;
	private static String name;
	private static String pwd;

	// 初始化属性文件的加载
	static {
		try {
			// 创建Properties对象
			Properties properties = new Properties();

			// 内加载器加载文件
			InputStream inStream = DBHelper.class.getClassLoader().getResourceAsStream("config/oracle.properties");

			// 把流对象传递给Properties对象
			properties.load(inStream);

			// 调用属性文件中的值
			driver = properties.getProperty("driver");// 加载驱动
			url = properties.getProperty("url");// 获得地址
			name = properties.getProperty("name");
			pwd = properties.getProperty("pwd");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// 1.数据库连接
	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, name, pwd);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return conn;
	}

	// 2、关闭数据库
	public static void getClose(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//3.实现通用的增删改操作的方法   第一个是sql语句   第二个是对象数组
	public static void common(String sql,Object...obj) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			//
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i+1, obj[i]);
			}
			int num = pstmt.executeUpdate();
			if(num>0) {
				System.out.println("操作成功");
			}else {
				System.out.println("操作失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
