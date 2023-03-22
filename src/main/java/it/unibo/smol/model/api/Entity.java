package it.unibo.smol.model.api;

import javafx.geometry.Point2D;
import java.util.Optional;

import it.unibo.smol.controller.api.InputComponent;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.impl.HealthComponent;

/**
 *  Functional interface for object and entities in the game.
 */
public interface Entity {
    /**
     * Get the current x coordinate of the object.
     * @return {@code x}
     */
    double getCurrentX();

    /**
     * Get the current x coordinate of the object.
     * @return {@code y}
     */
    double getCurrentY();

    /**
     * Get the current position of the object.
     * @return a Point2D
     */
    Point2D getCurrentPosition();

    /**
     * Getter for the World. 
     * @return the World
     */
    World getWorld();

    /**
     * Add the new x coordinate to the current X.
     * @param x : the new x to add in the object
     */
    void moveX(double x);

    /**
     * Add the new y coordinate to the current Y.
     * @param y : the new y to add in the object
     */
    void moveY(double y);

    /**
     * Set the current World.
     * @param w the World to set
     */
    void setWorld(World w);

    /**
     * Getter for the field type.
     * @return the {@link Type} of the entity
     */
    Type getType();

    /**
     * Getter for the HealthComponent.
     * @return The healthComponent
     */
    Optional<HealthComponent> getHealthComp();

    /**
     * Getter for the InputComponent.
     * @return The inputComponent
     */
    Optional<InputComponent> getInputComp();

    /**
     * Getter for the PhysicsComponent.
     * @return The PhysicsComponent
     */
    PhysicsComponent getPhysicsComp();

    /**
     * Update all the component of the object.
     */
    void update();

}
