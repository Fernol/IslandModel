package com.javarush.university.projects.fauna.predator;

import com.javarush.university.projects.fauna.Animal;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.List;

public abstract class Predator extends Animal {

    protected Predator(Config config, int id) {
        super(config, id);
    }
}
