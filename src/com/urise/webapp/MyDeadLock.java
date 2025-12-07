package com.urise.webapp;

import java.util.concurrent.CountDownLatch;

public class MyDeadLock {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        CountDownLatch latch = new CountDownLatch(2);
        MyThread thread11 = new MyThread(lock1, lock2, latch);
        MyThread thread12 = new MyThread(lock2, lock1, latch);
        thread11.start();
        thread12.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}

class MyThread extends Thread {
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
