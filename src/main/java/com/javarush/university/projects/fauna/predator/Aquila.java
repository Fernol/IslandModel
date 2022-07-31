package com.javarush.university.projects.fauna.predator;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Aquila extends Predator {

    private static final Config config = ConfigFactory.load().getConfig("aquila");
    public static final int id = 4;

    public Aquila() {
        super(config, id);
    }
}
