package com.ccnu.test;

import java.util.Iterator;
import java.util.Map;

//-Xss100k
public class StackTest {
	private int num = 1;
	
	public void recursion(){
		num++;
		recursion();
	}
	
	public static void main(String[] args) throws Throwable{
//		Map<String,String> maps = System.getenv();
//		Iterator<String> it = maps.keySet().iterator();
//		while(it.hasNext()){
//			String key =it.next();
//			System.out.println(key+" : "+maps.get(key));
//		}
		
		StackTest test = new StackTest();
		try{
			test.recursion();
		}catch(Throwable e){
			System.out.println(test.num);
			e.printStackTrace();
		}
	}
}