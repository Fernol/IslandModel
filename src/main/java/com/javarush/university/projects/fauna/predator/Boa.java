package com.javarush.university.projects.fauna.predator;

import com.javarush.university.projects.fauna.predator.Predator;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Boa extends Predator {

    private static final Config config = ConfigFactory.load().getConfig("boa");
    public static final int id = 1;

    public Boa() {
        super(config, id);
    }

}
