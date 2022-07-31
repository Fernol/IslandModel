package com.javarush.university.projects.fauna.herbivorous;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Duck extends Herbivores {
    private static final Config config = ConfigFactory.load().getConfig("duck");
    public static final int id = 13;

    public Duck() {
        super(config, id);
    }
}
