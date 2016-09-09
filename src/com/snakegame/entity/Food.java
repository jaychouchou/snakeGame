package com.snakegame.entity;

import com.snakegame.util.Global;

import java.awt.*;

/**
 * Created by 肖安华 on java.
 * 贪吃蛇的食物
 */
public class Food {
   Point point=new Point(3,3);

   public void getPoint(){
        this.point=new Ground().getFood();
    }
    /*
    * 画出食物
    * */
    public void drawMe(Graphics graphics){
        graphics.setColor(Color.red);
        graphics.fill3DRect(point.x*Global.CELL_SIZE,point.y*Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE,true);
    }
    /*
    * 是否被贪吃蛇碰到
    * */
    public boolean isEatBySnake(Snake snake){
            if (snake.body.getFirst().equals(point)) {
                return true;
            }

        return false;
    }

}
