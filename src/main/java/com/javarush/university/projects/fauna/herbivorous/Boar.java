package com.javarush.university.projects.fauna.herbivorous;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Boar extends Herbivores {
    private static final Config config = ConfigFactory.load().getConfig("boar");
    public static final int id = 11;

    public Boar() {
        super(config, id);
    }
}
