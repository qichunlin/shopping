package com.lanqiao.shopping.dao;

import java.util.List;

import com.lanqiao.shopping.domain.Order;
import com.lanqiao.shopping.domain.OrderItem;
import com.lanqiao.shopping.domain.Users;

public interface OrderDao {

	//���涩��
	public void saveOrder(Order order) throws Exception;

	//���涩����
	public void saveOrderItem(OrderItem orderItem) throws Exception;

	
	//��ȡ�Զ�Ӧ�û��µ��ܼ�¼��
	public int totalRecords(Users users);

	//��ȡָ���û��·�ҳ��������
	public List<Order> findOrderAllByUidWithPage(Users users, int startIndex, int endIndex);

}
