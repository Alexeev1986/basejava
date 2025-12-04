package com.urise.webapp;

public class MyDeadLock {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " Synchronized lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " Synchronized lock2");
                }
            }
            System.out.println(Thread.currentThread().getName() + "end");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "start");

            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + " Synchronized lock2");
                synchronized (lock1) {
                    System.out.println(Thread.currentThread().getName() + " Synchronized lock1");
                }
            }
            System.out.println(Thread.currentThread().getName() + "end");
        });

        thread1.start();
        thread2.start();
    }
}
