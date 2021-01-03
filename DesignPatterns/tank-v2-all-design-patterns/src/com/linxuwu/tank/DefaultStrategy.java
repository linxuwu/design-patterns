package com.linxuwu.tank;

public class DefaultStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        new Bullet(bX, bY, tank.dir, tank.group, tank.tf);
    }
}
