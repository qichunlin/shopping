package com.lanqiao.shopping.service;

import java.util.List;

import com.lanqiao.shopping.domain.Category;

public interface CategoryService {
	//读取所有的分类信息
	List<Category> findAllCategory() throws Exception;

}
