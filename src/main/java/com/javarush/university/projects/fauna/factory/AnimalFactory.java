package com.javarush.university.projects.fauna.factory;

import com.javarush.university.projects.fauna.Animal;
import com.javarush.university.projects.fauna.predator.*;
import com.javarush.university.projects.fauna.herbivorous.*;
import com.typesafe.config.Config;

import java.io.ObjectInputFilter;

public class AnimalFactory {

    public static Animal getAnimalByType(Animal animal) {
        if (animal instanceof Predator) {
            if (animal instanceof Wolf) return new Wolf();
            if (animal instanceof Boa) return new Boa();
            if (animal instanceof Fox) return new Fox();
            if (animal instanceof Bear) return new Bear();
            if (animal instanceof Aquila) return new Aquila();
        }
        else {
            if (animal instanceof Boar) return new Boar();
            if (animal instanceof Buffalo) return new Buffalo();
            if (animal instanceof Caterpillar) return new Caterpillar();
            if (animal instanceof Deer) return new Deer();
            if (animal instanceof Duck) return new Duck();
            if (animal instanceof Goat) return new Goat();
            if (animal instanceof Mouse) return new Mouse();
            if (animal instanceof Horse) return new Horse();
            if (animal instanceof Rabbit) return new Rabbit();
            if (animal instanceof Sheep) return new Sheep();
        }
        return null;
    }

    public static Animal getAnimalByID(int id) {
        if (Boar.id == id) return new Boar();
        if (Buffalo.id == id) return new Buffalo();
        if (Caterpillar.id == id) return new Caterpillar();
        if (Deer.id == id) return new Deer();
        if (Duck.id == id) return new Duck();
        if (Goat.id == id) return new Goat();
        if (Horse.id == id) return new Horse();
        if (Mouse.id == id) return new Mouse();
        if (Rabbit.id == id) return new Rabbit();
        if (Sheep.id == id) return new Sheep();

        if (Aquila.id == id) return new Aquila();
        if (Bear.id == id) return new Bear();
        if (Boa.id == id) return new Boa();
        if (Fox.id == id) return new Fox();
        if (Wolf.id == id) return new Wolf();

        return null;
    }
}
