package com.lanqiao.shopping.dao;

import java.util.List;

import com.lanqiao.shopping.domain.Category;

public interface CategoryDao {
	//��ȡ���еķ�����Ϣ
	List<Category> findAllCategory() throws Exception;

}
