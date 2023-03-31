package it.unibo.smol.controller.impl;

import java.util.Map;
import java.util.Optional;

import it.unibo.smol.common.Constant;
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
    private String folderName;

    /**
     * Constructor.
     * @param world
     */
    public GameStateImpl(final World world) {
        this.world = new WorldImpl(world);
        this.entityFactory = new EntityFactoryImpl();
        this.enemyCreator = new EnemyCreation(Optional.of(this));
        this.scoreStorage = new ScoreLocalStorage(Optional.of(this));
        this.folderName = Constant.KEY_PIXEL_SKINS;
    }

    /**
     * copy constructor.
     * @param gameState the game state that we want to copy
     */
    public GameStateImpl(final GameState gameState) {
        this.world = gameState.getWorld().orElseThrow();
        this.entityFactory = new EntityFactoryImpl();
        this.scoreStorage = new ScoreLocalStorage(Optional.of(gameState));
        this.folderName = gameState.getSkins();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<World> getWorld() {
        return Optional.of(this.world);
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
    public int getScore() {
        return this.world.getScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Entity, Boolean> occupiedPlants() {
        return getWorld().orElseThrow().occupiedPlants();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame() {
        //v OVEST WALL v
        world.addEntity(entityFactory.createWall(new RectangleHB(GameMap.BORDER_WIDTH, GameMap.HEIGHT,
            new Point2D(GameMap.BORDER_WIDTH / 2, GameMap.HEIGHT / 2)), this.world));
        //v EAST WALL v
        world.addEntity(entityFactory.createWall(new RectangleHB(GameMap.BORDER_WIDTH, GameMap.HEIGHT,
            new Point2D(GameMap.MAP_WIDTH + GameMap.BORDER_WIDTH / 2, GameMap.HEIGHT / 2)), this.world));
        //v NORTH WALL v
        world.addEntity(entityFactory.createWall(new RectangleHB(GameMap.WIDTH, GameMap.BORDER_HEIGHT,
            new Point2D(GameMap.WIDTH / 2, GameMap.BORDER_HEIGHT / 2)), this.world));
        //v SUD WALL v
        world.addEntity(entityFactory.createWall(new RectangleHB(GameMap.WIDTH, GameMap.BORDER_HEIGHT,
            new Point2D(GameMap.WIDTH / 2, GameMap.MAP_HEIGHT + GameMap.BORDER_HEIGHT / 2)), this.world));

        world.addEntity(entityFactory.createPlayer(GameMap.WIDTH / 2, GameMap.HEIGHT / 2, this.world));
        world.addEntity(entityFactory.createWeapon(GameMap.WIDTH / 2, GameMap.HEIGHT / 2, this.world));
        new PlantsCreation(Optional.of(this));
        this.enemyCreator.startCreation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setKeyInputs(final Optional<KeyInputs> keyInputs) {
        this.world.setKeyInputs(keyInputs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMouseInputs(final Optional<MouseInputs> mouseInputs) {
        this.world.setMouseInputs(mouseInputs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityFactory getEntityFactory() {
        return this.entityFactory;
    }

    /**
     * stops the enemyCreation Timer.
     */
    @Override
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
    public Optional<ScoreLocalStorage> getScoreLocalStorage() {
        return Optional.of(this.scoreStorage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSkins(final String folderName) {
        this.folderName = folderName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSkins() {
        return this.folderName;
    }

}
