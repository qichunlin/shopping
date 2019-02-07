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
    //创建OrderService对象
	OrderService orderService = new OrderServiceImpl();
	
	//保存订单
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.判断用户是否登录    保存"用户登录名" 取得   ----》保存用户对象   可以取得任意属性
			Users users = (Users) request.getSession().getAttribute("users");
			if(users == null) {
				return "jsp/login.jsp";
			}
	    	//2.获取购物车对象
			Cart cart = (Cart)request.getSession().getAttribute("cart");
			System.out.println("PWD"+users.getPassword());
			System.out.println("当前用户名叫"+users.getUsername()+"UUID"+users.getUuid());
	    	//3.创建订单及订单项
			
	    		//3-1  创建订单
	    		Order order = new Order();
	    		order.setOid(UUIDUtils.getCode());//32位内容
	    		order.setOrderTime(new Date());
	    		order.setTotal(cart.getTotal());//从购物车中取
	    		order.setState(1);
	    		order.setUsers(users);
	    		//order.setOrderItem(orderItem);
	    		//订单项的集合属性
	    		
	    		/*OID       VARCHAR2(32)    uuid生成                       
					ORDERTIME DATE         Y     new date                    
					TOTAL     NUMBER(8,2)  Y        购物中获取                    
					STATE     NUMBER(11)   Y    1                       
					ADDRESS   VARCHAR2(30) Y    null                     
					NAME      VARCHAR2(30) Y    null                  
					TELEPHONE VARCHAR2(30) Y    null                     
					UUID（Users）      VARCHAR2(32) Y   用户对象    getUUid*/ 
	    		
	    		
	    		//3-2：创建订单项
	    		//遍历购物车项同时创建订单项  再把购物车项中饭的数据填充到订单项上
	    		for(CartItem cartItem:cart.getMap().values()) {
	    			OrderItem orderItem = new OrderItem();
	    			orderItem.setItemId(UUIDUtils.getCode());
	    			orderItem.setOrder(order);//给关联的订单对象赋值
	    			orderItem.setProduct(cartItem.getProduct());//关联商品对象  product
	    			orderItem.setQuantity(cartItem.getNum());//quantity
	    			orderItem.setTotal(cartItem.getSubTotal());//获取的是购物车里面的小计
	    			
	    			//把订单项添加到订单中去   集合中只是  new  出来但是没有往里面添加数据
	    			order.getOrderItem().add(orderItem);
	    		
	    		}
	    		/*遍历购物车项同时创建订单项  再把购物车项中饭的数据填充到订单项上
	    			ITEMID   VARCHAR2(32)     UUID                      
					QUANTITY NUMBER(11)   Y    购物车获取                       
					TOTAL    NUMBER(8,2)  Y     购物车获取                    
					PID      VARCHAR2(32) Y     商品对象                    
					OID      VARCHAR2(32) Y 	订单对象*/
	   
	    		//4.调用业务逻辑层的保存方法实现数据的保存操作
	    		orderService.saveOrder(order);
	    		
	    		//5.request对象保存订单（order）
	    		request.setAttribute("order", order);
	    		
	    		//6.清空购物车数据
	    		cart.clearCart();
	    		//7.跳转到页面/jsp/order_info.jsp
	    		return "jsp/order_info.jsp";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	//分页查询对应用户下的订单数据
	public String findOrderAllByUidWithPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取用户对象
			Users users= (Users)request.getSession().getAttribute("users");
			//2.接收参数
			int num = Integer.parseInt(request.getParameter("num"));
			//3.调用业务层分页方法
			PageUtils pageUtils = orderService.findOrderAllByUidWithPage(users,num);
			//4.保存分页数据
			request.setAttribute("page", pageUtils);
			//5.页面跳转
			return "jsp/order_list.jsp";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}
}
