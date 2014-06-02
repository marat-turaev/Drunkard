package ru.spbau.turaev.drunkard.domain.pathfinding;

import ru.spbau.turaev.drunkard.domain.Map;
import ru.spbau.turaev.drunkard.domain.objects.MapObject;
import ru.spbau.turaev.drunkard.util.Tuple;

public class DFSAlgorithm {
    public DFSAlgorithm(Map map) {
    }

    public boolean existsPathBetween(MapObject from, MapObject to) {
        return true;
    }

    public Tuple<Integer, Integer> getNextMove(MapObject from, MapObject to) {
        return new Tuple<>(1, 1);
    }
}
