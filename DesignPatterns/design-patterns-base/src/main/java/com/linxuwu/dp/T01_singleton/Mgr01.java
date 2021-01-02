package com.linxuwu.dp.T01_singleton;

/**
 * 饿汉式
 */
public class Mgr01 {

    private static final Mgr01 MGR = new Mgr01();

    private Mgr01() {}

    public static Mgr01 getInstance() {
        return MGR;
    }

    public void fn() {
        System.out.println("hello");
    }

    public static void main(String[] args) {

        final int threadCount = 100;
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(()->{
                System.out.println(Mgr01.getInstance().hashCode());
                Mgr01.getInstance().fn();
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
