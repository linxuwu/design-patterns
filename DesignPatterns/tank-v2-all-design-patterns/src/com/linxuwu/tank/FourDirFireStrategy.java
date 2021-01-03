package com.linxuwu.tank;

public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            new Bullet(bX, bY, tank.dir, tank.group, tank.tf);
        }
    }
}
