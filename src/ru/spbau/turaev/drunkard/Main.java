package ru.spbau.turaev.drunkard;

import ru.spbau.turaev.drunkard.domain.HexagonalMap;
import ru.spbau.turaev.drunkard.domain.Map;

public class Main {
    public static void main(String[] args) {
        Map map = new HexagonalMap(15, 15);

        for (int i = 0; i < 500; i++) {
            map.doStep();
            map.draw();
            System.out.println();
        }
    }
}
