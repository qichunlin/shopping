package com.lanqiao.shopping.service;

import com.lanqiao.shopping.domain.Users;

/**
 * 业务逻辑层
 * 业务逻辑层去访问调用Dao层
 * @author Administrator
 *
 */
public interface UsersService {
	//用户注册
	public void userRegister(Users users);
	
	//用户激活:传递激活码，返回用户对象   
	public Users userActive(String code);
	//修改用户对象    主要是修改state的激活状态
	public void updateUsers(Users users);
	
	//用户登录
	public Users userLogin(String username, String pwd);
}
