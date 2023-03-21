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
        this.world = new WorldImpl(world);
        this.occupiedPlants = new HashMap<>();
    }

    /**
     * Constructor.
     * @param world
     */
    public GameStateImpl(final GameState gameState) {
        this.world = gameState.getWorld();
        this.occupiedPlants = gameState.occupiedPlants();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld() {
        return new WorldImpl(this.world);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        return false;//world.getLifePlants().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void incScore(final int quantity) {
        this.world.incScore(quantity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return this.world.getScore();
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
        return new HashMap<>(this.occupiedPlants);
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
