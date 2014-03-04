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
    private int spawnX = 0;
    private int spawnY = 9;
    private int x;
    private int y;
    private List<MapObject> mapObjects;

    public Map(int x, int y) {
        this.x = x;
        this.y = y;
        mapObjects = new ArrayList<MapObject>();
        mapObjects.add(new Post(7,7));
    }

    public void doStep() {

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

    private void spawnDrunkard() {

    }
}
