package com.lanqiao.shopping.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanqiao.shopping.domain.Cart;
import com.lanqiao.shopping.domain.CartItem;
import com.lanqiao.shopping.domain.Product;
import com.lanqiao.shopping.domain.Users;
import com.lanqiao.shopping.service.ProductService;
import com.lanqiao.shopping.service.impl.ProductServiceImpl;

@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
    
	//定义商品业务层对象
	ProductService productService = new ProductServiceImpl(); 
    
   //添加购物车
	public String addToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//判断用户是否登录   不要使用toString不然会报空指针异常
		Users users = (Users)request.getSession().getAttribute("users");
		if(users==null) {
			return "jsp/login.jsp";
		}
		
		//1、接收数据（pid、num）
		String pid = request.getParameter("pid");
		int num = Integer.parseInt(request.getParameter("num"));
		//2、通过pid查找指定商品对象
		Product product = productService.findProductByPid(pid);
		//3、构建购物车项对象(商品对象、数量、小计)
		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setNum(num);
		//4、把购物车项添加购物车中
		//5、从session中获取购物车	
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			//5-1：session中没有购物车
			//新建购物车
			cart = new Cart();
			//购物车保存在session中
			request.getSession().setAttribute("cart", cart);
		}
		
		//5-2：session中存在购物车
		//从session获取购物车
		
		//6、把购物项添加到购物车（addCart方法）
		cart.addCart(cartItem);
		
		//检测购物车中是否有数据
		System.out.println("购物车的长度:"+cart.getMap().size());
		
		//7、跳转到cart.jsp页面显示数据
		return "jsp/cart.jsp";
	}
	
	//删除购物车
	public String delCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		//获取购物车
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		//调用删除方法
		cart.delCart(pid);
		return "jsp/cart.jsp";
	}
	
	//清空购物车
	public String clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		//调用清空方法
		cart.clearCart();
		//返回当前页面
		return "jsp/cart.jsp";
	}
}
