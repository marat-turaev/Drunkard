package ru.spbau.turaev.drunkard.domain;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private int x;
    private int y;
    private List<MapObject> mapObjects;
    private int stepCount;
    private int inTavernDrunkards;

    public Map(int x, int y) {
        this.x = x;
        this.y = y;
        mapObjects = new ArrayList<MapObject>();
        mapObjects.add(new Post(7, 7));
    }

    public void doStep() {
        stepCount++;

        for (int i = 0; i < mapObjects.size(); i++) {
            mapObjects.get(i).move();
        }

        if (stepCount % 20 == 0) {
            inTavernDrunkards++;
        }

        if (inTavernDrunkards != 0) {
            spawnDrunkard();
        }
    }

    public boolean isFree(int x, int y) {
        for (MapObject obj : mapObjects) {
            if (x == obj.getX() && y == obj.getY()) {
                return false;
            }
        }

        return true;
    }

    public MapObject getObjectByCoordinates(int x, int y) {
        for (MapObject obj : mapObjects) {
            if (x == obj.getX() && y == obj.getY()) {
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
        if (isFree(0, 9)) {
            Drunkard drunkard = new Drunkard(0, 9, this);
            mapObjects.add(drunkard);
            inTavernDrunkards--;
        } else {
            inTavernDrunkards++;
        }
    }

    public void spawnBottleAt(int x, int y) {
        mapObjects.add(new Bottle(x, y));
    }
}
