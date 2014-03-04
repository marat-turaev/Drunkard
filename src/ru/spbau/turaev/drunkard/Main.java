package ru.spbau.turaev.drunkard;

import ru.spbau.turaev.drunkard.domain.Map;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Map map = new Map(10, 10);

        while (true) {
            map.doStep();
            map.draw();
            Thread.sleep(100);
        }
    }
}
