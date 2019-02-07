package com.lanqiao.shopping.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lanqiao.shopping.dao.ProductDao;
import com.lanqiao.shopping.domain.Category;
import com.lanqiao.shopping.domain.Product;
import com.lanqiao.shopping.utils.DBHelper;

public class ProductDaoImpl implements ProductDao {

	//未分页只是用来显示的
	@Override
	public List<Product> findProductByCid(String cid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> pList = new ArrayList<Product>();
		try {
			conn = DBHelper.getConn();
			String sql = "select * from product where cid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//封闭对象数据
				Product p = new Product();
				p.setCid(rs.getString("cid"));
				p.setIS_HOT(rs.getInt("IS_HOT"));
				p.setMARKET_PRICE(rs.getDouble("MARKET_PRICE"));
				p.setPdate(rs.getDate("PDATE"));
				p.setPdesc(rs.getString("PDESC"));
				p.setPflag(rs.getInt("PFLAG"));
				p.setPid(rs.getString("pid"));
				p.setPIMAGE(rs.getString("PIMAGE"));
				p.setPname(rs.getString("PNAME"));
				p.setSHOP_PRICE(rs.getDouble("SHOP_PRICE"));
				//添加到集合
				pList.add(p);

			}
			return pList;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBHelper.getClose(rs, pstmt, conn);
		}
		return null;
	}

	
	
	//已分页
	@Override
	public List<Product> findProductByCidWithPage(String cid, int startIndex, int endIndex) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> pList = new ArrayList<Product>();
		try {
			conn = DBHelper.getConn();
			String sql = "select * from (select rownum rn ,p.* from product p where cid=?) where rn>=? and rn<=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cid);
			
			pstmt.setInt(2, startIndex);//开始下标
			pstmt.setInt(3, endIndex);//结束下标
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//封闭对象数据
				Product p = new Product();
				p.setCid(rs.getString("cid"));
				p.setIS_HOT(rs.getInt("IS_HOT"));
				p.setMARKET_PRICE(rs.getDouble("MARKET_PRICE"));
				p.setPdate(rs.getDate("PDATE"));
				p.setPdesc(rs.getString("PDESC"));
				p.setPflag(rs.getInt("PFLAG"));
				p.setPid(rs.getString("pid"));
				p.setPIMAGE(rs.getString("PIMAGE"));
				p.setPname(rs.getString("PNAME"));
				p.setSHOP_PRICE(rs.getDouble("SHOP_PRICE"));
				//添加到集合
				pList.add(p);

			}
			System.out.println("========"+pList.size());
			return pList;
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBHelper.getClose(rs, pstmt, conn);
		}
		return null;
	}
	
	
	//查找热门商品
	@Override
	public List<Product> findHot() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> pList = new ArrayList<Product>();
		try {
			conn = DBHelper.getConn();
			String sql = "select * from product where IS_HOT=1 and pflag=0 and rownum<=9 order by pdate desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//封闭对象数据
				Product p = new Product();
				p.setCid(rs.getString("cid"));
				p.setIS_HOT(rs.getInt("IS_HOT"));
				p.setMARKET_PRICE(rs.getDouble("MARKET_PRICE"));
				p.setPdate(rs.getDate("PDATE"));
				p.setPdesc(rs.getString("PDESC"));
				p.setPflag(rs.getInt("PFLAG"));
				p.setPid(rs.getString("pid"));
				p.setPIMAGE(rs.getString("PIMAGE"));
				p.setPname(rs.getString("PNAME"));
				p.setSHOP_PRICE(rs.getDouble("SHOP_PRICE"));
				//添加到集合
				pList.add(p);

			}
			return pList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBHelper.getClose(rs, pstmt, conn);
		}
		return null;
	}



	//获取总记录数
	@Override
	public int totalRecord(String cid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBHelper.getConn();
			String sql = "select count(*) from product where cid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);//下标方式获取数据库字段  30
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBHelper.getClose(rs, pstmt, conn);
		}
		return 0;
	}



	//通过pid来找商品对象
	@Override
	public Product findProductByPid(String pid) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			conn = DBHelper.getConn();
			String sql = "select * from product where pid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//封闭对象数据
				Product p = new Product();
				p.setCid(rs.getString("cid"));
				p.setIS_HOT(rs.getInt("IS_HOT"));
				p.setMARKET_PRICE(rs.getDouble("MARKET_PRICE"));
				p.setPdate(rs.getDate("PDATE"));
				p.setPdesc(rs.getString("PDESC"));
				p.setPflag(rs.getInt("PFLAG"));
				p.setPid(rs.getString("pid"));
				p.setPIMAGE(rs.getString("PIMAGE"));
				p.setPname(rs.getString("PNAME"));
				p.setSHOP_PRICE(rs.getDouble("SHOP_PRICE"));
				return p;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBHelper.getClose(rs, pstmt, conn);
		}
		return null;
	}


	//查找最新商品
	@Override
	public List<Product> findNews() throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> pList = new ArrayList<Product>();
		try {
			conn = DBHelper.getConn();
			String sql = "select * from product where PFLAG=0 and rownum>=1 and rownum<=9  order by PDATE desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//封闭对象数据
				Product p = new Product();
				p.setCid(rs.getString("cid"));
				p.setIS_HOT(rs.getInt("IS_HOT"));
				p.setMARKET_PRICE(rs.getDouble("MARKET_PRICE"));
				p.setPdate(rs.getDate("PDATE"));
				p.setPdesc(rs.getString("PDESC"));
				p.setPflag(rs.getInt("PFLAG"));
				p.setPid(rs.getString("pid"));
				p.setPIMAGE(rs.getString("PIMAGE"));
				p.setPname(rs.getString("PNAME"));
				p.setSHOP_PRICE(rs.getDouble("SHOP_PRICE"));
				//添加到集合
				pList.add(p);

			}
			return pList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBHelper.getClose(rs, pstmt, conn);
		}
		return null;
	}
}
