package it.unibo.smol.controller.api;

import java.util.Optional;

import it.unibo.smol.common.Directions;
import javafx.geometry.Point2D;

/**
 * Interface to manage inputs.
 */
public interface InputComponent {
    /**
     * @return current direction if the entity have a direction, otherwise it gets an Optional empty
     */
    Optional<Directions> getDirection();

    /**
     * @return current position if the entity have a direction, otherwise it gets an Optional empty
     */
    Optional<Point2D> getPosition();

    /**
     * @return true if the entity is hittable, false otherwise
     */
    boolean isHittable();

    /**
     * Freeze the input for some time.
     * @param millisec the time express in millisecond
     */
    void freezeInput(int millisec);

    /**
     * gets the enemyInput.
     * @return enemyinput
     */
    Optional<EnemyInput> getEnemyInput();
}
