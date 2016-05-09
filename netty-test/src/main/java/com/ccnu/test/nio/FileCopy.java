package com.ccnu.test.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileCopy {
	private static final Log log = LogFactory.getLog(FileCopy.class);

	public static void main(String[] args) throws Exception {
		long before = System.currentTimeMillis();
		FileInputStream src = new FileInputStream(new File("d:/src.txt"));
		FileOutputStream des = new FileOutputStream(new File("d:/des.txt"));
		FileChannel finChannel = src.getChannel();
		FileChannel foutChannel = des.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(1024);
		// 上面的语句等价于下面的语句
		// byte array[] = new byte[1024];
		// ByteBuffer buffer = ByteBuffer.wrap( array );

		while (true) {
			// clear方法将limit设置为capacity的值，将position设置为0
			buffer.clear();
			int r = finChannel.read(buffer);

			if (r == -1) {
				break;
			}
			// flip方法将buffer的limit设置为当前position的值，将position的值设置为0，以方便数据从buffer写入到通道中
			buffer.flip();
			foutChannel.write(buffer);
		}

		src.close();
		des.close();
		long after = System.currentTimeMillis();
		log.info("共耗时" + (after - before) + "ms!");
	}
}
