package com.javarush.university.projects.fauna.herbivorous;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Buffalo extends Herbivores {
    private static final Config config = ConfigFactory.load().getConfig("buffalo");
    public static final int id = 12;

    public Buffalo() {
        super(config, id);
    }
}
