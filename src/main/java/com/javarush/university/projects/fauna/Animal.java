package com.javarush.university.projects.fauna;

import com.javarush.university.projects.fauna.biology.Sex;
import com.javarush.university.projects.fauna.factory.AnimalFactory;
import com.javarush.university.projects.fauna.herbivorous.Herbivores;
import com.javarush.university.projects.island.Cell;
import com.javarush.university.projects.island.Direction;
import com.javarush.university.projects.island.Island;
import com.javarush.university.projects.island.Statistic;
import com.typesafe.config.Config;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal {

    public final int id;

    private final double weight;

    private final int maxPerCell;

    private final int speed;

    public final double maxEat;

    private double currentEat = 0.;

    private volatile boolean targetToEat = false;

    private final List<Integer> canEat;

    private final Sex gender = Sex.getGender();

    // Клетка - позиция животного.
    private Cell cell;

    public final Statistic statistic = new Statistic();

    protected Animal(Config config, int id) {
        this.weight = config.getDouble("weight");
        this.maxPerCell = config.getInt("maxPerCell");
        this.speed = config.getInt("speed");
        this.maxEat = config.getDouble("maxEat");
        this.canEat = config.getIntList("canEat");
        this.cell = Cell.getRandomCellForAnimal(this);
        this.id = id;
        this.statistic.setCurrentCell(cell);
    }

    private synchronized void eat() {
        if (this instanceof Herbivores && this.cell.getPlantsOnCell() > 0) {
            Cell.removePlantsOnCell(this.cell);
            this.currentEat += 1;
            return;
        }
        if (this.cell.getAnimalListOnCell().size() <= 1) return;
        Set<Animal> animalsOnCell = cell.getAnimalListOnCell();
        Animal sacrifice = null;
        for (Animal victim :
                animalsOnCell) {
            if (this.canEat.get(victim.id) > 0 && !victim.targetToEat) {
                sacrifice = victim;
                sacrifice.targetToEat = true;
                break;
            }
        }
        if (sacrifice == null) return;

        int randomEat = ThreadLocalRandom.current().nextInt(100) + 1;
        if (randomEat > this.canEat.get(sacrifice.id)) {
            this.currentEat += sacrifice.weight;
            this.statistic.setEat(true);
            this.statistic.setEatenAnimal(sacrifice);
            sacrifice.die();
        }
    }

    public void die() {
        Cell.removeAnimalOnCell(Island.cells[this.cell.getX()][this.cell.getY()], this);
    }

    // Размножение начинают мужчины
    // (сделано в угоду быстродействию и многопоточности)
    private void multiply() {
        if (this.cell.getAnimalListOnCell().size() <= 1) return;
        if (this.gender == Sex.FEMALE) return;

        Set<Animal> animalsOnCell = cell.getAnimalListOnCell();
        Animal partner = null;
        for (Animal animal :
                animalsOnCell) {
            if (animal.getClass() == this.getClass() && animal.gender == Sex.FEMALE) {
                partner = animal;
                break;
            }
        }
        if (partner == null) return;

        Animal newAnimal = AnimalFactory.getAnimalByType(this);
        this.cell.getAnimalListOnCell().add(newAnimal);

        this.statistic.setMultiply(true);
        this.statistic.setMultiplePartner(partner);

    }

    // Выбор того, что сделает животное, когда придет на клетку.
    // Размножение только, если животные насыщенны.
    public void action() {
        if (this.currentEat >= this.maxEat) {
            this.multiply();
        } else {
            this.eat();
        }
    }

    public void move() {
        int currentSpeed = ThreadLocalRandom.current().nextInt(speed + 1);
        for (int i = 0; i < currentSpeed; i++) {
            // Если все клетки будут заблокированы для перемещения,
            // то рано или поздно выпадет Direction.NO - животное останется на месте.
            while (true) {
                Direction direction = Direction.getRandomDirection();
                Cell newCell = Cell.checkNewCell(this.cell, direction);
                // Если новая клетка существует и в неё возможно добавить animal.
                if (newCell != null && direction != Direction.NO && Cell.addAnimalOnCell(newCell, this)) {
                    Cell.removeAnimalOnCell(cell, this);
                    newCell.getAnimalListOnCell().add(this);
                    this.cell = newCell;

                    this.statistic.setMoving(true);
                    this.statistic.addAmountMoves(1);
                    this.statistic.setNewCell(newCell);

                    break;
                }
            }
        }
    }

    public int getMaxPerCell() {
        return maxPerCell;
    }

    public Cell getCurrentCell() {
        return cell;
    }

    public double getCurrentEat() {
        return currentEat;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " (" + this.hashCode() + ")";
    }
}
