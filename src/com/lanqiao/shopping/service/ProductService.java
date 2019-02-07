package com.lanqiao.shopping.service;

import java.util.List;

import com.lanqiao.shopping.domain.Product;
import com.lanqiao.shopping.utils.PageUtils;

public interface ProductService {
	//根据id查找对应的商品的所有信息
	public List<Product> findProductByCid(String cid) throws Exception;
	
	//创建分页方法  本来是集合的  现在换成工具类
	PageUtils findProductByCidWithPage(String cid,int curPageNo)throws Exception;
	
	//查找热门商品
	public List<Product> findHot() throws Exception;

	//根据pid来查找指定商品的对象
	public Product findProductByPid(String pid) throws Exception;
	//查找最新商品
	public List<Product> findNews() throws Exception;

}
