package com.lanqiao.shopping.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 1-2、购物车（Map、总金额）
			添加购物对象
			删除购物对象
			清空购物对象
 * @author Administrator
 *
 */
public class Cart {
	//键：传递的是商品的主键pid    值：对应的是购物车项
	private Map<String,CartItem> map = new HashMap<String,CartItem>();
	//总金额
	private double total;
	
	//添加购物对象
	//如果购物车中没有指定商品，就此生成新商品对象
	//如果购物车已经有了指定商品，则修改商品的数量
	public void addCart(CartItem cartItem) {
		//获得商品的pid
		String pid = cartItem.getProduct().getPid();
		//判断map集合中是否存在pid的对象
		if(map.containsKey(pid)) {
			//存在:改数量  从map中找对应的对象
			CartItem oldCartItem = map.get(pid);
			//改变的数量是现货区原来旧的购物车项里面的旧的数量在加上新的数量
			oldCartItem.setNum(oldCartItem.getNum()+cartItem.getNum());
		}else {
			//不存在:添加
			map.put(pid, cartItem);
		}
	}
	
	//删除购物对象(根据pid来删除)
	public void delCart(String pid) {
		map.remove(pid);
	}
	
	//清空购物对象
	public void clearCart() {
		map.clear();
	}
	
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	
	//总金额=购物车项小计的总和
	public double getTotal() {
		total = 0;
		//遍历map集合获取购物车项中的小计,然后累加   map.values  取得是CartItem
		for(CartItem cartItem:map.values()) {
			total += cartItem.getSubTotal();
		}
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	public Cart(Map<String, CartItem> map, double total) {
		super();
		this.map = map;
		this.total = total;
	}
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
