package com.ccnu.test;

import java.io.File;

public class FileTest {

	public static void main(String[] args) {
		try {
			File dest = new File("c:/test/dir/test/5678/test.txt");
            dest.getParentFile().mkdirs();
            dest.createNewFile();
		} catch (Exception e) {
		}
	}
}
