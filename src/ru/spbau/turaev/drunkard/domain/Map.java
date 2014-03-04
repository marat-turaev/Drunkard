package ru.spbau.turaev.drunkard.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: marat
 * Date: 05/03/14
 * Time: 00:53
 * To change this template use File | Settings | File Templates.
 */
public class Map {
    private int x;
    private int y;
    private List<MapObject> mapObjects;
    private int stepCount;

    public Map(int x, int y) {
        this.x = x;
        this.y = y;
        mapObjects = new ArrayList<MapObject>();
        mapObjects.add(new Post(7, 7));
    }

    public void doStep() {
        stepCount++;
        for (MapObject mapObject : mapObjects) {
            mapObject.move();
        }

        if (stepCount % 20 == 0) {
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
        char[][] map = new char[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                map[i][j] = '.';
            }
        }

        for (MapObject obj : mapObjects) {
            int x = obj.getX();
            int y = obj.getY();
            map[x][y] = obj.getSymbol();
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                System.out.print(map[i][j]);
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
            mapObjects.add(new Drunkard(0, 9, this));
        } else {
            //TODO: if 0;9 cell is occupied
        }
    }
}
