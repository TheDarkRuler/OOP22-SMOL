package it.unibo.smol.controller.impl;

import java.util.Map;

import it.unibo.smol.controller.api.GameState;
import it.unibo.smol.controller.input.KeyInputs;
import it.unibo.smol.controller.input.MouseInputs;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.EntityFactory;
import it.unibo.smol.model.api.World;
import it.unibo.smol.model.impl.EntityFactoryImpl;
import it.unibo.smol.model.impl.WorldImpl;
import it.unibo.smol.view.GameMap;
import javafx.geometry.Point2D;

/**
 * The implementation of the GameState.
 */
public class GameStateImpl implements GameState {
    private final World world;
    private final EntityFactory entityFactory;

    /**
     * Constructor.
     * @param world
     */
    public GameStateImpl(final World world) {
        this.world = new WorldImpl(world);
        this.entityFactory = new EntityFactoryImpl();
    }

    /**
     * copy constructor.
     * @param gameState the game state that we want to copy
     */
    public GameStateImpl(final GameState gameState) {
        this.world = gameState.getWorld();
        this.entityFactory = new EntityFactoryImpl();
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
        world.addEntity(entityFactory.createPlayer(GameMap.WIDTH / 2, GameMap.HEIGHT / 2, this.world));
        world.addEntity(entityFactory.createWeapon(GameMap.WIDTH / 2, (GameMap.HEIGHT / 2) + 50, this.world));
        world.addEntity(entityFactory.createBasicEnemy(new Point2D(GameMap.BORDER_WIDTH, GameMap.BORDER_HEIGHT), this.world));
        world.addEntity(entityFactory.createLifePlants(GameMap.WIDTH*0.100, GameMap.HEIGHT*0.100, world));
        world.addEntity(entityFactory.createLifePlants(GameMap.WIDTH*0.800, GameMap.HEIGHT*0.800, world));
        world.addEntity(entityFactory.createLifePlants(GameMap.WIDTH*0.100, GameMap.HEIGHT*0.800, world));
        world.addEntity(entityFactory.createLifePlants(GameMap.WIDTH*0.800, GameMap.HEIGHT*0.100, world));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setKeyInputs(final KeyInputs keyInputs) {
        this.world.setKeyInputs(keyInputs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMouseInputs(final MouseInputs mouseInputs) {
        this.world.setMouseInputs(mouseInputs);
    }

}
