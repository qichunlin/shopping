package com.lanqiao.shopping.service;

import com.lanqiao.shopping.domain.Order;
import com.lanqiao.shopping.domain.Users;
import com.lanqiao.shopping.utils.PageUtils;

public interface OrderService {

	//保存订单及订单项
	public void saveOrder(Order order) throws Exception;

	//分页查看我的订单
	public PageUtils findOrderAllByUidWithPage(Users users, int num) throws Exception;

}
