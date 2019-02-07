package com.lanqiao.shopping.test;

import com.lanqiao.shopping.service.impl.ProductServiceImpl;
import com.lanqiao.shopping.utils.PageUtils;

public class PageTest {
	public static void main(String[] args) {
		PageUtils p = new PageUtils(1, 30, 3);
		ProductServiceImpl productServiceImpl = new ProductServiceImpl();
		
		try {
			p = productServiceImpl.findProductByCidWithPage("1", 1);
			System.out.println(p.getList().size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
