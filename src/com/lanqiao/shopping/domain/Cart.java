package com.lanqiao.shopping.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 1-2�����ﳵ��Map���ܽ�
			��ӹ������
			ɾ���������
			��չ������
 * @author Administrator
 *
 */
public class Cart {
	//�������ݵ�����Ʒ������pid    ֵ����Ӧ���ǹ��ﳵ��
	private Map<String,CartItem> map = new HashMap<String,CartItem>();
	//�ܽ��
	private double total;
	
	//��ӹ������
	//������ﳵ��û��ָ����Ʒ���ʹ���������Ʒ����
	//������ﳵ�Ѿ�����ָ����Ʒ�����޸���Ʒ������
	public void addCart(CartItem cartItem) {
		//�����Ʒ��pid
		String pid = cartItem.getProduct().getPid();
		//�ж�map�������Ƿ����pid�Ķ���
		if(map.containsKey(pid)) {
			//����:������  ��map���Ҷ�Ӧ�Ķ���
			CartItem oldCartItem = map.get(pid);
			//�ı���������ֻ���ԭ���ɵĹ��ﳵ������ľɵ������ڼ����µ�����
			oldCartItem.setNum(oldCartItem.getNum()+cartItem.getNum());
		}else {
			//������:���
			map.put(pid, cartItem);
		}
	}
	
	//ɾ���������(����pid��ɾ��)
	public void delCart(String pid) {
		map.remove(pid);
	}
	
	//��չ������
	public void clearCart() {
		map.clear();
	}
	
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	
	//�ܽ��=���ﳵ��С�Ƶ��ܺ�
	public double getTotal() {
		total = 0;
		//����map���ϻ�ȡ���ﳵ���е�С��,Ȼ���ۼ�   map.values  ȡ����CartItem
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
