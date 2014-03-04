package ru.spbau.turaev.drunkard.domain;

public class Empty extends MapObject {
    public Empty(int x, int y) {
        super(x, y);
    }

    @Override
    public char getSymbol() {
        return '.';
    }
}
