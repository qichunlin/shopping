package com.lanqiao.shopping.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanqiao.shopping.domain.Cart;
import com.lanqiao.shopping.domain.CartItem;
import com.lanqiao.shopping.domain.Order;
import com.lanqiao.shopping.domain.OrderItem;
import com.lanqiao.shopping.domain.Users;
import com.lanqiao.shopping.service.OrderService;
import com.lanqiao.shopping.service.impl.OrderServiceImpl;
import com.lanqiao.shopping.utils.PageUtils;
import com.lanqiao.shopping.utils.UUIDUtils;


@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    //����OrderService����
	OrderService orderService = new OrderServiceImpl();
	
	//���涩��
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.�ж��û��Ƿ��¼    ����"�û���¼��" ȡ��   ----�������û�����   ����ȡ����������
			Users users = (Users) request.getSession().getAttribute("users");
			if(users == null) {
				return "jsp/login.jsp";
			}
	    	//2.��ȡ���ﳵ����
			Cart cart = (Cart)request.getSession().getAttribute("cart");
			System.out.println("PWD"+users.getPassword());
			System.out.println("��ǰ�û�����"+users.getUsername()+"UUID"+users.getUuid());
	    	//3.����������������
			
	    		//3-1  ��������
	    		Order order = new Order();
	    		order.setOid(UUIDUtils.getCode());//32λ����
	    		order.setOrderTime(new Date());
	    		order.setTotal(cart.getTotal());//�ӹ��ﳵ��ȡ
	    		order.setState(1);
	    		order.setUsers(users);
	    		//order.setOrderItem(orderItem);
	    		//������ļ�������
	    		
	    		/*OID       VARCHAR2(32)    uuid����                       
					ORDERTIME DATE         Y     new date                    
					TOTAL     NUMBER(8,2)  Y        �����л�ȡ                    
					STATE     NUMBER(11)   Y    1                       
					ADDRESS   VARCHAR2(30) Y    null                     
					NAME      VARCHAR2(30) Y    null                  
					TELEPHONE VARCHAR2(30) Y    null                     
					UUID��Users��      VARCHAR2(32) Y   �û�����    getUUid*/ 
	    		
	    		
	    		//3-2������������
	    		//�������ﳵ��ͬʱ����������  �ٰѹ��ﳵ���з���������䵽��������
	    		for(CartItem cartItem:cart.getMap().values()) {
	    			OrderItem orderItem = new OrderItem();
	    			orderItem.setItemId(UUIDUtils.getCode());
	    			orderItem.setOrder(order);//�������Ķ�������ֵ
	    			orderItem.setProduct(cartItem.getProduct());//������Ʒ����  product
	    			orderItem.setQuantity(cartItem.getNum());//quantity
	    			orderItem.setTotal(cartItem.getSubTotal());//��ȡ���ǹ��ﳵ�����С��
	    			
	    			//�Ѷ�������ӵ�������ȥ   ������ֻ��  new  ��������û���������������
	    			order.getOrderItem().add(orderItem);
	    		
	    		}
	    		/*�������ﳵ��ͬʱ����������  �ٰѹ��ﳵ���з���������䵽��������
	    			ITEMID   VARCHAR2(32)     UUID                      
					QUANTITY NUMBER(11)   Y    ���ﳵ��ȡ                       
					TOTAL    NUMBER(8,2)  Y     ���ﳵ��ȡ                    
					PID      VARCHAR2(32) Y     ��Ʒ����                    
					OID      VARCHAR2(32) Y 	��������*/
	   
	    		//4.����ҵ���߼���ı��淽��ʵ�����ݵı������
	    		orderService.saveOrder(order);
	    		
	    		//5.request���󱣴涩����order��
	    		request.setAttribute("order", order);
	    		
	    		//6.��չ��ﳵ����
	    		cart.clearCart();
	    		//7.��ת��ҳ��/jsp/order_info.jsp
	    		return "jsp/order_info.jsp";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	//��ҳ��ѯ��Ӧ�û��µĶ�������
	public String findOrderAllByUidWithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.��ȡ�û�����
			Users users= (Users)request.getSession().getAttribute("users");
			//2.���ղ���
			int num = Integer.parseInt(request.getParameter("num"));
			//3.����ҵ����ҳ����
			PageUtils pageUtils = orderService.findOrderAllByUidWithPage(users,num);
			//4.�����ҳ����
			request.setAttribute("page", pageUtils);
			//5.ҳ����ת
			return "jsp/order_list.jsp";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}
}
