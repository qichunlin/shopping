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
 * 业务逻辑层的实现类    
 * 主要去调用UserDao接口里面的方法
 * @author Administrator
 *
 */
public class UsersServiceImpl implements UsersService{
	//调用UserDao层的对象
	UserDao userDao = new UserDaoImpl();
	
	//用户注册
	@Override
	public void userRegister(Users users) {
		//创建Dao层的对象    调用注册方法
		userDao.register(users);
		
		//注册完就往邮箱中发送激活码信息   这里有个method参数
		SendJMail.sendMail(users.getEmail(), " 注册成功，请<a href='http://localhost:8080/shopping/UserServlet?method=userActive&code="+users.getCode()+"'>激活</a>后登录");
		//email：接收者的邮箱  emailMsg：发送邮箱的内容) 
	}
	
	
	
	//用户激活:传递激活码，返回用户对象
	@Override
	public Users userActive(String code) {
		return userDao.userActive(code);
	}

	
	//修改用户对象
	@Override
	public void updateUsers(Users users) {
		// TODO Auto-generated method stub
		userDao.updateUsers(users);
	}



	//用户注册
	@Override
	public Users userLogin(String username, String pwd) {
		return userDao.userLogin(username,pwd);
	}



	
}
