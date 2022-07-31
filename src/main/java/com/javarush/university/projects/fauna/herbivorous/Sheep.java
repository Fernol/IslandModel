package com.javarush.university.projects.fauna.herbivorous;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Sheep extends Herbivores {
    private static final Config config = ConfigFactory.load().getConfig("sheep");
    public static final int id = 10;

    public Sheep() {
        super(config, id);
    }
}
