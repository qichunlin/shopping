package com.lanqiao.shopping.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private String oid;//订单ID
	private Date orderTime;//下单时间
	private int state;//状态1：下单未付款   2.付款未发货  3.发货未收货  4.收货结束
	private double total;//总计
	private String address;//收货人地址
	private String name;//收货人姓名
	private String telephone;//收货人电话
	
	//根据数据库结构来关联实现对象的联系
	//private String uid;
	
	//根据java面向对象思想  来关联实现对象的关联关系（推荐）
	private Users users;//1：1  关联
	
	//额外创建多一个属性    一个用户有多个订单 
	//包含对订单项的内容
	//一个订单可以关联多个订单项
	List<OrderItem> orderItem = new ArrayList<OrderItem>();

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

	public Order(String oid, Date orderTime, int state, double total, String address, String name, String telephone,
			Users users, List<OrderItem> orderItem) {
		super();
		this.oid = oid;
		this.orderTime = orderTime;
		this.state = state;
		this.total = total;
		this.address = address;
		this.name = name;
		this.telephone = telephone;
		this.users = users;
		this.orderItem = orderItem;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", orderTime=" + orderTime + ", state=" + state + ", total=" + total + ", address="
				+ address + ", name=" + name + ", telephone=" + telephone + ", users=" + users + ", orderItem="
				+ orderItem + "]";
	}
	
	

}
