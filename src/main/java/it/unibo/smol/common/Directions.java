package it.unibo.smol.common;

import java.awt.event.KeyEvent;

/**
 * Potential directions in which the entity could move.
 */

public enum Directions {

    /**
     * The player moves up.
     */
    UP(KeyEvent.VK_W),

    /**
     * The player moves down.
     */
    DOWN(KeyEvent.VK_S),

    /**
     * The player moves left.
     */
    LEFT(KeyEvent.VK_A),

    /**
     * The player moves right.
     */
    RIGHT(KeyEvent.VK_D);

    private final int direction;

    /**
     * Constructor for the enum.
     * @param direction
     */
    Directions(final int direction) {
        this.direction = direction;
    }

    /**
     * gets the KeyValue of the directions.
     * @return the the KeyValue of the directions
     */
    public int getDirection() {
        return this.direction;
    }
}
