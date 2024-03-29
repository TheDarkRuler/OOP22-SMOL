package it.unibo.smol.model.api;

import javafx.geometry.Point2D;
import java.util.Optional;

import it.unibo.smol.controller.api.InputComponent;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.impl.HealthComponent;
import it.unibo.smol.view.api.GraphicComponent;

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
    Optional<World> getWorld();

    /**
     * Add the new x coordinate to the current X.
     * @param x : the new x to add in the object
     */
    void setX(double x);

    /**
     * Add the new y coordinate to the current Y.
     * @param y : the new y to add in the object
     */
    void setY(double y);

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
    Optional<PhysicsComponent> getPhysicsComp();

    /**
     * Getter for Graphic component.
     * @return The GraphicComponent 
     */
    Optional<GraphicComponent> getGraphicComp();

    /**
     * Update the position of the entity based on the physicsComponent.
     */
    void updatePosition();

    /**
     * Process the input received by the inputComponent.
     */
    void processInput();

    /**
     * Check the status of the healthComponent.
     */
    void checkHealth();

    /**
     * Update all the component of the Entity.
     */
    void update();

}
