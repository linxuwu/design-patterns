package com.linxuwu.tank;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt((String) PropertyMgr.getInstance().get("initTankCount"));

        //初始化地方坦克
        for (int i =0; i < initTankCount; i++) {
            tf.tanks.add(new Tank(50 + i * 60, 200, Dir.DOWN, Group.BAD, tf));
        }

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
