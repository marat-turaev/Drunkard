package ru.spbau.turaev.drunkard.domain.objects.spawnable;

import ru.spbau.turaev.drunkard.domain.Map;
import ru.spbau.turaev.drunkard.domain.objects.MapObject;
import ru.spbau.turaev.drunkard.domain.pathfinding.BFSAlgorithm;

import java.util.Optional;
import java.util.stream.Stream;

enum HunterState {
    HIDDEN,
    SEARCHING,
    CHASING,
    GOING_HOME
}

/**
 * Represents object that hunts a `target'.
 *
 * @param <T> -- `target' type
 */
public abstract class Hunter<T extends MapObject> extends Spawnable {
    protected HunterState state;
    private BFSAlgorithm algorithm = new BFSAlgorithm(map);

    protected T target;

    protected Hunter(int spawnX, int spawnY, Map map) {
        super(spawnX, spawnY, map);
        state = HunterState.HIDDEN;
    }

    @Override
    public void move() {
        if (canLeaveHome()) {
            super.move();
        }

        if (state == HunterState.SEARCHING) {
            search();
        }

        if (state == HunterState.CHASING) {
            chase();
        }

        if (state == HunterState.GOING_HOME) {
            goHome();
        }

        onMoveEnds();
    }

    protected abstract void onMoveEnds();

    protected abstract boolean canLeaveHome();

    private void search() {
        Optional<T> maybeTarget = getTargets().findFirst();
        if (maybeTarget.isPresent()) {
            target = maybeTarget.get();
            state = HunterState.CHASING;
        }
    }

    public abstract Stream<T> getTargets();


    private void chase() {
        if (x == target.getX() && y == target.getY()) {
            target.hide();
            state = HunterState.GOING_HOME;
            return;
        }

        if (!algorithm.existsPathBetween(this, target)) {
            state = HunterState.GOING_HOME;
            return;
        }

        MapObject nextMove = algorithm.getNextMove(this, target);
        this.x = nextMove.getX();
        this.y = nextMove.getY();
    }

    private void goHome() {
        if (x == spawnX && y == spawnY) {
            onEnterHome();
            return;
        }

        if (!algorithm.existsPathBetween(this, map.getObjectByCoordinates(spawnX, spawnY))) {
            return;
        }

        MapObject nextMove = algorithm.getNextMove(this, map.getObjectByCoordinates(spawnX, spawnY));
        this.x = nextMove.getX();
        this.y = nextMove.getY();
    }

    protected abstract void onEnterHome();

    @Override
    public void appear() {
        super.appear();
        this.state = HunterState.SEARCHING;
    }
}
