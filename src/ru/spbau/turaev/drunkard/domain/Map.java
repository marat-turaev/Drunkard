package ru.spbau.turaev.drunkard.domain;

import ru.spbau.turaev.drunkard.domain.objects.*;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private final Lamp lamp;
    private int x;
    private int y;
    private List<MapObject> mapObjects;
    private int stepCount;

    public Map(int x, int y) {
        this.x = x;
        this.y = y;

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

        return new Empty(x, y);
    }

    public void draw() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(getObjectByCoordinates(i, j).getSymbol());
            }
            System.out.println();
        }
    }

    public int getMaxX() {
        return x - 1;
    }

    public int getMaxY() {
        return y - 1;
    }

    private void spawnDrunkard() {
        mapObjects.add(new Drunkard(0, 9, this));
    }

    public void spawnBottleAt(int x, int y) {
        mapObjects.add(new Bottle(x, y));
    }

    public List<MapObject> objects() {
        return mapObjects;
    }
}
