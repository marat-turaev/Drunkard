package ru.spbau.turaev.drunkard.domain.pathfinding;

import ru.spbau.turaev.drunkard.domain.Map;
import ru.spbau.turaev.drunkard.domain.objects.EmptyCell;
import ru.spbau.turaev.drunkard.domain.objects.MapObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BFSAlgorithm {
    private Map map;
    private LinkedList<MapObject> path;

    public BFSAlgorithm(Map map) {
        this.map = map;
    }

    public boolean existsPathBetween(MapObject from, MapObject to) {
        return getPath(from, to);
    }

    public MapObject getNextMove(MapObject from, MapObject to) {
        getPath(from, to);
        return path.peekFirst();
    }

    private boolean getPath(MapObject from, MapObject to) {
        path = new LinkedList<>();
        path.clear();
        HashMap<MapObject, MapObject> parents = new HashMap<>();
        Queue<MapObject> queue = new LinkedList<>();
        queue.add(from);
        parents.put(from, null);
        while (from != to && !queue.isEmpty()) {
            from = queue.poll();
            for (MapObject object : map.getAdjacentCells(from)) {
                if (!parents.containsKey(object) && (object instanceof EmptyCell || object == to)) {
                    parents.put(object, from);
                    queue.offer(object);
                }
            }
        }

        if (!parents.containsKey(to)) {
            return false;
        }

        MapObject parent = to;
        while (parent != null) {
            path.addFirst(parent);
            parent = parents.get(parent);
        }
        path.removeFirst();
        return true;
    }
}
