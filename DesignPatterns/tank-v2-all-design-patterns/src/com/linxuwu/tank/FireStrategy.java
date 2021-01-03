package com.linxuwu.tank;

public interface FireStrategy {
    /**
     * 参数tank能指定子弹位置在哪
     * @param tank
     */
    void fire(Tank tank);
}
