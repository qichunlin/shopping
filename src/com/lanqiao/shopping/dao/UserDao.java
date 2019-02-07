package com.lanqiao.shopping.dao;

import com.lanqiao.shopping.domain.Users;

/**
 * Dao层---->用户对象模块
 * 
 * @author Administrator
 *
 */
public interface UserDao {
	// 用户注册
	public void register(Users users);// javaBean
	// 用户激活
	public Users userActive(String code);

	// 修改用户对象
	public void updateUsers(Users users);
	
	//用户登录
	public Users userLogin(String username, String pwd);
}
