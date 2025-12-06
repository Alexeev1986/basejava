package com.urise.webapp;

public class MyDeadLock {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        MyThread thread11 = new MyThread(lock1, lock2, 200);
        MyThread thread12 = new MyThread(lock2, lock1, 0);
        thread11.start();
        thread12.start();
    }
}

class MyThread extends Thread {
    private final Object firstLock;
    private final Object secondLock;
    private final int sleepTime;

    public MyThread(Object firstLock, Object secondLock, int sleepTime) {
        this.firstLock = firstLock;
        this.secondLock = secondLock;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " start");
        synchronized (firstLock) {
            System.out.println(Thread.currentThread().getName() + " Synchronized lock1");
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            synchronized (secondLock) {
                System.out.println(Thread.currentThread().getName() + " Synchronized secondLock");
            }
        }
        System.out.println(Thread.currentThread().getName() + "end");
    }
}
