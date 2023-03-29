package it.unibo.smol.model.api;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;

import it.unibo.smol.controller.input.KeyInputs;
import it.unibo.smol.controller.input.MouseInputs;

/**
 * Interface for World: a container for entities.
 */
public interface World {
    /**
     * @return list of moles.
     */
    List<Entity> getMoles();

    /**
     * @return player.
     */
    Entity getPlayer();

    /**
     * @return lifePlants.
     */
    List<Entity> getLifePlants();

    /**
     * @return all entities.
     */
    ConcurrentLinkedDeque<Entity> getEntities();

    /**
     * @param thisEntity entity to remove from the list of entities.
     */
    void remove(Entity thisEntity);

    /**
     * @return score.
     */
    int getScore();

    /**
     *  update Word.
     */
    void updateWorld();

    /**
     * @return difficulty increment.
     */
    int diffIncrement();

    /**
     * @param thisEntity is the entity to add to the list of entities.
     */
    void addEntity(Entity thisEntity);

    /**
     * @param thisEntity is the entity to add to the list of entities.
     */
    void addFirstEntity(Entity thisEntity);

    /**
     * increments game current game score.
     * @param quantity is the incremental value
     */
    void incScore(int quantity);

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

    /**
     * sets the keyInput in world.
     * @param keyInputs
     */
    void setKeyInputs(KeyInputs keyInputs);

    /**
     * sets the mouseInputs in world.
     *  @param mouseInputs
     */
    void setMouseInputs(MouseInputs mouseInputs);

    /**
     * gets the keyInput in world.
     * @return the keyInput
     */
    KeyInputs getKeyInputs();

    /**
     * gets the mouseInputs in world.
     * @return the mouseInput
     */
    MouseInputs getMouseInputs();

}
