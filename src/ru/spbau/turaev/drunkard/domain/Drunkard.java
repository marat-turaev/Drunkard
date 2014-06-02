package ru.spbau.turaev.drunkard.domain;

import ru.spbau.turaev.drunkard.util.RandomUtils;

public class Drunkard extends MapObject {
    private DrunkardState state;
    private Map map;
    private char symbol;

    public Drunkard(int x, int y, Map map) {
        super(x, y);
        this.map = map;

        activate();
    }

    public void move() {
        if (state == DrunkardState.SLEEPING || state == DrunkardState.LYING) {
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
            dropBottle();
        }

        MapObject neighbour = map.getObjectByCoordinates(newX, newY);
        if (neighbour instanceof Empty) {
            x = newX;
            y = newY;
        }
        if (neighbour instanceof Post) {
            sleep();
        }
        if (neighbour instanceof Bottle) {
            lie();
        }
        if (neighbour instanceof Drunkard) {
            Drunkard neighbourDrunkard = (Drunkard) neighbour;
            if (neighbourDrunkard.state == DrunkardState.SLEEPING) {
                sleep();
            }
        }
    }

    private void dropBottle() {
        int rand = RandomUtils.randInt(1, 30);
        if (rand == 1) {
            map.spawnBottleAt(x, y);
        }
    }

    private void lie() {
        this.state = DrunkardState.LYING;
        this.symbol = '&';
    }

    private void sleep() {
        this.state = DrunkardState.SLEEPING;
        this.symbol = 'Z';
    }

    private void activate() {
        this.state = DrunkardState.ACTIVE;
        this.symbol = 'D';
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}
