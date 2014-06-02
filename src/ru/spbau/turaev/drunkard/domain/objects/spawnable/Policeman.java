package ru.spbau.turaev.drunkard.domain.objects.spawnable;

import ru.spbau.turaev.drunkard.domain.Map;
import ru.spbau.turaev.drunkard.domain.objects.MapObject;
import ru.spbau.turaev.drunkard.domain.pathfinding.BFSAlgorithm;

import java.util.Optional;

enum PolicemanState {
    HIDDEN,
    SEARCHING,
    FOLLOWING_DRUNKARD,
    GOING_HOME
}

public class Policeman extends Spawnable {
    private PolicemanState state;
    private Drunkard target;
    private BFSAlgorithm algorithm = new BFSAlgorithm(map);

    public Policeman(int x, int y, Map map) {
        super(x, y, map);
        this.state = PolicemanState.HIDDEN;
    }

    @Override
    public void move() {
        super.move();
        if (state == PolicemanState.SEARCHING) {
            findDrunkard();
        }

        if (state == PolicemanState.FOLLOWING_DRUNKARD) {
            followDrunkard();
        }

        if (state == PolicemanState.GOING_HOME) {
            goHome();
        }
    }

    private void findDrunkard() {
        Optional<Drunkard> maybeTarget = map.getLamp().getLightedDrunkards().filter(Drunkard::isLyingOrSleeping).findFirst();
        if (maybeTarget.isPresent()) {
            target = maybeTarget.get();
            state = PolicemanState.FOLLOWING_DRUNKARD;
        }
    }

    private void followDrunkard() {
        if (x == target.getX() && y == target.getY()) {
            target.hide();
            state = PolicemanState.GOING_HOME;
            return;
        }

        if (!algorithm.existsPathBetween(this, target)) {
            state = PolicemanState.GOING_HOME;
            return;
        }

        MapObject nextMove = algorithm.getNextMove(this, target);
        this.x = nextMove.getX();
        this.y = nextMove.getY();
    }

    private void goHome() {
        if (x == spawnX && y == spawnY) {
            state = PolicemanState.SEARCHING;
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
        this.state = PolicemanState.SEARCHING;
    }

    @Override
    public char getSymbol() {
        return 'P';
    }
}
