package com.school.util;

import java.util.Random;

public class RandomString {

	public static String RandomS(int Random){
		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random1=new Random();
		//指定字符串长度，拼接字符并toString
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < Random; i++) {
		//获取指定长度的字符串中任意一个字符的索引值
		int number=random1.nextInt(str.length());   
		//根据索引值获取对应的字符
		char charAt = str.charAt(number);
		    sb.append(charAt);
		}
		return sb.toString();
	}
}
