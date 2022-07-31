package com.javarush.university.projects.fauna.predator;

import com.javarush.university.projects.fauna.predator.Predator;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Fox extends Predator {

    private static final Config config = ConfigFactory.load().getConfig("fox");
    public static final int id = 2;

    public Fox() {
        super(config, id);
    }
}
