package com.lanqiao.shopping.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lanqiao.shopping.domain.Category;
import com.lanqiao.shopping.service.CategoryService;
import com.lanqiao.shopping.service.impl.CategoryServiceImpl;

import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

/*
 * 管理分类模块
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
	CategoryService categoryService = new CategoryServiceImpl();
	
	//ajax   读取所有的分类信息    用于数据传输
	public String findAllCategory(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			//使用redis缓冲技术提高项目执行性能
			Jedis jedis = new Jedis("localhost");
			
			String redis_cList = jedis.get("redis_cList");
			if(redis_cList ==null) {
				System.out.println("从数据库中加载数据");
				//设置一下显示格式
				response.setContentType("text/html;charset=utf-8");
				//调用业务逻辑层
				List<Category> cList = categoryService.findAllCategory();
				//集合转换为json
				JSONArray jsonArray = JSONArray.fromObject(cList);
				
				//保存到redis缓冲区中
				jedis.set("redis_cList",jsonArray.toString());
				//把json数组发送到页面
				response.getWriter().println(jsonArray);
			}else {
				System.out.println("从redis中加载数据");
				
				//设置一下显示格式
				response.setContentType("text/html;charset=utf-8");
				//从redis中读取数据
				response.getWriter().println(redis_cList);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;//不需要返回
	}
    
}
