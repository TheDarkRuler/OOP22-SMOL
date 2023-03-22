package it.unibo.smol.model.impl;

import java.util.Map;

import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.GameState;
import it.unibo.smol.model.api.World;

/**
 * The implementation of the GameState.
 */
public class GameStateImpl implements GameState {
    private final World world;

    /**
     * Constructor.
     * @param world
     */
    public GameStateImpl(final World world) {
        this.world = new WorldImpl(world);
    }

    /**
     * copy constructor.
     * @param gameState the game state that we want to copy
     */
    public GameStateImpl(final GameState gameState) {
        this.world = gameState.getWorld();
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
        return world.getLifePlants().isEmpty();
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
        return getWorld().occupiedPlants();
    }

    @Override
    public void initGame() { 
    }

}
