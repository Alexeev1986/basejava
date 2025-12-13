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


