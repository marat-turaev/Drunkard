package ru.spbau.turaev.drunkard.domain;

public class Post implements MapObject {
    public Post(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private int x;
    private int y;

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
        return 'C';
    }
}
