package ru.spbau.turaev.drunkard.domain.objects.spawnable;

import ru.spbau.turaev.drunkard.domain.Map;
import ru.spbau.turaev.drunkard.domain.objects.Bottle;

import java.util.stream.Stream;

public class Tramp extends Hunter<Bottle> {
    private Timer timer = new Timer();

    public Tramp(int spawnX, int spawnY, Map map) {
        super(spawnX, spawnY, map);
    }

    @Override
    protected void onMoveEnds() {
        timer.tick();
    }

    @Override
    protected boolean canLeaveHome() {
        return timer.isZero();
    }

    @Override
    public Stream<Bottle> getTargets() {
        return map.getObjects().filter(b -> b instanceof Bottle).map(b -> (Bottle) b);
    }

    @Override
    protected void onEnterHome() {
        state = HunterState.HIDDEN;
        timer.set(30);
        hide();
    }

    @Override
    public char getSymbol() {
        return 'z';
    }
}
