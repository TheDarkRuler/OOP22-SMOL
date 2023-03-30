package it.unibo.smol.controller.impl;

import java.util.Map;

import it.unibo.smol.common.hitbox.RectangleHB;
import it.unibo.smol.controller.api.GameState;
import it.unibo.smol.controller.input.KeyInputs;
import it.unibo.smol.controller.input.MouseInputs;
import it.unibo.smol.model.ScoreLocalStorage;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.EntityFactory;
import it.unibo.smol.model.api.World;
import it.unibo.smol.model.impl.EnemyCreation;
import it.unibo.smol.model.impl.EntityFactoryImpl;
import it.unibo.smol.model.impl.PlantsCreation;
import it.unibo.smol.model.impl.WorldImpl;
import it.unibo.smol.view.GameMap;
import javafx.geometry.Point2D;

/**
 * The implementation of the GameState.
 */
public class GameStateImpl implements GameState {
    private final World world;
    private final EntityFactory entityFactory;
    private EnemyCreation enemyCreator;
    private final ScoreLocalStorage scoreStorage;

    /**
     * Constructor.
     * @param world
     */
    public GameStateImpl(final World world) {
        this.world = new WorldImpl(world);
        this.entityFactory = new EntityFactoryImpl();
        this.scoreStorage = new ScoreLocalStorage(this);
    }

    /**
     * copy constructor.
     * @param gameState the game state that we want to copy
     */
    public GameStateImpl(final GameState gameState) {
        this.world = gameState.getWorld();
        this.entityFactory = new EntityFactoryImpl();
        this.scoreStorage = new ScoreLocalStorage(gameState);
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
        return !world.getEntities().stream()
            .filter(x -> x.getType() == Type.HEALTH)
            .findAny().isPresent();
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
        //v OVEST WALL v
        world.addEntity(entityFactory.createWall(new RectangleHB(GameMap.BORDER_WIDTH, GameMap.HEIGHT, new Point2D(GameMap.BORDER_WIDTH / 2 , GameMap.HEIGHT/2)),this.world));
        //v EAST WALL v
        world.addEntity(entityFactory.createWall(new RectangleHB(GameMap.BORDER_WIDTH, GameMap.HEIGHT, new Point2D(GameMap.MAP_WIDTH + GameMap.BORDER_WIDTH / 2 , GameMap.HEIGHT/2)),this.world));
        //v NORTH WALL v
        world.addEntity(entityFactory.createWall(new RectangleHB(GameMap.WIDTH, GameMap.BORDER_HEIGHT, new Point2D(GameMap.WIDTH / 2, GameMap.BORDER_HEIGHT/2)),this.world));
        //v SUD WALL v
        world.addEntity(entityFactory.createWall(new RectangleHB(GameMap.WIDTH, GameMap.BORDER_HEIGHT, new Point2D(GameMap.WIDTH / 2 , GameMap.MAP_HEIGHT + GameMap.BORDER_HEIGHT/2)),this.world));
        
        world.addEntity(entityFactory.createPlayer(GameMap.WIDTH / 2, GameMap.HEIGHT / 2, this.world));
        world.addEntity(entityFactory.createWeapon(GameMap.WIDTH / 2, (GameMap.HEIGHT / 2), this.world));
        new PlantsCreation(this);
        this.enemyCreator = new EnemyCreation(this);
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

    public EntityFactory getEntityFactory() {
        return this.entityFactory;
    }

    public void stopEnemyCreation() {
        this.enemyCreator.stopCreation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRecord() {
        return this.scoreStorage.getRecord();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyWrite() {
        this.scoreStorage.writeFile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyRead() {
        this.scoreStorage.readFile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScoreLocalStorage getScoreLocalStorage() {
        return this.scoreStorage;
    }

}
