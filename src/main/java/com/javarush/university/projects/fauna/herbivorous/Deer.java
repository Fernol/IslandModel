package com.javarush.university.projects.fauna.herbivorous;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Deer extends Herbivores {
    private static final Config config = ConfigFactory.load().getConfig("deer");
    public static final int id = 6;

    public Deer() {
        super(config, id);
    }
}
