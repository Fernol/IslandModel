package com.javarush.university.projects.island;

import com.javarush.university.projects.fauna.Animal;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;


public class Modeling implements Runnable {
    private static final Config config = ConfigFactory.load().getConfig("map");
    private static List<Thread> threads = new ArrayList<>();
    private final Animal animal;

    private Modeling(Animal animal) {
        this.animal = animal;
    }

    public static void gameInitialization() throws InterruptedException {
        Island.addAnimalsToMap();
        Modeling.startGame();
    }

    private static void startGame() throws InterruptedException {
        for (int n = 0; n < config.getInt("numberMoves"); n++) {
            for (int i = 0; i < Island.maxX; i++) {
                for (int j = 0; j < Island.maxY; j++) {
                    Set<Animal> animalSet = Island.cells[i][j].getAnimalListOnCell();
                    if (!animalSet.isEmpty()) {
                        for (Animal animal :
                                animalSet) {
                            Thread thread = new Thread(new Modeling(animal));
                            threads.add(thread);
                            thread.start();
                        }
                    }
                }
            }
            threads.forEach(thread -> {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            threads = new ArrayList<>();
            Statistic.printStatsPerTurn();
            Thread.sleep(5000);
        }
        Statistic.printGlobalStats();
    }

    @Override
    public void run() {
        this.animal.move();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        this.animal.action();

    }
}
