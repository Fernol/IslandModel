package com.javarush.university.projects.island;

import com.javarush.university.projects.fauna.Animal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Statistic {

    private static int totalMoves;
    private static int totalEatenAnimals;
    private static int totalBirthAnimals;
    private static int currentTurn = 0;
    private int amountMoves;
    private boolean isMoving;

    private boolean isEat;
    private boolean isMultiply;
    private Animal multiplePartner;
    private Animal eatenAnimal;
    private Cell currentCell;
    private Cell newCell;


    public static void printGlobalStats() {
        System.out.println("Всего ходов: " + currentTurn);
        System.out.println("Всего было пройдено расстояния: " + totalMoves);
        System.out.println("Всего животных было съедено: " + totalEatenAnimals);
        System.out.println("Всего животных родилось: " + totalBirthAnimals);
    }

    public static void printStatsPerTurn() {
        Statistic.currentTurn++;
        System.out.println("Ход номер: " + Statistic.currentTurn + "\n");
        for (int i = 0; i < Island.maxX; i++) {
            for (int j = 0; j < Island.maxY; j++) {
                Set<Animal> animalSet = Island.cells[i][j].getAnimalListOnCell();
                if (!animalSet.isEmpty()) {
                    animalSet.forEach(Statistic::printIndividualStatsPerTurnIsMoving);
                }
            }
        }

        System.out.println("----------------------------------------");

        for (int i = 0; i < Island.maxX; i++) {
            for (int j = 0; j < Island.maxY; j++) {
                Set<Animal> animalSet = Island.cells[i][j].getAnimalListOnCell();
                if (!animalSet.isEmpty()) {
                    animalSet.forEach(Statistic::printIndividualStatsPerTurnIsAction);
                }
            }
        }

        System.out.println();
        System.out.println("----------------------------------");
        System.out.println("----------------------------------");
        System.out.println();
    }

    private static void printIndividualStatsPerTurnIsMoving(Animal animal) {
        Statistic statistic = animal.statistic;
        statistic.newCell = animal.getCurrentCell();
        if (statistic.isMoving) {
            System.out.println(animal + " прошел " + statistic.amountMoves + " шаг(-а). " +
                    "С клетки " +
                    animal.statistic.currentCell.getX() + ":" + animal.statistic.currentCell.getY() + " на клетку "
                    + animal.statistic.newCell.getX() + ":" + animal.statistic.newCell.getY());
            statistic.currentCell = statistic.newCell;
            statistic.amountMoves = 0;
            statistic.isMoving = false;
        } else {
            System.out.println(animal + " остался на месте. Клетка: "
                    + animal.statistic.currentCell.getX() + ":" + animal.statistic.currentCell.getY());
        }

    }

    private static void printIndividualStatsPerTurnIsAction(Animal animal) {
        if (animal.statistic.isEat) {
            System.out.println(animal + " съел " + animal.statistic.eatenAnimal);
            animal.statistic.isEat = false;
        }
        if (animal.statistic.isMultiply) {
            System.out.println("У " + animal + " и " + animal.statistic.multiplePartner + " родился ребёнок!");
            animal.statistic.isMultiply = false;
        }
    }

    public void addAmountMoves(int amountMoves) {
        this.amountMoves += amountMoves;
        totalMoves++;
    }

    public void setEatenAnimal(Animal eatenAnimal) {
        this.eatenAnimal = eatenAnimal;
        totalEatenAnimals++;
    }

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public void setNewCell(Cell newCell) {
        this.newCell = newCell;
    }

    public void setEat(boolean eat) {
        this.isEat = eat;
    }

    public void setMultiply(boolean multiply) {
        this.isMultiply = multiply;
        totalBirthAnimals++;
    }

    public void setMultiplePartner(Animal animal) {
        this.multiplePartner = animal;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }
}
