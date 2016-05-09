package com.ccnu.test;

import java.util.HashMap;
import java.util.Map.Entry;

public class Snippet {
	public static void main(String[] args) {

		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("语文", 1);
		map.put("数学", 2);
		map.put("英语", 3);
		map.put("历史", 4);
		map.put(null, 100);
		map.put("政治", 5);
		map.put("地理", 6);
		map.put("生物", 7);
		map.put("化学", 8);
		map.put(null, 99);
		for (Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}
}
