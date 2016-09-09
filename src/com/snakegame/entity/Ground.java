package com.snakegame.entity;

import com.snakegame.util.Global;

import java.awt.*;
import java.util.Random;

/**
 * Created by 肖安华 on java.
 * 障碍物
 */
public class Ground {

    int[][] rocks = new int[Global.WIDTH][Global.HEIGHT];

    public Ground(){
        for (int y = 0; y < Global.HEIGHT; y++) {
            for (int x = 0; x < Global.WIDTH; x++) {
                if (y == 0 || y == Global.HEIGHT - 1||x==0||x==Global.WIDTH-1){
                    rocks[y][x]=1;
               }
            }
        }
    }

    /*
    * 画出障碍物
    * */
    public void drawMe(Graphics graphics) {
        graphics.setColor(Color.yellow);
        for (int y = 0; y < Global.HEIGHT; y++) {
            for (int x = 0; x < Global.WIDTH; x++) {
                if (rocks[y][x]==1)
                    graphics.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
            }

        }

    }

    /*
    * 是否被贪吃蛇碰到
    * */
    public boolean isEatBySnake(Snake snake) {
        Point head=snake.body.getFirst();
        for (int y = 0; y < Global.HEIGHT; y++) {
            for (int x = 0; x < Global.WIDTH; x++) {
            if (rocks[y][x]==1&&head.getX()==x&&head.getY()==y)
                return true;
            }
        }
        return false;
    }
    public Point getFood(){
        int x,y;
        do {
            x = new Random().nextInt(Global.WIDTH);
            y = new Random().nextInt(Global.HEIGHT);
        }while (rocks[x][y]==1);
        return new Point(x,y);
    }
}
