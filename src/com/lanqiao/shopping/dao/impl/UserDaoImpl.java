package com.lanqiao.shopping.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lanqiao.shopping.dao.UserDao;
import com.lanqiao.shopping.domain.Users;
import com.lanqiao.shopping.utils.DBHelper;
import com.lanqiao.shopping.utils.MD5Util;

/**
 * 
 * Dao层 的实现类
 * @author Administrator
 *
 */
public class UserDaoImpl implements UserDao {
	
	//用户注册方法的具体实现    就是将数据插入数据库
	@Override
	public void register(Users users) {
		String sql = "insert into users values(?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?)";
		Object[] obj= {users.getUuid(),users.getUsername(),users.getPassword(),users.getName(),users.getEmail(),users.getTelephone(),users.getBirthday(),users.getSex(),users.getState(),users.getCode()};
		DBHelper.common(sql, obj);
	}

	/*
	 * 用户激活
	 * code：激活码
	 * return：用户对象
	 * jdbc连接数据库
	 */
	@Override
	public Users userActive(String code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBHelper.getConn();
			String sql = "select * from users where code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			System.out.println("UserDaoImpl"+code);
			
			if(rs.next()) {
				//封装对象数据
				
				/*
				 * 	UUID      VARCHAR2(32)                           
					USERNAME  VARCHAR2(20) Y                         
					PASSWORD  VARCHAR2(20) Y                         
					NAME      VARCHAR2(20) Y                         
					EMAIL     VARCHAR2(30) Y                         
					TELEPHONE VARCHAR2(20) Y                         
					BIRTHDAY  DATE         Y                         
					SEX       VARCHAR2(10) Y                         
					STATE     NUMBER(11)   Y        0                
					CODE      VARCHAR2(64) Y 
				 */
				
				Users users = new Users();
				users.setUuid(rs.getString("UUID"));
				users.setUsername(rs.getString("USERNAME"));
				users.setPassword(rs.getString("PASSWORD"));
				users.setName(rs.getString("NAME"));
				users.setEmail(rs.getString("EMAIL"));
				users.setTelephone(rs.getString("TELEPHONE"));
				
				users.setBirthday(rs.getString("BIRTHDAY"));//存入的数据是Date   取出来怎么取
				users.setSex(rs.getString("sex"));
				
				users.setState(rs.getInt("state"));
				users.setCode(rs.getString("code"));
				//System.out.println(users.getCode()+"UDI");
				return users;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBHelper.getClose(rs, pstmt, conn);
		}
		return null;
	}
	
	
	//修改用户对象
	@Override
	public void updateUsers(Users users) {
		// TODO Auto-generated method stub
		//根据主键来修改记录里面的任意属性
		String sql = "update gusers set USERNAME=?,PASSWORD=?,NAME=?,EMAIL=?,TELEPHONE=?,SEX=?,STATE=?,CODE=? where UUID=?";
		//这里面的get的方法需要跟上面的语句?位置一一对应   数组传参
		Object[] obj= {users.getUsername(),users.getPassword(),users.getName(),users.getEmail(),users.getTelephone(),users.getSex(),users.getState(),users.getCode(),users.getUuid()};
		DBHelper.common(sql, obj);
	}

	
	//用户登录
	@Override
	public Users userLogin(String username, String pwd) {
		Connection conn = DBHelper.getConn();
		Users users = null;
		String sql = "select * from gusers where username=? and password=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				users = new Users();
				users.setUuid(rs.getString("UUID"));
				users.setUsername(rs.getString("USERNAME"));
				users.setPassword(rs.getString("PASSWORD"));
				users.setName(rs.getString("NAME"));
				users.setEmail(rs.getString("EMAIL"));
				users.setTelephone(rs.getString("TELEPHONE"));
				
				users.setBirthday(rs.getString("BIRTHDAY"));//存入的数据是Date   取出来怎么取
				users.setSex(rs.getString("sex"));
				
				users.setState(rs.getInt("state"));
				users.setCode(rs.getString("code"));
				return users;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
