package it.unibo.smol.model.api;

import java.util.List;

public interface World {
    /**
     * @return list of moles
     */
    List<Entity> getMoles();

    /**
     * @return player
     */
    Entity getPlayer();

    /**
     * @return lifePlants
     */
    List<Entity> getLifePlants();

    /**
     * @return all entities 
     */
    List<Entity> getEntities();

    /**
     * @return a world without that mole
     */
    World removeMole();

    /**
     * @return a world without that plant
     */
    World removeLifePlants();

    /**
     * @return gmae map
     */
    GameMap getMap();

    /**
     * @return updated Word
     */
    World updateWorld();
}
