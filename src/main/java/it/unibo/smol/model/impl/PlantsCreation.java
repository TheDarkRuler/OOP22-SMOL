package it.unibo.smol.model.impl;

import java.util.Random;

import it.unibo.smol.common.Constant;
import it.unibo.smol.common.GameMap;
import it.unibo.smol.controller.api.GameState;
import javafx.geometry.Point2D;

public class PlantsCreation {

    private static final int NUM_PLANTS = 4;
    
    private GameState gs;
    private Random rand;

    public PlantsCreation(final GameState gs) {
        this.gs = gs;
        rand = new Random();
        createPlants();
    }

    private void createPlants() {
        for (int i = 0; i < NUM_PLANTS; i++) {
            Point2D plantPosition = findPosition(i);
            gs.getWorld()
                .addEntity(gs.getEntityFactory()
                    .createLifePlants(plantPosition.getX(), plantPosition.getY(), gs.getWorld()));
        }
    }

    private Point2D findPosition(final int i) {
        switch (i) {
            case 0:
                return new Point2D(plantRandX(),
                    plantRandY());
            case 1:
                return new Point2D(plantRandX() + (GameMap.MAP_WIDTH / 2),
                    plantRandY());
            case 2:
                return new Point2D(plantRandX(),
                    plantRandY() + (GameMap.MAP_HEIGHT / 2));
            case 3:
                return new Point2D(plantRandX() + (GameMap.MAP_WIDTH / 2),
                    plantRandY() + (GameMap.MAP_HEIGHT / 2)); 
            default:
                return null;
        }
    }

    /**
     * finds a random position in the x axis.
     * @return a random x position
     */
    private double plantRandX() {
        return GameMap.BORDER_WIDTH / 2  + Constant.HEALTH_WIDTH / 2
            + rand.nextDouble(GameMap.MAP_WIDTH / 2 - Constant.HEALTH_WIDTH); 
    }

    /**
     * finds a random position in the y axis.
     * @return a random y position
     */
    private double plantRandY() {
        return GameMap.BORDER_HEIGHT / 2 + Constant.HEALTH_HEIGHT / 2
            + rand.nextDouble(GameMap.MAP_HEIGHT / 2 - Constant.HEALTH_HEIGHT);
    }
}
