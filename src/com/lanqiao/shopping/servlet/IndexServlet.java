package com.lanqiao.shopping.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 在加载index.jsp页面之前执行
 */

import com.lanqiao.shopping.domain.Product;
import com.lanqiao.shopping.service.ProductService;
import com.lanqiao.shopping.service.impl.ProductServiceImpl;
@WebServlet("/IndexServlet")
public class IndexServlet extends BaseServlet {
	//创建业务逻辑层对象
	ProductService productService = new ProductServiceImpl();
	
	//加载最新的和热门的9件商品
	public String loadProduct(HttpServletRequest request, HttpServletResponse response){
		try {
			//调用业务逻辑层   查找热门的9个商品
			List<Product> hots = productService.findHot();
			System.out.println("===="+hots.size());
			//查找最新的9个商品
			List<Product> news = productService.findNews();
			System.out.println("===="+news.size());
			
			//保存request对象
			request.setAttribute("hots", hots);
			//保存request对象
			request.setAttribute("news", news);
			//跳转页面
			return "jsp/index.jsp";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	} 
}
