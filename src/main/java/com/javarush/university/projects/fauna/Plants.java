package com.javarush.university.projects.fauna;

import com.javarush.university.projects.island.Cell;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class Plants {
    private static final Config config = ConfigFactory.load().getConfig("map");
    private final int maxPlantsOnCell = config.getInt("maxPlantsOnCell");
    private final Cell cell = Cell.getRandomCellForPlants(maxPlantsOnCell);

    public Cell getCell() {
        return cell;
    }
}
