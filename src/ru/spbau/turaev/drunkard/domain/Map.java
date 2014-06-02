package ru.spbau.turaev.drunkard.domain;

import ru.spbau.turaev.drunkard.domain.objects.*;
import ru.spbau.turaev.drunkard.domain.objects.spawnable.Drunkard;
import ru.spbau.turaev.drunkard.domain.objects.spawnable.Policeman;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final Lamp lamp;
    private int maxX;
    private int maxY;
    private List<MapObject> mapObjects;
    private int stepCount;

    public Map(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;

        mapObjects = new ArrayList<>();
        mapObjects.add(new Post(7, 7));
        Lamp lamp = new Lamp(10, 3, this);
        this.lamp = lamp;
        mapObjects.add(lamp);
        mapObjects.add(new Policeman(14, 3, this));
    }

    public Lamp getLamp() {
        return lamp;
    }

    public void doStep() {
        stepCount++;

        for (int i = 0; i < mapObjects.size(); i++) {
            mapObjects.get(i).move();
        }

        if (stepCount % 20 == 0) {
            spawnDrunkard();
        }
    }

    public boolean isFree(int x, int y) {
        for (MapObject obj : mapObjects) {
            if (x == obj.getX() && y == obj.getY() && !obj.isHidden()) {
                return false;
            }
        }

        return true;
    }

    public MapObject getObjectByCoordinates(int x, int y) {
        for (MapObject obj : mapObjects) {
            if (x == obj.getX() && y == obj.getY() && !obj.isHidden()) {
                return obj;
            }
        }

        return new EmptyCell(x, y);
    }

    public void draw() {
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                System.out.print(getObjectByCoordinates(j, i).getSymbol());
            }
            System.out.println();
        }
    }

    public int getMaxX() {
        return maxX - 1;
    }

    public int getMaxY() {
        return maxY - 1;
    }

    private void spawnDrunkard() {
        mapObjects.add(new Drunkard(9, 0, this));
    }

    public void spawnBottleAt(int x, int y) {
        mapObjects.add(new Bottle(x, y));
    }

    public List<MapObject> objects() {
        return mapObjects;
    }

    public List<MapObject> getAdjacentCells(MapObject from) {
        List<MapObject> result = new ArrayList<>();

        if (from.getX() > 0) {
            result.add(getObjectByCoordinates(from.getX() - 1, from.getY()));
        }

        if (from.getX() < maxX) {
            result.add(getObjectByCoordinates(from.getX() + 1, from.getY()));
        }

        if (from.getY() > 0) {
            result.add(getObjectByCoordinates(from.getX(), from.getY() - 1));
        }

        if (from.getY() < maxY) {
            result.add(getObjectByCoordinates(from.getX(), from.getY() + 1));
        }

        return result;
    }
}
