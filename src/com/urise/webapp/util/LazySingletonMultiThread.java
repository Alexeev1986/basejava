package com.urise.webapp.util;

public class LazySingletonMultiThread {
    private static volatile LazySingletonMultiThread INSTANCE;

    private LazySingletonMultiThread() {
    }

    public static LazySingletonMultiThread getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingletonMultiThread.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazySingletonMultiThread();
                }
            }
        }
        return INSTANCE;
    }
}
