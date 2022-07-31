package com.javarush.university.projects.fauna.herbivorous;

import com.javarush.university.projects.fauna.Animal;
import com.typesafe.config.Config;

public abstract class Herbivores extends Animal {

    protected Herbivores(Config config, int id) {
        super(config, id);
    }
}
