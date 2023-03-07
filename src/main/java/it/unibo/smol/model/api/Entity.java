package it.unibo.smol.model.api;

import it.unibo.smol.model.impl.HealthComponent;

/**
 *  Functional interface for object and entities in the game.
 */
public interface Entity {
    /**
     * Get the current x coordinate of the object.
     * @return {@code x}
     */
    int getCurrentX();

    /**
     * Get the current x coordinate of the object.
     * @return {@code y}
     */
    int getCurrentY();

    /**
     * Get the world 
     * @return
     */
    World getWorld();

    /**
     * Set a new x coordinate for the object.
     * @param x : the new x coordinate of the object
     */
    void setX(int x);

    /**
     * Set a new y coordinate for the object.
     * @param y : the new y coordinate of the object
     */
    void setY(int y);

    void setWorld(World w);

    HealthComponent getHealthComp();

    PhysicsComponent getPhysicsComp();

    GraphicComponent getGraphicComp();

    InputComponent getInputComp();

    /**
     * Update all the component of the object.
     */
    void update();
}
