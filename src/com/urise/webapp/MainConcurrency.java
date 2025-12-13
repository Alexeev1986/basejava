package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static final Object LOCK = new Object();
    public static final int THREADS_NUMBER = 10000;
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main: " + Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println("Thread thread0: " + getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(() -> System.out.println("new Thread(new Runnable() {: " +
                Thread.currentThread().getName() + ", " + Thread.currentThread().getState())).start();
        System.out.println("thread0.getState(): " + thread0.getState());
        Thread.sleep(100);
        System.out.println("thread0.getState(): " + thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                }
            });
            thread.start();
            threads.add(thread);
        }
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(counter);
    }

    private synchronized void inc() {
        counter++;
    }
}
