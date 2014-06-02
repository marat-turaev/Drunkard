package ru.spbau.turaev.drunkard.domain;

import ru.spbau.turaev.drunkard.domain.objects.*;
import ru.spbau.turaev.drunkard.domain.objects.spawnable.Drunkard;
import ru.spbau.turaev.drunkard.domain.objects.spawnable.Policeman;
import ru.spbau.turaev.drunkard.domain.objects.spawnable.Tramp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class Map {
    private Lamp lamp = new Lamp(10, 3, this);
    private List<MapObject> mapObjects = new ArrayList<>();
    private int stepCount;
    protected int maxX;
    protected int maxY;

    public Map(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        initSpawn();
    }

    private void initSpawn() {
        mapObjects.add(lamp);
        mapObjects.add(new Post(7, 7));
        mapObjects.add(new Policeman(14, 3, this));
        mapObjects.add(new Tramp(0, 4, this));
    }

    public Lamp getLamp() {
        return lamp;
    }

    public void doStep() {
        for (int i = 0; i < mapObjects.size(); i++) {
            mapObjects.get(i).move();
        }

        if (stepCount % 20 == 0) {
            spawnDrunkard();
        }

        stepCount++;
    }

    public MapObject getObjectAt(int x, int y) {
        for (MapObject obj : mapObjects) {
            if (x == obj.getX() && y == obj.getY() && !obj.isHidden()) {
                return obj;
            }
        }

        return new EmptyCell(x, y);
    }

    private void spawnDrunkard() {
        mapObjects.add(new Drunkard(9, 0, this));
    }

    public void spawnBottleAt(int x, int y) {
        mapObjects.add(new Bottle(x, y));
    }

    public Stream<MapObject> getObjects() {
        return mapObjects.stream().filter(o -> !o.isHidden());
    }

    public abstract void draw();

    public abstract List<MapObject> getAdjacentCells(MapObject from);
}
