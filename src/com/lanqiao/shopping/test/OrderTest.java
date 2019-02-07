package com.lanqiao.shopping.test;

import java.util.List;

import com.lanqiao.shopping.dao.OrderDao;
import com.lanqiao.shopping.dao.impl.OrderDaoImpl;
import com.lanqiao.shopping.domain.Order;
import com.lanqiao.shopping.domain.OrderItem;
import com.lanqiao.shopping.domain.Users;

/**
 * �����������
 * @author Administrator
 *
 */
public class OrderTest {
	public static void main(String[] args) {
		OrderDao orderDao = new OrderDaoImpl();
		Users users = new Users();
		users.setUuid("A8A500AD8CBC4B96B65C7C97D827EB8D");
		List<Order> orderList = orderDao.findOrderAllByUidWithPage(users, 1, 5);
		//�ȱ�������
		for (Order order :orderList) {
			System.out.println("�������:"+order.getOid());
			//����������
			for(OrderItem orderItem :order.getOrderItem()) {
				System.out.print(orderItem.getProduct().getPIMAGE()+"\t");
				System.out.print(orderItem.getProduct().getPname()+"\t");
				System.out.print(orderItem.getProduct().getSHOP_PRICE()+"\t");
				System.out.print(orderItem.getQuantity()+"\t");
				System.out.println(orderItem.getTotal()+"\t");
				System.out.println();
				
				
			}
		}
	}
}
