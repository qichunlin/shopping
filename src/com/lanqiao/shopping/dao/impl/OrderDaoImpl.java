package com.lanqiao.shopping.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.lanqiao.shopping.dao.OrderDao;
import com.lanqiao.shopping.domain.Order;
import com.lanqiao.shopping.domain.OrderItem;
import com.lanqiao.shopping.domain.Product;
import com.lanqiao.shopping.domain.Users;
import com.lanqiao.shopping.utils.DBHelper;

public class OrderDaoImpl implements OrderDao{

	//保存订单
	@Override
	public void saveOrder(Order order) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		Object[] obj = {order.getOid(),new java.sql.Date(order.getOrderTime().getTime()),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUsers().getUuid()};
		
		DBHelper.common(sql, obj);
	}

	@Override
	public void saveOrderItem(OrderItem orderItem) throws Exception {
		// TODO Auto-generated method stub
		String sql = "insert into orderItem values(?,?,?,?,?)";
		Object[] obj = {orderItem.getItemId(),orderItem.getQuantity(),orderItem.getTotal(),orderItem.getProduct().getPid(),orderItem.getOrder().getOid()};
		DBHelper.common(sql, obj);
	}

	
	//查找总记录数
	@Override
	public int totalRecords(Users users) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBHelper.getConn();
			String sql = "select count(*) from orders where uuid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, users.getUuid());//通过对象获取里面的属性    关联
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

	
	//分页显示对应用户下的订单
	@Override
	public List<Order> findOrderAllByUidWithPage(Users users, int startIndex, int endIndex) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//保存所有的订单对象
		List<Order> oList = new ArrayList<Order>();
		try {
			conn = DBHelper.getConn();
			String sql1 = "select * from (select rownum rn ,o.* from orders o where uuid=?) where rn>=? and rn<=?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, users.getUuid());//关联属性
			
			pstmt.setInt(2, startIndex);//开始下标
			pstmt.setInt(3, endIndex);//结束下标
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//遍历所有订单对象
				Order order = new Order();//创建订单对象
				order.setOid(rs.getString("oid"));
				order.setOrderTime(rs.getDate("ordertime"));
				order.setState(rs.getInt("state"));
				order.setTotal(rs.getDouble("total"));
				order.setUsers(users);
				
				
				//每一个订单对应的所有订单项进行关联
				String sql2 = "select * from product p,orderitem o where p.pid = o.pid and o.oid=?";
				//通过指定的订单编号来查询他下面的订单项内容（连接数据库）
				PreparedStatement ps = null;
				ResultSet rs1 = null;
				try {
					ps = conn.prepareStatement(sql2);
					ps.setString(1, order.getOid());
					rs1 = ps.executeQuery();
					while(rs1.next()) {
						//商品对象
						Product p = new Product();
						p.setCid(rs1.getString("cid"));
						p.setIS_HOT(rs1.getInt("IS_HOT"));
						p.setMARKET_PRICE(rs1.getDouble("MARKET_PRICE"));
						p.setPdate(rs1.getDate("PDATE"));
						p.setPdesc(rs1.getString("PDESC"));
						p.setPflag(rs1.getInt("PFLAG"));
						p.setPid(rs1.getString("pid"));
						p.setPIMAGE(rs1.getString("PIMAGE"));
						p.setPname(rs1.getString("PNAME"));
						p.setSHOP_PRICE(rs1.getDouble("SHOP_PRICE"));
						
						//订单项对象
						OrderItem orderItem = new OrderItem();
						orderItem.setItemId(rs1.getString("itemid"));
						orderItem.setQuantity(rs1.getInt("quantity"));
						orderItem.setTotal(rs1.getDouble("total"));
						
						//关联商品对象数据
						orderItem.setProduct(p);
						
						//关联订单对象
						orderItem.setOrder(order);
						
						//把订单项添加到订单的集合中     获取到的是集合 getOrderItem()
						order.getOrderItem().add(orderItem);	
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				//把订单对象添加到集合olist里面
				oList.add(order);
			}
			System.out.println("========"+oList.size());
			return oList;
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DBHelper.getClose(rs, pstmt, conn);
		}
		return null;
	}

}
