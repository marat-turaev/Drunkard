package ru.spbau.turaev.drunkard.domain.objects.spawnable;

import ru.spbau.turaev.drunkard.domain.Map;
import ru.spbau.turaev.drunkard.domain.objects.Bottle;
import ru.spbau.turaev.drunkard.domain.objects.MapObject;
import ru.spbau.turaev.drunkard.domain.pathfinding.BFSAlgorithm;

import java.util.Optional;

enum TrampState {
    HIDDEN,
    SEARCHING,
    CHASING_BOTTLE,
    GOING_HOME
}

public class Tramp extends Spawnable {
    private Timer timer = new Timer();
    private TrampState state;
    private Bottle target;
    private BFSAlgorithm algorithm = new BFSAlgorithm(map);

    public Tramp(int spawnX, int spawnY, Map map) {
        super(spawnX, spawnY, map);
        state = TrampState.HIDDEN;
    }

    @Override
    public void move() {
        if (timer.isZero()) {
            super.move();
        }

        if (state == TrampState.SEARCHING) {
            findBottle();
        }

        if (state == TrampState.CHASING_BOTTLE) {
            chaseBottle();
        }

        if (state == TrampState.GOING_HOME) {
            goHome();
        }

        timer.tick();
    }

    private void findBottle() {
        Optional<Bottle> maybeBottle = map.getObjects().filter(b -> b instanceof Bottle).map(b -> (Bottle) b).findFirst();
        if (maybeBottle.isPresent()) {
            target = maybeBottle.get();
            state = TrampState.CHASING_BOTTLE;
        }
    }

    private void chaseBottle() {
        if (x == target.getX() && y == target.getY()) {
            target.hide();
            state = TrampState.GOING_HOME;
            return;
        }

        if (!algorithm.existsPathBetween(this, target)) {
            state = TrampState.GOING_HOME;
            return;
        }

        MapObject nextMove = algorithm.getNextMove(this, target);
        this.x = nextMove.getX();
        this.y = nextMove.getY();
    }

    private void goHome() {
        if (x == spawnX && y == spawnY) {
            state = TrampState.HIDDEN;
            timer.set(30);
            hide();
            return;
        }

        if (!algorithm.existsPathBetween(this, map.getObjectByCoordinates(spawnX, spawnY))) {
            return;
        }

        MapObject nextMove = algorithm.getNextMove(this, map.getObjectByCoordinates(spawnX, spawnY));
        this.x = nextMove.getX();
        this.y = nextMove.getY();
    }


    @Override
    public void appear() {
        super.appear();
        state = TrampState.SEARCHING;
    }

    @Override
    public char getSymbol() {
        return 'z';
    }
}
