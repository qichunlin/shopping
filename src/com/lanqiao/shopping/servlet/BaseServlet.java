 package com.lanqiao.shopping.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 处理乱码
		request.setCharacterEncoding("UTF-8");

		// 注册页面上的参数 /UserServlet?method=registerUI
		String method = request.getParameter("method");
		
		//测试
		System.out.println(method);
		
		if(method ==null ||method.equals("") ||method.trim().equals("")) {
			System.out.println("方法参数错误");
		}else {
			try {
				Method m = this.getClass().getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
				//获得返回值得内容
				String path = (String)m.invoke(this, request,response);
				
				if(path ==null ||path.equals("") ||path.trim().equals("")) {
					System.out.println("路径错误");
				}else {
					//response.sendRedirect(path);
					request.getRequestDispatcher(path).forward(request, response);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}	
		}
	}
}
