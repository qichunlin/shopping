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
 * Dao�� ��ʵ����
 * @author Administrator
 *
 */
public class UserDaoImpl implements UserDao {
	
	//�û�ע�᷽���ľ���ʵ��    ���ǽ����ݲ������ݿ�
	@Override
	public void register(Users users) {
		String sql = "insert into users values(?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'),?,?,?)";
		Object[] obj= {users.getUuid(),users.getUsername(),users.getPassword(),users.getName(),users.getEmail(),users.getTelephone(),users.getBirthday(),users.getSex(),users.getState(),users.getCode()};
		DBHelper.common(sql, obj);
	}

	/*
	 * �û�����
	 * code��������
	 * return���û�����
	 * jdbc�������ݿ�
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
				//��װ��������
				
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
				
				users.setBirthday(rs.getString("BIRTHDAY"));//�����������Date   ȡ������ôȡ
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
	
	
	//�޸��û�����
	@Override
	public void updateUsers(Users users) {
		// TODO Auto-generated method stub
		//�����������޸ļ�¼�������������
		String sql = "update gusers set USERNAME=?,PASSWORD=?,NAME=?,EMAIL=?,TELEPHONE=?,SEX=?,STATE=?,CODE=? where UUID=?";
		//�������get�ķ�����Ҫ����������?λ��һһ��Ӧ   ���鴫��
		Object[] obj= {users.getUsername(),users.getPassword(),users.getName(),users.getEmail(),users.getTelephone(),users.getSex(),users.getState(),users.getCode(),users.getUuid()};
		DBHelper.common(sql, obj);
	}

	
	//�û���¼
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
				
				users.setBirthday(rs.getString("BIRTHDAY"));//�����������Date   ȡ������ôȡ
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
