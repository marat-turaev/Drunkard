package ru.spbau.turaev.drunkard.domain.objects;

import ru.spbau.turaev.drunkard.domain.objects.spawnable.Drunkard;

public class Bottle extends MapObject {
    public Bottle(int x, int y) {
        super(x, y);
    }

    @Override
    public char getSymbol() {
        return 'B';
    }

    @Override
    public void hitBy(Drunkard drunkard) {
        drunkard.lie();
    }
}
