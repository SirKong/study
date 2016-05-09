package com.ccnu.test.poi;

import com.ccnu.test.poi.model.APIDescriptor;
import com.ccnu.test.poi.util.APIExcelReader;
import com.ccnu.test.poi.util.APIExcelWriter;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Client {

	public static void main(String[] args) throws Exception {
		test3();
	}

	public static void test1() throws Exception{
		List<APIDescriptor> apiDescriptorList = APIExcelReader.readAPIDescriptors("4.0API接口文档.xls");
		APIExcelWriter.write(apiDescriptorList, "D:\\", "4.0API接口文档(排序后).xls");
		Map<String,String> apiMap = new TreeMap<String,String>();
		for(APIDescriptor apiDescriptor:apiDescriptorList){
			apiMap.put(apiDescriptor.getFunctionNo(),apiDescriptor.getFunctionDesc());
		}
		System.out.println(apiMap);
	}
	public static void test2() throws Exception {
		List<APIDescriptor> apiDescriptorList = APIExcelReader.readAPIDescriptors("4.0API导入模板(已弃用_组合投资).xls");
		APIExcelWriter.write(apiDescriptorList, ".", "4.0API接口文档(排序后).xls");
		Map<String,String> apiMap = new TreeMap<String,String>();
		for(APIDescriptor apiDescriptor:apiDescriptorList){
			apiMap.put(apiDescriptor.getFunctionNo(),apiDescriptor.getFunctionDesc());
		}
		System.out.println(apiMap);
	}
	public static void test3() throws Exception {
		List<APIDescriptor> apiDescriptorList = APIExcelReader.readAPIDescriptors("4.0API导入模板(查询类往后).xls");
		APIExcelWriter.write(apiDescriptorList, ".", "4.0API接口文档(排序后).xls");
		Map<String,String> apiMap = new TreeMap<String,String>();
		for(APIDescriptor apiDescriptor:apiDescriptorList){
			apiMap.put(apiDescriptor.getFunctionNo(),apiDescriptor.getFunctionDesc());
		}
		System.out.println(apiMap);
	}
}
