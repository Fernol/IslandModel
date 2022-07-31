package com.javarush.university.projects.fauna.predator;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Bear extends Predator {
    private static final Config config = ConfigFactory.load().getConfig("bear");
    public static final int id = 3;

    public Bear() {
        super(config, id);
    }
}
