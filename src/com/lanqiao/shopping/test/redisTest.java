package com.lanqiao.shopping.test;

import java.util.List;

import redis.clients.jedis.Jedis;

public class redisTest {
	public static void main(String[] args) {
		//����jedis��������redis�ķ���   redis�൱�ڷ�����
		Jedis jedis = new Jedis("localhost");
		System.out.println("���ӳɹ�");
		
		
		//�б����ʾ
		jedis.lpush("names","����");//�������  ��β��
		jedis.lpush("names","����");
		jedis.lpush("names","����");
		
		//��ȡ��������
		List<String> str = jedis.lrange("names", 0, jedis.llen("names"));
		for (int i = 0; i < str.size(); i++) {
			System.out.println(str.get(i));
		}
		
		/*//ʹ��redis�е�Stringʵ��
		jedis.set("address", "����");
		
		//��ȡredis�е��ַ���������
		String value = jedis.get("address");
		System.out.println(value);*/
	}
}
