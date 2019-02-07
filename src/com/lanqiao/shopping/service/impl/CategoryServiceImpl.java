package com.lanqiao.shopping.service.impl;

import java.util.List;

import com.lanqiao.shopping.dao.CategoryDao;
import com.lanqiao.shopping.dao.impl.CategoryDaoImpl;
import com.lanqiao.shopping.domain.Category;
import com.lanqiao.shopping.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{
	
	CategoryDao categoryDao = new CategoryDaoImpl();
	
	//查找所有的分类信息
	@Override
	public List<Category> findAllCategory() throws Exception {
		List<Category> cList = categoryDao.findAllCategory();
		return cList;
	}

}
