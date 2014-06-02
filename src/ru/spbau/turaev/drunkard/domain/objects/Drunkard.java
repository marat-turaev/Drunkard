package ru.spbau.turaev.drunkard.domain.objects;

import ru.spbau.turaev.drunkard.domain.Map;
import ru.spbau.turaev.drunkard.util.RandomUtils;


enum DrunkardState {
    HIDDEN,
    ACTIVE,
    SLEEPING,
    LYING
}


public class Drunkard extends Spawnable {
    private DrunkardState state;
    private char symbol;

    public Drunkard(int x, int y, Map map) {
        super(x, y, map);
        this.state = DrunkardState.HIDDEN;
    }

    public void move() {
        super.move();

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

        MapObject neighbour = map.getObjectByCoordinates(newX, newY);
        neighbour.hitBy(this);

        if (map.isFree(newX, newY)) {
            x = newX;
            y = newY;
            dropBottle();
        }
    }

    @Override
    public void hitBy(Drunkard drunkard) {
        if (state == DrunkardState.SLEEPING) {
            drunkard.sleep();
        }
    }

    private void dropBottle() {
        int rand = RandomUtils.randInt(1, 30);
        if (rand == 1) {
            map.spawnBottleAt(x, y);
        }
    }

    public void lie() {
        this.state = DrunkardState.LYING;
        this.symbol = '&';
    }

    public void sleep() {
        this.state = DrunkardState.SLEEPING;
        this.symbol = 'Z';
    }

    public boolean isLyingOrSleeping() {
        return state == DrunkardState.LYING || state == DrunkardState.SLEEPING;
    }

    @Override
    public void appear() {
        this.symbol = 'D';
        this.state = DrunkardState.ACTIVE;
        super.appear();
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}
