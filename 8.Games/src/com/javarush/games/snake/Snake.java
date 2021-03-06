package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Snake {
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    private List<GameObject> snakeParts = new ArrayList<>();
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    public void setDirection(Direction direction) {
        boolean flagX = snakeParts.get(0).x == snakeParts.get(1).x;
        boolean flagY = snakeParts.get(0).y == snakeParts.get(1).y;

        if ((this.direction.equals(Direction.LEFT) || this.direction.equals(Direction.RIGHT)) && flagX) {
            return;
        }  
        if ((this.direction.equals(Direction.UP) || this.direction.equals(Direction.DOWN)) && flagY) {
            return;
        }

        if (this.direction.equals(Direction.LEFT) && direction.equals(Direction.RIGHT)) {
            return;
        } else if (this.direction.equals(Direction.UP) && direction.equals(Direction.DOWN)) {
            return;
        } else if (this.direction.equals(Direction.RIGHT) && direction.equals(Direction.LEFT)) {
            return;
        } else if (this.direction.equals(Direction.DOWN) && direction.equals(Direction.UP)) {
            return;
        }
        this.direction = direction;
    }

    public Snake(int x, int y) {
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void draw(Game game) {
        Color colorSnake = isAlive ? Color.BLACK : Color.RED;
        for (int i = 0; i < snakeParts.size(); i++) {
            String string = i == 0 ? HEAD_SIGN : BODY_SIGN;
            GameObject snakePart = snakeParts.get(i);
            game.setCellValueEx(snakePart.x, snakePart.y,Color.NONE, string,colorSnake,75 );
        }
    }

    public void move(Apple apple) {
        GameObject newHead = createNewHead();
        if (newHead.x < 0 || newHead.x >= SnakeGame.WIDTH  || newHead.y < 0 || newHead.y >= SnakeGame.HEIGHT) {
            isAlive = false;
            return;
        }
        if (checkCollision(newHead)){
            isAlive = false;
            return;
        }
        snakeParts.add(0, newHead);
        if (newHead.x == apple.x && newHead.y == apple.y) {
            apple.isAlive = false;
        } else {
            removeTail();
        }
    }

    public GameObject createNewHead() {
        GameObject oldHead = snakeParts.get(0);
        if (direction.equals(Direction.LEFT)) {
            return new GameObject(oldHead.x - 1, oldHead.y);
        } else if (direction.equals(Direction.RIGHT)) {
            return new GameObject(oldHead.x + 1, oldHead.y);
        } else if (direction.equals(Direction.UP)) {
            return new GameObject(oldHead.x, oldHead.y - 1);
        } else {
            return new GameObject(oldHead.x, oldHead.y + 1);
        }
    }

    public  void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean checkCollision(GameObject gameObject) {
        return snakeParts.stream().anyMatch(snakePart -> gameObject.x == snakePart.x && gameObject.y == snakePart.y );
//        for (int i = 0; i < snakeParts.size(); i++) {
//            if (gameObject.x == snakeParts.get(i).x && gameObject.y == snakeParts.get(i).y) {
//                return true;
//            }
//        }
//        return  false;
    }

    public int getLength() {
       return snakeParts.size();
    }

}