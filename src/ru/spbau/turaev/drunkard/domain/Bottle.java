package ru.spbau.turaev.drunkard.domain;

public class Bottle extends MapObject {
    public Bottle(int x, int y) {
        super(x, y);
    }

    @Override
    public char getSymbol() {
        return 'B';
    }
}
