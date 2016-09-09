package com.snakegame.view;

import com.snakegame.entity.Food;
import com.snakegame.entity.Ground;
import com.snakegame.entity.Snake;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 肖安华 on java.
 * 贪吃蛇游戏的面板，主界面
 */
public class GamePanel extends JPanel{
    private Snake snake;
    private Food food;
    private  Ground ground;
    /*
     * 显示贪吃蛇，食物，障碍物
     */
    public void display(Snake snake, Food food,Ground ground){
        this.snake=snake;
        this.food=food;
        this.ground=ground;
        repaint();
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(snake!=null&&food!=null&&ground!=null){
            snake.drawMe(g);
            food.drawMe(g);
            ground.drawMe(g);
        }
    }
}
