package ru.spbau.turaev.drunkard.domain.objects.spawnable;

import ru.spbau.turaev.drunkard.domain.Map;
import ru.spbau.turaev.drunkard.domain.objects.MapObject;

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

        List<MapObject> neighbours = map.getAdjacentCells(this);
        MapObject neighbour = neighbours.get(random.nextInt(neighbours.size()));
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
        int rand = random.nextInt(30);
        if (rand == 0) {
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
