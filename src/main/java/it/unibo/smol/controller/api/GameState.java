package it.unibo.smol.controller.api;

import java.util.Map;
import java.util.Optional;

import it.unibo.smol.model.ScoreLocalStorage;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.EntityFactory;
import it.unibo.smol.model.api.World;

/**
 *  Interface for control the state of the game.
 */
public interface GameState {
    /**
     * Get the current world.
     * @return game world
     */
    Optional<World> getWorld();

    /**
     * @return if game is Over or not.
     */
    boolean isGameOver();

    /**
     * Getter for the score.
     * @return the current score
     */
    int getScore();

    /**
     * @return a map of plants occupied.
     */
    Map<Entity, Boolean> occupiedPlants();

    /**
     * Initialize the game.
     */
    void initGame();

    /**
     * gets the entity factory for the entities creation.
     * @return the entity factory
     */
    EntityFactory getEntityFactory();

    /**
     * stops the creation of enemies.
     */
    void stopEnemyCreation();

    /**
     * Getter for record score.
     * @return the record
     */
    int getRecord();

    /**
     * Notify the ScoreLocalStorage to write.
     */
    void notifyWrite();

    /**
     * Notify the ScoreLocalStorage to read.
     */
    void notifyRead();

    /**
     * Getter for ScoreLocalStorage.
     * @return the ScoreLocalStorage
     */
    Optional<ScoreLocalStorage> getScoreLocalStorage();

    /**
     * Set skin.
     * @param folderName name of the folder skin
     */
    void setSkins(String folderName);

    /**
     * Get skins.
     * @return name of the folder containing skins
     */
    String getSkins();
}
