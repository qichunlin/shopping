package com.lanqiao.shopping.domain;

/**
 * 1-1、购物车项（图片、商品名称、价格、数量、小计）
 * 
 *通过商品对象、数量、小计
 * @author Administrator
 *
 */
public class CartItem {
	//定义商品对象
	private Product product;
	//定义数量
	private int num;
	//定义小计
	private double subTotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	//小计是通过计算得到的：商品数量*商品价格
	public double getSubTotal() {
		subTotal = num*product.getSHOP_PRICE();
		return subTotal;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	public CartItem(Product product, int num, double subTotal) {
		super();
		this.product = product;
		this.num = num;
		this.subTotal = subTotal;
	}
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CartItem [product=" + product + ", num=" + num + ", subTotal=" + subTotal + "]";
	}
	
	
}
