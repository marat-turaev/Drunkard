package ru.spbau.turaev.drunkard.domain;

/**
 * Created with IntelliJ IDEA.
 * User: marat
 * Date: 05/03/14
 * Time: 01:28
 * To change this template use File | Settings | File Templates.
 */
public class Empty implements MapObject {
    public Empty(int y, int x) {
        this.y = y;
        this.x = x;
    }

    private int y;
    private int x;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public char getSymbol() {
        return '.';
    }

    @Override
    public void move() {
        return;
    }
}
