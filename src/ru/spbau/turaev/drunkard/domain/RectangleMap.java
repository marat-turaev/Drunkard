package ru.spbau.turaev.drunkard.domain;

import ru.spbau.turaev.drunkard.domain.objects.MapObject;

import java.util.ArrayList;
import java.util.List;

public class RectangleMap extends Map {
    public RectangleMap(int maxX, int maxY) {
        super(maxX, maxY);
    }

    @Override
    public void draw() {
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                System.out.print(getObjectAt(j, i).getSymbol());
            }
            System.out.println();
        }
    }

    @Override
    public List<MapObject> getAdjacentCells(MapObject from) {
        List<MapObject> result = new ArrayList<>();

        int x = from.getX();
        int y = from.getY();

        if (isValid(x - 1, y)) {
            result.add(getObjectAt(x - 1, y));
        }

        if (isValid(x + 1, y)) {
            result.add(getObjectAt(x + 1, y));
        }

        if (isValid(x, y - 1)) {
            result.add(getObjectAt(x, y - 1));
        }

        if (isValid(x, y + 1)) {
            result.add(getObjectAt(x, y + 1));
        }

        return result;
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < maxX && y < maxY;
    }
}
