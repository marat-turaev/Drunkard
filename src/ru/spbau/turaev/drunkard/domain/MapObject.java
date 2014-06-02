package ru.spbau.turaev.drunkard.domain;

public abstract class MapObject {
    public MapObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected int x;
    protected int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract char getSymbol();

    public void move() {
        return;
    }

    public void hitBy(Drunkard drunkard) {
        return;
    }
}
