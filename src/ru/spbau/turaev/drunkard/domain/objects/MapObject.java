package ru.spbau.turaev.drunkard.domain.objects;

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

    public final double distanceTo(MapObject other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    public final boolean isHidden() {
        return hidden;
    }

    public void hide() {
        this.hidden = true;
    }

    public void appear() {
        this.hidden = false;
    }
}
