package com.lanqiao.shopping.test;

import com.lanqiao.shopping.utils.MD5Util;
import com.lanqiao.shopping.utils.UUIDUtils;

public class Test {
	public static void main(String[] args) {
		//随机产生64位UUID
		for (int i = 0; i < 2; i++) {
			//System.out.println(UUIDUtils.getUUID64());
			System.out.println(UUIDUtils.getCode());
		}
		
		String[] str = new String[10];
		int a = str.length;
		System.out.println(a);
		
		
		System.out.println("=============");
		String str2 = "1234";
		String md5Str = MD5Util.string2MD5(str2);
		System.out.println(md5Str);
		
		String ab = MD5Util.convertMD5(md5Str);
		String abc = MD5Util.convertMD5(MD5Util.convertMD5(md5Str));
		System.out.println(abc);
	}
}
