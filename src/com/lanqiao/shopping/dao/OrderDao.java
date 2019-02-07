package com.lanqiao.shopping.dao;

import java.util.List;

import com.lanqiao.shopping.domain.Order;
import com.lanqiao.shopping.domain.OrderItem;
import com.lanqiao.shopping.domain.Users;

public interface OrderDao {

	//保存订单
	public void saveOrder(Order order) throws Exception;

	//保存订单项
	public void saveOrderItem(OrderItem orderItem) throws Exception;

	
	//获取对对应用户下的总记录数
	public int totalRecords(Users users);

	//获取指定用户下分页订单数据
	public List<Order> findOrderAllByUidWithPage(Users users, int startIndex, int endIndex);

}
