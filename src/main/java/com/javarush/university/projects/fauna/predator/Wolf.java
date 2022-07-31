package com.javarush.university.projects.fauna.predator;

import com.javarush.university.projects.fauna.predator.Predator;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Wolf extends Predator {

    private static final Config config = ConfigFactory.load().getConfig("wolf");
    public static final int id = 0;

    public Wolf() {
        super(config, id);
    }
}
