package ru.spbau.turaev.drunkard.domain;

import ru.spbau.turaev.drunkard.domain.objects.MapObject;

import java.util.ArrayList;
import java.util.List;

public class HexagonalMap extends Map {
    public HexagonalMap(int maxX, int maxY) {
        super(maxX, maxY);
    }

    @Override
    public List<MapObject> getAdjacentCells(MapObject from) {
        ArrayList<MapObject> adjacentCells = new ArrayList<>();

        int x = from.getX();
        int y = from.getY();

        if (isValid(x - 1, y)) {
            adjacentCells.add(getObjectAt(x - 1, y));
        }
        if (isValid(x + 1, y)) {
            adjacentCells.add(getObjectAt(x + 1, y));
        }
        if (isValid(x, y - 1)) {
            adjacentCells.add(getObjectAt(x, y - 1));
        }
        if (isValid(x, y + 1)) {
            adjacentCells.add(getObjectAt(x, y + 1));
        }

        if (y % 2 == 0) {
            if (isValid(x + 1, y - 1)) {
                adjacentCells.add(getObjectAt(x + 1, y - 1));
            }
            if (isValid(x + 1, y + 1)) {
                adjacentCells.add(getObjectAt(x + 1, y + 1));
            }
        } else {
            if (isValid(x - 1, y - 1)) {
                adjacentCells.add(getObjectAt(x - 1, y - 1));
            }
            if (isValid(x - 1, y + 1)) {
                adjacentCells.add(getObjectAt(x - 1, y + 1));
            }
        }

        return adjacentCells;
    }

    @Override
    public void draw() {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (!isValid(x, y))
                    continue;
                if (x == 0 && y % 2 == 0)
                    System.out.print(' ');
                System.out.print(getObjectAt(x, y).getSymbol());
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    private boolean isValid(int x, int y) {
        int maxX = y % 2 == 0 ? this.maxX - 1 : this.maxX;
        return 0 <= x && x < maxX && 0 <= y && y < maxY;
    }
}
