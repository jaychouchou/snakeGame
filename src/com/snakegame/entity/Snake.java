package com.snakegame.entity;

import com.snakegame.listener.SnakeListener;
import com.snakegame.util.Global;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by 肖安华 on java.
 * 贪吃蛇
 */
public class Snake {
    private SnakeListener snakeListener;
    public LinkedList<Point> body = new LinkedList<>();
    public static final int UP = 1;
    public static final int DOWN = -1;
    public static final int RIGHT = 2;
    public static final int LEFT = -2;
    private int oldDirection, newDirection;//存储蛇的前进方向
    private Point tail;//蛇的尾部的坐标
    private boolean life ;//判断蛇的生命

   /* public Snake() {
        init();
    }*/

    /*
    * 获取蛇的身体
    * */
    public void init() {
        int x = Global.WIDTH / 2;
        int y = Global.HEIGHT / 2;
        for (int i = 0; i < 3; i++) {
            body.add(new Point(x - i, y));
        }
        this.oldDirection = this.newDirection = RIGHT;
    }

    /*
    * 设置蛇的生命
    * */
    public void setLife(Boolean life) {
        this.life = life;
    }

    /*
    * 画出贪吃蛇
    * */
    public void drawMe(Graphics graphics) {
        graphics.setColor(Color.blue);
        for (Point p : body) {
            graphics.fill3DRect(p.x * Global.CELL_SIZE, p.y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
        }

    }

    /*
    * 贪吃蛇移动
    * */
    public void move() {
        //去尾
        this.tail = body.removeLast();
        //加头---->根据方向添加头部
        //获得当前的头部
        int x = body.getFirst().x;
        int y = body.getFirst().y;
        /*
        * 获得新的头部要确定方向才知道，在初始化构造
        * 蛇身的时候默认方向是向又的
        * */
        if (newDirection + oldDirection != 0)
            oldDirection = newDirection;
        switch (oldDirection) {
            case UP:
                y--;
                if (y < 0) y = Global.HEIGHT - 1;
                break;
            case DOWN:
                y++;
                if (y == Global.HEIGHT) y = 0;
                break;
            case LEFT:
                x--;
                if (x < 0) x = Global.WIDTH - 1;
                break;
            case RIGHT:
                x++;
                if (x == Global.WIDTH) x = 0;
                break;
        }

        body.addFirst(new Point(x, y));

    }

    /*
    * 是否碰到自己
    * */
    public boolean isEatBySnake() {
        for (int i=1;i<body.size();i++){
            if (body.get(i).equals(body.getFirst()))
                return true;
        }
        return false;
    }

    /*
    * 贪吃蛇改变方向
    * */
    public void changeDirection(int direction) {
        this.newDirection = direction;
    }

    /*
    * 贪吃蛇吃食物
    * */
    public void eatFood() {
        body.addLast(tail);

    }

    /*
    * 添加蛇移动的监听器
    * */
    public void addSnakeListener(SnakeListener snakeListener) {
        if (snakeListener != null) {
            this.snakeListener = snakeListener;
        }
    }

    public void start() {
        this.life=true;
        init();
        new StartDriver().start();

    }

    /*
    * 线程类控制游戏的开始
    * */
    private class StartDriver extends Thread {
        @Override
        public void run() {
            while (life) {
                move();
                snakeListener.snakeMoved(Snake.this);
                //每移动一次睡一秒钟
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
