package ru.spbau.turaev.drunkard.domain;

public class Post extends MapObject {
    public Post(int x, int y) {
        super(x, y);
    }

    @Override
    public char getSymbol() {
        return 'C';
    }

    @Override
    public void hitBy(Drunkard drunkard) {
        drunkard.sleep();
    }
}
