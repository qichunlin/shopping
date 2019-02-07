package com.lanqiao.shopping.servlet;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanqiao.shopping.domain.Product;
import com.lanqiao.shopping.service.ProductService;
import com.lanqiao.shopping.service.impl.ProductServiceImpl;
import com.lanqiao.shopping.utils.PageUtils;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	//创建业务层对象
	ProductService productService = new ProductServiceImpl();
	
	//根据pid查找指定的商品对象
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1.接收数据
		String pid = request.getParameter("pid");
		//2.调用业务
		Product product = productService.findProductByPid(pid);
		System.out.println(product);
		
		//3.保存数据   作用域保存     session浪费空间   但是很方便
		request.setAttribute("product", product);
		//4.跳转页面
		return "jsp/product_info.jsp";
	}
	

	
	//会分页
	public String findProductByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			//获取cid数据
			String cid = request.getParameter("cid");
			//获取num数据
			int num  = Integer.parseInt(request.getParameter("num"));
			
			//调用service层中的分页方法
			PageUtils page = productService.findProductByCidWithPage(cid, num);
			System.out.println(page.getList().size());
			
			//保存request对象    只能是page对象
			request.setAttribute("page", page);	
			
			return "jsp/product_list.jsp";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	//查找商品根据id 未分页
	public String findProductByCid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			//获取cid
			String cid = request.getParameter("cid");
			//调用业务逻辑层
			List<Product> pList = productService.findProductByCid(cid);
			System.out.println(pList.size());
			//System.out.println(pList.get(1).getPIMAGE());
			
			//保存数据
			request.setAttribute("pList", pList);
			//跳转页面
			return "jsp/product_list.jsp";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}
}
