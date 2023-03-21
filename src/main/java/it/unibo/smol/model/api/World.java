package it.unibo.smol.model.api;

import java.util.List;
import java.util.Map;

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
    List<Entity> getEntities();

    /**
     * @param thisEntity entity to remove from the list of entities.
     */
    void remove(Entity thisEntity);

    /**
     * @return score.
     */
    int getScore();

    /**
     * @return updated Word.
     */
    World updateWorld();

    /**
     * @return difficulty increment.
     */
    int diffIncrement();

    /**
     * @param thisEntity is the entity to add to the list of entities.
     */
    void addEntity(Entity thisEntity);

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

}
