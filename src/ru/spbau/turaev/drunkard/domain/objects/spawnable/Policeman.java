package ru.spbau.turaev.drunkard.domain.objects.spawnable;

import ru.spbau.turaev.drunkard.domain.Map;

import java.util.stream.Stream;

public class Policeman extends Hunter<Drunkard> {
    public Policeman(int x, int y, Map map) {
        super(x, y, map);
    }

    @Override
    public Stream<Drunkard> getTargets() {
        return map.getLamp().getLightedDrunkards().filter(Drunkard::isLyingOrSleeping);
    }

    @Override
    protected void onEnterHome() {
        state = HunterState.SEARCHING;
    }

    @Override
    protected boolean canLeaveHome() {
        return true;
    }

    @Override
    protected void onMoveEnds() {
        return;
    }

    @Override
    public char getSymbol() {
        return 'P';
    }
}
