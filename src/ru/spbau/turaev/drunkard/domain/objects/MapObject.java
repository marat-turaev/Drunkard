package ru.spbau.turaev.drunkard.domain.objects;

import ru.spbau.turaev.drunkard.domain.objects.spawnable.Drunkard;

/**
 * Any object on a map in the game.
 */
public abstract class MapObject {
    private boolean hidden;
    protected int x;
    protected int y;

    protected MapObject() {
    }

    public MapObject(int x, int y) {
        this.x = x;
        this.y = y;
        appear();
    }

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

    /**
     * Manhattan distance between two objects
     * @param other `to' object
     * @return distance between `this' and `to'
     */
    public final double distanceTo(MapObject other) {
        return Math.max(Math.abs(this.x - other.x), Math.abs(this.y - other.y));
    }

    public final boolean isHidden() {
        return hidden;
    }

    public final void hide() {
        this.hidden = true;
    }

    public void appear() {
        this.hidden = false;
    }

    public boolean isFree() {
        return false;
    }
}
