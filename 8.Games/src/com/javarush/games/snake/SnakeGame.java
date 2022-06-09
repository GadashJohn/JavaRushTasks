package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {

    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL = 28;

    private boolean isGameStopped;
    private int turnDelay;
    private Snake snake;
    private Apple apple;

    private void drawScene() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellValueEx(i, j, Color.DARKSEAGREEN, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }

    private void createGame() {
        snake = new Snake(WIDTH / 2, HEIGHT / 2);
        createNewApple();
        isGameStopped = false;
        drawScene();
    }

    private  void createNewApple() {
        while (true) {
            apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
            if (!snake.checkCollision(apple)) {
                return;
            }
        }
    }

    private  void  gameOver() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE,"Game over",Color.RED,75);
    }

    private void win() {
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.NONE,"You win!",Color.GREEN,75);
    }

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);

    }

    @Override
    public void onTurn(int step) {
        snake.move(apple);
        if (!apple.isAlive) {
            createNewApple();
        }
        if (!snake.isAlive) {
            gameOver();
        }
        if(snake.getLength() > GOAL){
            win();
        }
        drawScene();
    }


    @Override
    public void onKeyPress(Key key) {
        if(key.equals(Key.SPACE) && isGameStopped) {
          createGame();
        } else if(key.equals(Key.LEFT)){
            snake.setDirection(Direction.LEFT);
        } else  if(key.equals(Key.RIGHT)){
            snake.setDirection(Direction.RIGHT);
        } else  if(key.equals(Key.UP)){
            snake.setDirection(Direction.UP);
        } else if(key.equals(Key.DOWN)) {
            snake.setDirection(Direction.DOWN);
        }
    }
}
