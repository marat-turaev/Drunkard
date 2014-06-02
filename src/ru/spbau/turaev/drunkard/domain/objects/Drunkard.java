package ru.spbau.turaev.drunkard.domain.objects;

import ru.spbau.turaev.drunkard.domain.Map;
import ru.spbau.turaev.drunkard.util.RandomUtils;


enum DrunkardState {
    ACTIVE,
    SLEEPING,
    LYING
}


public class Drunkard extends MapObject {
    private DrunkardState state;
    private Map map;
    private char symbol;

    public Drunkard(int x, int y, Map map) {
        super(x, y);
        this.map = map;
        this.state = DrunkardState.ACTIVE;
        hide();
    }

    public void move() {
        if (state == DrunkardState.SLEEPING || state == DrunkardState.LYING) {
            return;
        }

        if (isHidden()) {
            if (!map.isFree(0, 9)) {
                return;
            }
            this.x = 0;
            this.y = 9;
            appear();
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

        if (map.isFree(newX, newY)) {
            x = newX;
            y = newY;
            dropBottle();
        }

        MapObject neighbour = map.getObjectByCoordinates(newX, newY);
        neighbour.hitBy(this);
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
        super.appear();
    }

    @Override
    public char getSymbol() {
        return symbol;
    }
}
