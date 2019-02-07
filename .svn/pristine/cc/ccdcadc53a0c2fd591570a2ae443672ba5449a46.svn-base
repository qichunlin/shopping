package com.lanqiao.shopping.test;

import java.util.List;

import redis.clients.jedis.Jedis;

public class redisTest {
	public static void main(String[] args) {
		//创建jedis对象，连接redis的服务   redis相当于服务器
		Jedis jedis = new Jedis("localhost");
		System.out.println("连接成功");
		
		
		//列表的演示
		jedis.lpush("names","张三");//添加数据  在尾部
		jedis.lpush("names","李四");
		jedis.lpush("names","王五");
		
		//获取集合数据
		List<String> str = jedis.lrange("names", 0, jedis.llen("names"));
		for (int i = 0; i < str.size(); i++) {
			System.out.println(str.get(i));
		}
		
		/*//使用redis中的String实例
		jedis.set("address", "广西");
		
		//读取redis中的字符串的内容
		String value = jedis.get("address");
		System.out.println(value);*/
	}
}
