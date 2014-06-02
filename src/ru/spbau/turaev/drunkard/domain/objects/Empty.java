package ru.spbau.turaev.drunkard.domain.objects;

public class Empty extends MapObject {
    public Empty(int x, int y) {
        super(x, y);
    }

    @Override
    public char getSymbol() {
        return '.';
    }
}
