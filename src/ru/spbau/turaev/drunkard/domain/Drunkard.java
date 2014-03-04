package ru.spbau.turaev.drunkard.domain;

import ru.spbau.turaev.drunkard.util.RandomUtils;

public class Drunkard extends MapObject {
    private DrunkardState state;
    private Map map;
    private char symbol;

    public Drunkard(int x, int y, Map map) {
        super(x, y);
        this.map = map;

        Activate();
    }

    public void move() {
        if (state == DrunkardState.Sleeping || state == DrunkardState.Lying) {
            return;
        }

        int newX = x;
        int newY = y;

        int rand = RandomUtils.randInt(1, 4);
        switch (rand) {
            case 1:
                if (x > 0) {
                    newX = x - 1;
                }
                break;
            case 2:
                if (y > 0) {
                    newY = y - 1;
                }
                break;
            case 3:
                if (x < map.getMaxX()) {
                    newX = x + 1;
                }
                break;
            case 4:
                if (y < map.getMaxY()) {
                    newY = y + 1;
                }
                break;
        }

        boolean wasMoved = false;
        if (x != newX || y != newY) {
            wasMoved = true;
        }
        if (wasMoved) {
            DropBottle();
        }

        MapObject neighbour = map.getObjectByCoordinates(newX, newY);
        if (neighbour instanceof Empty) {
            x = newX;
            y = newY;
        }
        if (neighbour instanceof Post) {
            Sleep();
        }
        if (neighbour instanceof Bottle) {
            Lie();
        }
        if (neighbour instanceof Drunkard) {
            Drunkard neighbourDrunkard = (Drunkard) neighbour;
            if (neighbourDrunkard.state == DrunkardState.Sleeping) {
                Sleep();
            }
        }
    }

    private void DropBottle() {
        int rand = RandomUtils.randInt(1, 30);
        if (rand == 1) {
            map.spawnBottle(x, y);
        }
    }

    private void Lie() {
        this.state = DrunkardState.Lying;
        this.symbol = '&';
    }

    private void Sleep() {
        this.state = DrunkardState.Sleeping;
        this.symbol = 'Z';
    }

    private void Activate() {
        this.state = DrunkardState.Active;
        this.symbol = 'D';
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}
