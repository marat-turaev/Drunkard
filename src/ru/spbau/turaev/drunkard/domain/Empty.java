package ru.spbau.turaev.drunkard.domain;

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
}
