package com.ccnu.test.juc;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private static int index = 0;

    static ReentrantLock lock = new ReentrantLock();

    static class CountThread implements Runnable {
        private int threadIndex;

        public CountThread(int threadIndex) {
            this.threadIndex = threadIndex;
        }

        public void run() {
            try {
                lock.lock();
                int before = index;
                index++;
                System.out.println("线程[" + threadIndex + "]执行操作前后值分别为[" + before + "]和[" + index + "]");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new CountThread(i)).start();
        }
    }

}
