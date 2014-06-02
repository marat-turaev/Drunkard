package ru.spbau.turaev.drunkard.domain.objects.spawnable;

public class Timer {
    private int value;

    public Timer() {
        this.value = 0;
    }

    public void set(int value) {
        this.value = value;
    }

    public void tick() {
        value = value == 0 ? 0 : value - 1;
    }

    public boolean isZero() {
        return value == 0;
    }
}
