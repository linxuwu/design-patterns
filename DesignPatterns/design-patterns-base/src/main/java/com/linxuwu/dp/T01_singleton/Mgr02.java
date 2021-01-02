package com.linxuwu.dp.T01_singleton;

public class Mgr02 {

    private Mgr02() {}

    private static class Mgr02Holder {
        private static final Mgr02 MGR = new Mgr02();
    }

    public static Mgr02 getInstance() {
        return Mgr02Holder.MGR;
    }

    public static void main(String[] args) {
        final int threadCount = 100;
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(()->{
                System.out.println(Mgr02.getInstance().hashCode());
            });
        }
        for (int i = 0; i < threadCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }
    }

}
