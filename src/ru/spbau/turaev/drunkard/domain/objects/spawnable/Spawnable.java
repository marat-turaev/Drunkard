package ru.spbau.turaev.drunkard.domain.objects.spawnable;

import ru.spbau.turaev.drunkard.domain.Map;
import ru.spbau.turaev.drunkard.domain.objects.MapObject;

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
            if (map.isFree(spawnX, spawnY)) {
                this.x = spawnX;
                this.y = spawnY;
                appear();
            }
        }
    }
}
