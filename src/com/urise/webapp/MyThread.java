package com.urise.webapp;

import java.util.concurrent.CountDownLatch;

public class MyThread extends Thread {
    private final Object firstLock;
    private final Object secondLock;
    private final CountDownLatch latch;

    public MyThread(Object firstLock, Object secondLock, CountDownLatch latch) {
        this.firstLock = firstLock;
        this.secondLock = secondLock;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " start");
        synchronized (firstLock) {
            System.out.println(Thread.currentThread().getName() + " Synchronized lock1");
            latch.countDown();
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (secondLock) {
                System.out.println(Thread.currentThread().getName() + " Synchronized secondLock");
            }
        }
        System.out.println(Thread.currentThread().getName() + "end");
    }
}
