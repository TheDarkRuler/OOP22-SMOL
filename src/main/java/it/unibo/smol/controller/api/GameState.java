package it.unibo.smol.controller.api;

import java.util.Map;

import it.unibo.smol.controller.input.KeyInputs;
import it.unibo.smol.controller.input.MouseInputs;
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
    World getWorld();

    /**
     * @return if game is Over or not.
     */
    boolean isGameOver();

    /**
     * Notify the world to increase the score.
     * @param quantity is the incremental value
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
     * Initialize the game.
     */
    void initGame();

    /**
     * sets the keyInput in gamestate.
     */
    void setKeyInputs(KeyInputs keyInputs);

    /**
     * sets the mouseInputs in gamestate.
     */
    void setMouseInputs(MouseInputs mouseInputs);

    EntityFactory getEntityFactory();

}
