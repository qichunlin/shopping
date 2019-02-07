package com.lanqiao.shopping.service.impl;

import java.util.List;

import com.lanqiao.shopping.dao.OrderDao;
import com.lanqiao.shopping.dao.impl.OrderDaoImpl;
import com.lanqiao.shopping.domain.Order;
import com.lanqiao.shopping.domain.OrderItem;
import com.lanqiao.shopping.domain.Users;
import com.lanqiao.shopping.service.OrderService;
import com.lanqiao.shopping.utils.PageUtils;

public class OrderServiceImpl implements OrderService {

	//创建dao层对象
	OrderDao orderDao = new OrderDaoImpl();
	
	//保存订单及订单项
	@Override
	public void saveOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		//1.保存订单
		orderDao.saveOrder(order);
		
		for(OrderItem orderItem:order.getOrderItem()) {
			//2.保存订单项
			orderDao.saveOrderItem(orderItem);//orderItem
		}
		
	}

	

	//分页显示我的订单
	@Override
	public PageUtils findOrderAllByUidWithPage(Users users, int num) throws Exception {
		//获取对应用户下的总记录数
		int totalRecords = orderDao.totalRecords(users);
				
		//创建PageUtils对象
		PageUtils pageUtils = new PageUtils(num, totalRecords, 3);
		
		//获取Dao层的分页方法来获取集合数据    需要对应用户下的订单   所以还需要传入一个用户对象
		List<Order> orderList= orderDao.findOrderAllByUidWithPage(users,pageUtils.getStartIndex(),pageUtils.getEndIndex());
		//设置两个参数
		pageUtils.setUrl("OrderServlet?method=findOrderAllByUidWithPage");
		pageUtils.setList(orderList);
		return pageUtils;
	}

}
