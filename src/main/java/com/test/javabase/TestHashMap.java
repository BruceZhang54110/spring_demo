package com.test.javabase;

import java.util.HashMap;
import java.util.Map;

public class TestHashMap {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1000", "test01");
		
		String a = "hello";
		String b = "hello";
		System.out.println(a==b);
		
		String c = new String("hello");
		String d = new String("hello");
		System.out.println(c==d);
	}
}
