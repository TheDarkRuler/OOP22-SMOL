package it.unibo.smol.controller.api;

import java.util.Map;
import java.util.Optional;

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
     * sets the keyInput in gamestate.
     * @param keyInputs
     */
    void setKeyInputs(Optional<KeyInputs> keyInputs);

    /**
     * sets the mouseInputs in gamestate.
     * @param mouseInputs
     */
    void setMouseInputs(Optional<MouseInputs> mouseInputs);

    /**
     * gets the entity factory for the entities creation.
     * @return the entity factory
     */
    EntityFactory getEntityFactory();

    /**
     * stops the creation of enemies.
     */
    void stopEnemyCreation();

}
