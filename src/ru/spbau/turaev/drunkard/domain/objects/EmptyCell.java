package ru.spbau.turaev.drunkard.domain.objects;

public class EmptyCell extends MapObject {
    public EmptyCell(int x, int y) {
        super(x, y);
    }

    @Override
    public char getSymbol() {
        return '.';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmptyCell mapObject = (EmptyCell) o;

        if (x != mapObject.x) return false;
        if (y != mapObject.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
