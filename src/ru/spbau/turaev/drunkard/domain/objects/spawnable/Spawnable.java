package ru.spbau.turaev.drunkard.domain.objects.spawnable;

import ru.spbau.turaev.drunkard.domain.Map;
import ru.spbau.turaev.drunkard.domain.objects.MapObject;

/**
 * Represents an object that has a spawn point.
 */
public abstract class Spawnable extends MapObject {
    protected final int spawnX;
    protected final int spawnY;
    protected final Map map;

    protected Spawnable(int spawnX, int spawnY, Map map) {
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.map = map;
        hide();
    }

    @Override
    public void move() {
        if (isHidden()) {
            if (map.getObjectAt(spawnX, spawnY).isFree()) {
                this.x = spawnX;
                this.y = spawnY;
                appear();
            }
        }
    }
}
