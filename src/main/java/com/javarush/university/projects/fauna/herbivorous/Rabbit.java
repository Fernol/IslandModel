package com.javarush.university.projects.fauna.herbivorous;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Rabbit extends Herbivores {

    private static final Config config = ConfigFactory.load().getConfig("rabbit");
    public static final int id = 7;

    public Rabbit() {
        super(config, id);
    }
}
