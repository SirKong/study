package com.ccnu.test.threads;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {
	public static void main(String[] args) {
		// 创建固定线程数的线程池
		// new ExecutorTest().testFixedThreadPool();
		// 创建单个线程数的线程池
		// new ExecutorTest().testSingleThreadExecutor();
		// 创建可变尺寸线程池
		// new ExecutorTest().testCachedThreadPool();
		// 创建多任务延迟线程池
		// new ExecutorTest().testScheduledThreadPool();
		// 创建单任务延迟线程池
		// new ExecutorTest().testSingleThreadScheduledExecutor();
		// 创建自定义线程池
		new ExecutorTest().testCustomThreadPool();
	}

	/**
	 * 创建固定线程数的线程池
	 */
	public void testFixedThreadPool() {
		// 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newFixedThreadPool(2);
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// 关闭线程池
		pool.shutdown();
	}

	/**
	 * 创建单个线程数的线程池
	 */
	public void testSingleThreadExecutor() {
		// 创建一个使用单个 worker 线程的 Executor，以无界队列方式来运行该线程。
		ExecutorService pool = Executors.newSingleThreadExecutor();
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// 关闭线程池
		pool.shutdown();
	}

	/**
	 * 创建可变尺寸线程池
	 */
	public void testCachedThreadPool() {
		// 创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
		ExecutorService pool = Executors.newCachedThreadPool();
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		// 关闭线程池
		pool.shutdown();
	}

	/**
	 * 创建多任务延迟线程池
	 */
	public void testScheduledThreadPool() {
		// 创建一个线程池，它可安排在给定延迟后运行命令或者定期地执行。
		ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		// 使用延迟执行风格的方法
		pool.schedule(t4, 3000, TimeUnit.MILLISECONDS);
		pool.schedule(t5, 3000, TimeUnit.MILLISECONDS);
		// 关闭线程池
		pool.shutdown();
	}

	/**
	 * 创建单任务延迟线程池
	 */
	public void testSingleThreadScheduledExecutor() {
		// 创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。
		ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		// 将线程放入池中进行执行
		pool.execute(t1);
		// 使用延迟执行风格的方法
		pool.schedule(t2, 3000, TimeUnit.MILLISECONDS);
		pool.schedule(t3, 3000, TimeUnit.MILLISECONDS);
		pool.schedule(t4, 3000, TimeUnit.MILLISECONDS);
		pool.schedule(t5, 3000, TimeUnit.MILLISECONDS);
		// 关闭线程池
		pool.shutdown();
	}

	/**
	 * 自定义线程池
	 */
	public void testCustomThreadPool() {
		// 创建等待队列
		BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);
		// 创建一个单线程执行程序，它可安排在给定延迟后运行命令或者定期地执行。
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 2, TimeUnit.MILLISECONDS, bqueue);
		// 创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口
		Thread t1 = new MyThread();
		Thread t2 = new MyThread();
		Thread t3 = new MyThread();
		Thread t4 = new MyThread();
		Thread t5 = new MyThread();
		Thread t6 = new MyThread();
		Thread t7 = new MyThread();
		// 将线程放入池中进行执行
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		pool.execute(t6);
		pool.execute(t7);
		// 关闭线程池
		pool.shutdown();
	}
}

class MyThread extends Thread {
	@Override
	public void run() {
		// 这里每次执行都打印出线程Id并random一下，因为有线程池在，可能前后两次线程执行都是同一个线程在执行（线程Id一样），但是random的数值却是不一样的
		System.out.println(Thread.currentThread().getName() + "正在执行。。。" + new Random().nextInt() + " 当前系统毫秒数[" + System.currentTimeMillis() + "]");
	}
}