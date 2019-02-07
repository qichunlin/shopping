package com.lanqiao.shopping.service.impl;

import java.util.List;

import com.lanqiao.shopping.dao.ProductDao;
import com.lanqiao.shopping.dao.impl.ProductDaoImpl;
import com.lanqiao.shopping.domain.Product;
import com.lanqiao.shopping.service.ProductService;
import com.lanqiao.shopping.utils.PageUtils;

public class ProductServiceImpl implements ProductService {
	//创建dao层对象
	ProductDao productDao = new ProductDaoImpl();
	
	//通过pid来查找具体商品信息
	@Override
	public List<Product> findProductByCid(String cid) throws Exception {
		
		return productDao.findProductByCid(cid);
	}

	//查找热门商品
	@Override
	public List<Product> findHot() throws Exception {
		return productDao.findHot();
	}

	//分页
	@Override
	public PageUtils findProductByCidWithPage(String cid, int curPageNo) throws Exception {
		//获取总记录数
		int totalRecord = productDao.totalRecord(cid);
		System.out.println("总记录数"+totalRecord);
		
		// 创建工具类对象     当前页  总记录数  页面 大小  
		PageUtils pageUtils = new PageUtils(curPageNo, totalRecord, 3);
		
		//获取dao层分页的集合数据
		List<Product> pList = productDao.findProductByCidWithPage(cid, pageUtils.getStartIndex(), pageUtils.getEndIndex());
		System.out.println("dao层分页数据大小长度"+pList.size());
		
		//给PageUtils工具类中list属性赋值
		pageUtils.setList(pList);
		
		//给PageUtils工具类中url属性赋值
		pageUtils.setUrl("ProductServlet?method=findProductByCidWithPage&cid="+cid);//下一页    ProductServlet?method=findProductByCidWithPage&cid=1
		
		
		return pageUtils;
	}

	//通过pid来查找指定的对象
	@Override
	public Product findProductByPid(String pid) throws Exception{
		return productDao.findProductByPid(pid);
	}

	@Override
	public List<Product> findNews() throws Exception {
		// TODO Auto-generated method stub
		return productDao.findNews();
	}

}
