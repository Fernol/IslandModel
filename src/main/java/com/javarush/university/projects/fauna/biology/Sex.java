package com.javarush.university.projects.fauna.biology;

import java.util.concurrent.ThreadLocalRandom;

public class Sex {

    public static final Sex MALE = new Sex("Male");
    public static final Sex FEMALE = new Sex("Female");

    private final String name;

    private Sex(String name) {
        this.name = name;
    }

    public static Sex getGender() {
        return ThreadLocalRandom.current().nextInt(2) == 0 ? MALE : FEMALE;
    }

    @Override
    public String toString() {
        return name;
    }
}
