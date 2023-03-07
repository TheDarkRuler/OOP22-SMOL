package it.unibo.smol.common;

import javafx.scene.input.KeyCode;

/**
 * Potential directions in which the entity could move.
 */

public enum Directions {

    /**
     * The player moves up.
     */
    UP(KeyCode.W),

    /**
     * The player moves down.
     */
    DOWN(KeyCode.S),

    /**
     * The player moves left.
     */
    LEFT(KeyCode.A),

    /**
     * The player moves right.
     */
    RIGHT(KeyCode.D);

    private final KeyCode direction;

    /**
     * Constructor for the enum.
     * @param direction
     */
    Directions(final KeyCode direction) {
        this.direction = direction;
    }

    /**
     * gets the KeyValue of the directions.
     * @return the the KeyValue of the directions
     */
    public KeyCode getDirection() {
        return this.direction;
    }
}
