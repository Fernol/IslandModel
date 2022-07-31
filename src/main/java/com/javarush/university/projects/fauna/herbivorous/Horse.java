package com.javarush.university.projects.fauna.herbivorous;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Horse extends Herbivores {
    private static final Config config = ConfigFactory.load().getConfig("horse");
    public static final int id = 5;

    public Horse() {
        super(config, id);
    }
}
