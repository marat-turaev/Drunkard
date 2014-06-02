package ru.spbau.turaev.drunkard.domain.objects.spawnable;

import ru.spbau.turaev.drunkard.domain.Map;
import ru.spbau.turaev.drunkard.domain.objects.MapObject;
import ru.spbau.turaev.drunkard.util.RandomUtils;

import java.util.List;
import java.util.Random;

enum DrunkardState {
    HIDDEN,
    ACTIVE,
    SLEEPING,
    LYING
}

public class Drunkard extends Spawnable {
    private Random random = new Random();
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

        List<MapObject> adjacent = map.getAdjacentCells(this);
        int rand = random.nextInt(4);
        if (rand >= adjacent.size()) {
            return;
        }

        MapObject neighbour = adjacent.get(rand);
        neighbour.hitBy(this);

        if (neighbour.isFree()) {
            dropBottle();
            x = neighbour.getX();
            y = neighbour.getY();
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
