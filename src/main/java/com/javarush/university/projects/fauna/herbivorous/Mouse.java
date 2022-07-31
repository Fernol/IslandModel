package com.javarush.university.projects.fauna.herbivorous;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Mouse extends Herbivores {
    private static final Config config = ConfigFactory.load().getConfig("mouse");
    public static final int id = 8;

    public Mouse() {
        super(config, id);
    }
}
