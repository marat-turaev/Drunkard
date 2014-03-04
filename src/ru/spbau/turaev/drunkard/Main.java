package ru.spbau.turaev.drunkard;

import ru.spbau.turaev.drunkard.domain.Map;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Map map = new Map(10, 10);

        while (true) {
            map.doStep();
            map.draw();
            System.in.read();
        }
    }
}
