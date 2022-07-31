package com.javarush.university.projects.island;

import com.javarush.university.projects.fauna.Animal;
import com.javarush.university.projects.fauna.Plants;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Cell {

    private final Set<Animal> animalOnCell = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private int plantsOnCell = 0;

    private final int x;
    private final int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Set<Animal> getAnimalListOnCell() {
        return this.animalOnCell;
    }

    public int getPlantsOnCell() {
        return this.plantsOnCell;
    }

    // Инициализация карты - игры
    protected static Cell[][] initMap(int maxX, int maxY) {
        Cell[][] cells = new Cell[maxX][maxY];
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
        return cells;
    }

    // Даёт рандомную позицию - клетку для животного.
    public static Cell getRandomCellForAnimal(Animal animal) {
        while (true) {
            int x = ThreadLocalRandom.current().nextInt(Island.maxX);
            int y = ThreadLocalRandom.current().nextInt(Island.maxY);
            Cell cell = Island.cells[x][y];

            if (addAnimalOnCell(cell, animal)) {
                Island.cells[x][y].getAnimalListOnCell().add(animal);
                return cell;
            }
        }
    }

    public static Cell getRandomCellForPlants(int maxPlants) {
        while (true) {
            int x = ThreadLocalRandom.current().nextInt(Island.maxX);
            int y = ThreadLocalRandom.current().nextInt(Island.maxY);
            Cell cell = Island.cells[x][y];

            if (addPlantsOnCell(cell)) {
                Island.cells[x][y].plantsOnCell = ThreadLocalRandom.current().nextInt(maxPlants);
                return cell;
            }
        }
    }

    public static Cell checkNewCell(Cell cell, Direction direction) {
        int x = cell.getX() + direction.getDeltaX();
        int y = cell.getY() + direction.getDeltaY();
        if (x < 0 || x >= Island.maxX || y < 0 || y >= Island.maxY) {
            return null;
        }
        return Island.cells[x][y];
    }

    // Если на клетке не находятся максимальное кол-во животных (по переменной animal.maxPerCell),
    // то происходит добавление животного на клетку, иначе false
    public static boolean addAnimalOnCell(Cell cell, Animal animal) {
        int count = 0;
        for (Animal animalOnCell :
                cell.animalOnCell) {
            if (animal.getClass() == animalOnCell.getClass())
                count++;
        }
        return count < animal.getMaxPerCell();
    }

    public static boolean addPlantsOnCell(Cell cell) {
        return cell.plantsOnCell < 200;
    }

    public static void removePlantsOnCell(Cell cell) {
        cell.plantsOnCell = cell.plantsOnCell - 1;
    }

    public static void removeAnimalOnCell(Cell cell, Animal animal) {
        cell.animalOnCell.remove(animal);
    }

}
