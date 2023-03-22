package it.unibo.smol.model.impl;

import java.util.Map;

import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.GameState;
import it.unibo.smol.model.api.World;
import it.unibo.smol.view.GameMap;
import javafx.geometry.Point2D;

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
        return false; //world.getLifePlants().isEmpty();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame() {
        new EntityFactoryImpl().createPlayer(GameMap.WIDTH / 2, GameMap.HEIGHT / 2);
        new EntityFactoryImpl().createWeapon(GameMap.WIDTH / 2, GameMap.HEIGHT / 2);
        new EntityFactoryImpl().createBasicEnemy(new Point2D(GameMap.BORDER_WIDTH / 2, GameMap.BORDER_HEIGHT / 2), this.world);
    }

}
