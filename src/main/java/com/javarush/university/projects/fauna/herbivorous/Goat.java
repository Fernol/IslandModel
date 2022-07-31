package com.javarush.university.projects.fauna.herbivorous;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Goat extends Herbivores {
    private static final Config config = ConfigFactory.load().getConfig("goat");
    public static final int id = 9;

    public Goat() {
        super(config, id);
    }
}
