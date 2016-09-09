package com.snakegame.test;

import com.snakegame.control.Controller;
import com.snakegame.entity.Food;
import com.snakegame.entity.Ground;
import com.snakegame.entity.Snake;
import com.snakegame.util.Global;
import com.snakegame.view.GamePanel;

import javax.swing.*;

/**
 * Created by 肖安华 on java.
 * 测试类
 */
public class Test {
    public static void main(String arg[]){
        //创建实体类的对象
        Snake snake=new Snake();
        Food food=new Food();
        Ground ground=new Ground();

        //创建视图的对象
        GamePanel gamePanel=new GamePanel();

        //控制器的创建----->既是蛇的监听，又是键盘的监听
        Controller controller=new Controller(snake,food,ground,gamePanel);

        snake.addSnakeListener(controller);
        gamePanel.addKeyListener(controller);


        JFrame jFrame=new JFrame("贪吃蛇version1.0");
        jFrame.setSize(Global.WIDTH*Global.CELL_SIZE+100,Global.HEIGHT*Global.CELL_SIZE+100);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        //让面板获得焦点
        gamePanel.setFocusable(true);
        //设置窗体不可改变
        jFrame.setResizable(false);
        jFrame.add(gamePanel);
        //启动游戏
        controller.startGame();
        //显示窗体
        jFrame.setVisible(true);

    }

}
