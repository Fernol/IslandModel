package com.javarush.university.projects.fauna.herbivorous;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Caterpillar extends Herbivores{
    private static final Config config = ConfigFactory.load().getConfig("caterpillar");
    public static final int id = 14;


    public Caterpillar() {
        super(config, id);
    }
}
