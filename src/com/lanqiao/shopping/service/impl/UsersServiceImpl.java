package com.lanqiao.shopping.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lanqiao.shopping.dao.UserDao;
import com.lanqiao.shopping.dao.impl.UserDaoImpl;
import com.lanqiao.shopping.domain.Users;
import com.lanqiao.shopping.service.UsersService;
import com.lanqiao.shopping.utils.DBHelper;
import com.lanqiao.shopping.utils.SendJMail;

/**
 * ҵ���߼����ʵ����    
 * ��Ҫȥ����UserDao�ӿ�����ķ���
 * @author Administrator
 *
 */
public class UsersServiceImpl implements UsersService{
	//����UserDao��Ķ���
	UserDao userDao = new UserDaoImpl();
	
	//�û�ע��
	@Override
	public void userRegister(Users users) {
		//����Dao��Ķ���    ����ע�᷽��
		userDao.register(users);
		
		//ע������������з��ͼ�������Ϣ   �����и�method����
		SendJMail.sendMail(users.getEmail(), " ע��ɹ�����<a href='http://localhost:8080/shopping/UserServlet?method=userActive&code="+users.getCode()+"'>����</a>���¼");
		//email�������ߵ�����  emailMsg���������������) 
	}
	
	
	
	//�û�����:���ݼ����룬�����û�����
	@Override
	public Users userActive(String code) {
		return userDao.userActive(code);
	}

	
	//�޸��û�����
	@Override
	public void updateUsers(Users users) {
		// TODO Auto-generated method stub
		userDao.updateUsers(users);
	}



	//�û�ע��
	@Override
	public Users userLogin(String username, String pwd) {
		return userDao.userLogin(username,pwd);
	}



	
}
