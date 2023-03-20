package it.unibo.smol.model.api;

import java.util.Map;

/**
 *  Interface for control the state of the game.
 */
public interface GameState {
    /**
     * Get the current world.
     * @return game world
     */
    World getWorld();

    /**
     * @return if game is Over or not.
     */
    boolean isGameOver();

    /**
     *  Notify the world to increase the score.
     */
    void incScore(int quantity);

    /**
     * Getter for the score.
     * @return the current score
     */
    int getScore();

    /**
     *  Notify the world to kill entities.
     */
    void notifyDeath();

    /**
     * @return a map of plants occupied.
     */
    Map<Entity, Boolean> occupiedPlants();

    /**
     * @param plant the plant to set free.
     */
    void setPlantFree(Entity plant);

    /**
     * @param plant the plant to set occupied.
     */
    void setPlantOccupied(Entity plant);

}
