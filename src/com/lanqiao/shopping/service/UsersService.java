package com.lanqiao.shopping.service;

import com.lanqiao.shopping.domain.Users;

/**
 * ҵ���߼���
 * ҵ���߼���ȥ���ʵ���Dao��
 * @author Administrator
 *
 */
public interface UsersService {
	//�û�ע��
	public void userRegister(Users users);
	
	//�û�����:���ݼ����룬�����û�����   
	public Users userActive(String code);
	//�޸��û�����    ��Ҫ���޸�state�ļ���״̬
	public void updateUsers(Users users);
	
	//�û���¼
	public Users userLogin(String username, String pwd);
}
