package com.javarush.university.projects.island;

import java.util.concurrent.ThreadLocalRandom;

public enum Direction {

    NO(0, 0),
    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);

    private final int deltaX;
    private final int deltaY;

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    // Даёт рандомное направление для движения
    public static Direction getRandomDirection() {
        int direction = ThreadLocalRandom.current().nextInt(5);
        if (direction == 0) return NO;
        if (direction == 1) return NORTH;
        if (direction == 2) return EAST;
        if (direction == 3) return SOUTH;
        return WEST;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
}
