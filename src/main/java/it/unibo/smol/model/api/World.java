package it.unibo.smol.model.api;

import java.util.List;
import java.util.Optional;

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
     * @return a world without that mole
     */
    World removeMole();

    /**
     * @return a world without that plant
     */
    World removeLifePlants();

    /**
     * @return updated Word
     */
    World updateWorld();
}
