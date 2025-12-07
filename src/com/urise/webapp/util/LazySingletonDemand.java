package com.urise.webapp.util;

public class LazySingletonDemand {
    volatile private static LazySingletonDemand INSTANCE;

    private LazySingletonDemand() {
    }

   private static class LazySingletonDemandHolder {
        private static final LazySingletonDemand INSTANCE = new LazySingletonDemand();
   }

   public static LazySingletonDemand getInstance() {
        return LazySingletonDemandHolder.INSTANCE;
   }
}
