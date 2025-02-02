package com.linxuwu.tank;

import lombok.Getter;
import lombok.Setter;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.awt.*;
import java.util.Iterator;
import java.util.Random;

/**
 * 封装：合适的方法放在合适的类中
 */
@Getter
@Setter
public class Tank {

    int x, y;
    Dir dir = Dir.DOWN;
    private static final int SPEED = 2;

    Rectangle rect = new Rectangle();

    private boolean moving = true;
    TankFrame tf = null;
    private Random random = new Random();
    Group group = Group.BAD;
    private boolean living = true;

    public static final int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankD.getHeight();


    FireStrategy fireStrategy;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        if(group == Group.GOOD) {
            /**
             * Class.forName()....配置文件
             */
            fireStrategy = new FourDirFireStrategy();
        }else {
            fireStrategy = new DefaultStrategy();
        }
    }

    public void paint(Graphics g) {
        Iterator<Tank> it = tf.tanks.iterator();
        while (it.hasNext()) {
            Tank tank = it.next();
            if(!tank.isLiving()) {
                it.remove();
            }
        }
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        if(!moving) {return;}

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }


        if(this.group == Group.BAD && random.nextInt(100) > 95){
            this.fire(fireStrategy);
        }
        if(this.group == Group.BAD && random.nextInt(10) > 8) {
            randomDir();
        }

        boundsCheck();

        //update rect
        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 2) {x = 2;}
        if(this.y < 28) {y = 28;}
        if(this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) {x = TankFrame.GAME_WIDTH - Tank.WIDTH;}
        if(this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) {x = TankFrame.GAME_HEIGHT - Tank.HEIGHT;}

    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }


    /**
     * fire(FireStrategy s) -> 每次调用，都需要new，因此，应该把DefaultStrategy -> Singleton
     * 成员变量
     * @param fireStrategy
     */
    public void fire(FireStrategy fireStrategy) {
        fireStrategy.fire(this);
    }

    public void die() {
        this.living = false;
    }
}
