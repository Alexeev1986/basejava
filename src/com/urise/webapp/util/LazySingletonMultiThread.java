package com.urise.webapp.util;

public class LazySingletonMultiThread {
    private static volatile LazySingletonMultiThread instance;

    private LazySingletonMultiThread() {
    }

    public static LazySingletonMultiThread getInstance() {
        if (instance == null) {
            synchronized (LazySingletonMultiThread.class) {
                if (instance == null) {
                    instance = new LazySingletonMultiThread();
                }
            }
        }
        return instance;
    }
}
