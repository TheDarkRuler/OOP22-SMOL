package it.unibo.smol.model.api;

import javafx.geometry.Point2D;

/**
 *  Functional interface for object and entities in the game.
 */
public interface GameObject {
    /**
     * Get the current position of the object.
     * @param pos : is current position
     * @return {@code pos}
     */
    Point2D getCurrentPos(Point2D pos);

    /**
     * Get the current velocity of the object.
     * @param vel : is current velocity
     * @return {@code vel}
     */
    //Vector<Double> getCurrentVel(Vector2D vel);

    //World getWorld(World w);

    /**
     * Set a new position for the object.
     * @param pos : the new position of the object
     */
    void setPos(Point2D pos);

    /**
     * Set a new velocity for the object.
     * @param vel : the new velocity of the object
     */
    //void setVel(Vector2D vel);

    //void setWorld(World w);

    /**
     * Update all the component of the object.
     */
    void updateAll();

    /**
     * Update the physics component of the object.
     */
    void updatePhysics();

    /**
     * Update the graphics component of the object.
     */
    void updateGraphics();

    /**
     * Update the input component of the object.
     */
    void updateInput();

    /**
     * Update the health component of the object.
     */
    void updateHealth();
}
