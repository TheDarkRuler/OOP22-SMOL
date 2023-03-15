package it.unibo.smol.model.api;

import java.util.List;

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
     * @return gmae map.
     */
    GameMap getMap();

    /**
     * @return score.
     */
    int getScore();

    /**
     * @return updated Word.
     */
    World updateWorld();

}
