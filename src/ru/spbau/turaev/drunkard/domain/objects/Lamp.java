package ru.spbau.turaev.drunkard.domain.objects;

import ru.spbau.turaev.drunkard.domain.Map;
import ru.spbau.turaev.drunkard.domain.objects.spawnable.Drunkard;

import java.util.stream.Stream;

public class Lamp extends MapObject {
    private final int DISTANCE = 3;
    private final Map map;

    public Lamp(int x, int y, Map map) {
        super(x, y);
        this.map = map;
    }

    @Override
    public char getSymbol() {
        return 'L';
    }

    public Stream<Drunkard> getLightedDrunkards() {
        return map.getObjects().filter(mapObject -> distanceTo(mapObject) <= DISTANCE && mapObject instanceof Drunkard).map(d -> (Drunkard) d);
    }
}
