package com.snakegame.control;

import com.snakegame.entity.Food;
import com.snakegame.entity.Ground;
import com.snakegame.entity.Snake;
import com.snakegame.listener.SnakeListener;
import com.snakegame.view.GamePanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by 肖安华 on java.
 * 控制器类
 */
public class Controller extends KeyAdapter implements SnakeListener {
    private Snake snake;
    private Food food;
    private Ground ground;
    private GamePanel gamePanel;

    public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
        this.snake = snake;
        this.food = food;
        this.gamePanel = gamePanel;
        this.ground = ground;
    }

    /*
    * 监听键盘按键
    * */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                snake.changeDirection(Snake.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.changeDirection(Snake.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.changeDirection(Snake.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.changeDirection(Snake.RIGHT);
                break;
        }
    }

    /*
    * 判断蛇移动
    * */
    @Override
    public void snakeMoved(Snake snake) {
        //显示蛇身体，食物，障碍物
        gamePanel.display(snake, food, ground);
        if (food.isEatBySnake(snake)) {
            snake.eatFood();
            food.getPoint();
        }
        if (ground.isEatBySnake(snake)) {
            this.falid();
        }
        if (snake.isEatBySnake()) {
            this.falid();
        }
    }

    /*
    *控制游戏的开始
    */
    public void startGame() {
        ground.getFood();
        snake.start();

    }

    /*
    * 游戏失败
    * */
    public void falid() {
        snake.setLife(false);
        int mess = JOptionPane.showConfirmDialog(null, "是否继续？", "提示：", JOptionPane.YES_NO_OPTION);
        if (mess == 0) {
            snake.body.clear();
            snake.setLife(true);
            snake.init();
        } else if (mess == 1) {
            System.exit(0);
        }
    }
}

