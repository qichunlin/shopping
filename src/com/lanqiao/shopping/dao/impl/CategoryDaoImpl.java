package com.lanqiao.shopping.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lanqiao.shopping.dao.CategoryDao;
import com.lanqiao.shopping.domain.Category;
import com.lanqiao.shopping.domain.Users;
import com.lanqiao.shopping.utils.DBHelper;

public class CategoryDaoImpl implements CategoryDao{

	@Override
	public List<Category> findAllCategory() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBHelper.getConn();
			String sql = "select * from category";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			List<Category> cList = new ArrayList<Category>();
			while(rs.next()) {
				//封装对象数据
				Category c = new Category();
				c.setCid(rs.getString("cid"));
				c.setCname(rs.getString("cname"));
				cList.add(c);//添加到集合中去
			}
			return cList;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBHelper.getClose(rs, pstmt, conn);
		}
		return null;
	}

}
