package com.ccnu.test.threads;

import java.util.concurrent.CountDownLatch;

public class ThreadTest {
	public static void main(String[] args) throws Exception {
		int loop = 10000;
		
		CountDownLatch countDownLatch1 = new CountDownLatch(100);

		long before = System.currentTimeMillis();
		for(int i=0;i<loop;i++){
			new ThreadExtendThread(countDownLatch1).start();
		}
		countDownLatch1.await();
		long after = System.currentTimeMillis();
		System.out.println("继承Thread方式共耗时"+(after-before)+"ms!");
		CountDownLatch countDownLatch2 = new CountDownLatch(100);
		before = System.currentTimeMillis();
		for(int i=0;i<loop;i++){
			new Thread(new ThreadImplRunnable(countDownLatch2)).start();
		}
		countDownLatch2.await();
		after = System.currentTimeMillis();
		System.out.println("实现Runnable接口方式共耗时"+(after-before)+"ms!");
	}
}

class ThreadExtendThread extends Thread {
	private CountDownLatch countDownLatch;

	public ThreadExtendThread(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		count();
		countDownLatch.countDown();
	}

	private void count() {
		for (int i = 0; i < 10000000; i++) {
		}
	}
}

class ThreadImplRunnable implements Runnable {
	private CountDownLatch countDownLatch;

	public ThreadImplRunnable(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	private void count() {
		for (int i = 0; i < 10000000; i++) {
		}
	}

	@Override
	public void run() {
		count();
		countDownLatch.countDown();

	}

}