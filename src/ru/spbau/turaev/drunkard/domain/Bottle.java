package ru.spbau.turaev.drunkard.domain;

public class Bottle implements MapObject {
    private int x;
    private int y;

    public Bottle(int x, int y) {
        this.x = x;
        this.y = y;
    }

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
        return 'B';
    }
}
