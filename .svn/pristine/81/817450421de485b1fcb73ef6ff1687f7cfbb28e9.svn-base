package com.lanqiao.shopping.dao;

import java.util.List;

import com.lanqiao.shopping.domain.Product;

public interface ProductDao {
	//已分页   导入了工具类
	public List<Product> findProductByCidWithPage(String cid,int startIndex,int endIndex)throws Exception;
	
	//获取总记录数的方法
	public int totalRecord(String cid);
	//未分页显示商品的分类信息
	public List<Product> findProductByCid(String cid) throws Exception;
	//查找热门商品
	public List<Product> findHot() throws Exception;

	//通过pid来查找指定商品的对象
	public Product findProductByPid(String pid) throws Exception;

	public List<Product> findNews() throws Exception;

}
