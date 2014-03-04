package ru.spbau.turaev.drunkard.domain;

/**
 * Created with IntelliJ IDEA.
 * User: marat
 * Date: 05/03/14
 * Time: 00:51
 * To change this template use File | Settings | File Templates.
 */
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
