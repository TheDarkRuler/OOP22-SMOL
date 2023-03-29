package it.unibo.smol.model.impl;

import java.util.Random;

import it.unibo.smol.common.Constant;
import it.unibo.smol.common.hitbox.RectangleHB;
import it.unibo.smol.controller.api.GameState;
import it.unibo.smol.view.GameMap;
import javafx.geometry.Point2D;

public class PlantsCreation {

    private boolean validPosition;
    private GameState gs;
    private Random rand;

    public PlantsCreation(final GameState gs) {
        this.gs = gs;
        this.validPosition = true;
        this.rand = new Random();
        createPlants();
    }

    private void createPlants() {
        for (int i = 0; i < Constant.NUM_PLANTS; i++) {
            Point2D plantPosition;
            do {
                this.validPosition = true;
                plantPosition = findPosition(i);
                final var temp = new RectangleHB(Constant.HEALTH_WIDTH, Constant.HEALTH_HEIGHT, plantPosition);
                gs.getWorld().getEntities().forEach(x -> {
                    if (temp.isColliding(x.getPhysicsComp().getHitBox())) {
                        this.validPosition = false;
                    }
                });
            } while (!this.validPosition);
            gs.getWorld()
                .addFirstEntity(gs.getEntityFactory()
                    .createLifePlants(plantPosition.getX(), plantPosition.getY(), gs.getWorld()));
        }
    }

    private Point2D findPosition(final int i) {
        switch (i) {
            case 0 -> {
                return new Point2D(plantRand(1+GameMap.BORDER_WIDTH, GameMap.MAP_WIDTH),
                    plantRand(1+GameMap.BORDER_HEIGHT, GameMap.MAP_HEIGHT));
            }
            case 1 -> {
                return new Point2D(plantRand(1+GameMap.WIDTH/2, GameMap.MAP_WIDTH),
                    plantRand(1+GameMap.BORDER_HEIGHT, GameMap.HEIGHT/2));
            }
            case 2 -> {
                return new Point2D(plantRand(1+GameMap.BORDER_WIDTH, GameMap.WIDTH/2),
                    plantRand(1+GameMap.HEIGHT/2, GameMap.MAP_HEIGHT));
            } 
            case 3 -> {
                return new Point2D(plantRand(1+GameMap.WIDTH/3, GameMap.WIDTH *2/3),
                    plantRand(1+GameMap.HEIGHT/3, GameMap.HEIGHT*2/3));
            }
            default -> {
                return null;
            }
        }
    }

    /**
     * finds a random position in the y axis.
     * @return a random y position
     */
    private double plantRand(double origin, double bound) {
        /*return GameMap.BORDER_HEIGHT + Constant.HEALTH_HEIGHT / 2
            + rand.nextDouble(GameMap.MAP_HEIGHT - Constant.HEALTH_HEIGHT / 2) + bound;*/
        return rand.nextDouble(origin, bound);
    }
}
