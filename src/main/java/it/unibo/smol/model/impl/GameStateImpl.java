package it.unibo.smol.model.impl;

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
        this.world = world;
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
}
