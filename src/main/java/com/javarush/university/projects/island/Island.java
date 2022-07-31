package com.javarush.university.projects.island;

import com.javarush.university.projects.fauna.factory.AnimalFactory;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.Arrays;
import java.util.List;

public class Island {
    private static final Config config = ConfigFactory.load().getConfig("map");
    private static final List<Integer> listAnimals = config.getIntList("listAnimal");
    public static final int countAllAnimals = listAnimals.stream().reduce(0, Integer::sum);

    protected static final int maxX = config.getInt("x");
    protected static final int maxY = config.getInt("y");

    public static final Cell[][] cells = Cell.initMap(maxX, maxY);

    public static void addAnimalsToMap() {
        for (int i = 0; i < listAnimals.size(); i++) {
            for (int j = 0; j < listAnimals.get(i); j++) {
                AnimalFactory.getAnimalByID(i);
            }
        }
    }
}
