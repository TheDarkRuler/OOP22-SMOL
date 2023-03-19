package it.unibo.smol.model.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.GameState;
import it.unibo.smol.model.api.World;

/**
 * The implementation of the GameState.
 */
public class GameStateImpl implements GameState {
    private static final Boolean OCCUPIED = true;
    private static final Boolean FREE = false;
    private final World world;
    private final Map<Entity, Boolean> occupiedPlants;

    /**
     * Constructor.
     * @param world
     */
    public GameStateImpl(final World world) {
        this.world = world;
        this.occupiedPlants = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld() {
        return this.world;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        return world.getLifePlants().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incScore() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decScore() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyDeath() {
        // TODO Auto-generated method stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Entity, Boolean> occupiedPlants() {
        updateLifePlants();
        return this.occupiedPlants;
    }

    private void updateLifePlants() {
        this.world.getLifePlants().forEach(plant -> {
            if (!occupiedPlants.containsKey(plant)) {
                occupiedPlants.put(plant, FREE);
            }
        });
        checkRemoved();
    }

    private void checkRemoved() {
        occupiedPlants.keySet().forEach(plant -> {
            if (!this.world.getLifePlants().contains(plant)) {
                occupiedPlants.remove(plant);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlantFree(final Entity plant) {
        updateLifePlants();
        if (occupiedPlants.containsKey(plant)) {
            occupiedPlants.put(plant, FREE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlantOccupied(final Entity plant) {
        updateLifePlants();
        if (occupiedPlants.containsKey(plant)) {
            occupiedPlants.put(plant, OCCUPIED);
        }
    }

}
