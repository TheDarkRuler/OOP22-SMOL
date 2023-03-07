package it.unibo.smol.common;

/**
 * Potential directions in which the entity could move.
 */

public enum Directions {

    /**
     * The player moves up.
     */
    UP,

    /**
     * The player moves down.
     */
    DOWN,

    /**
     * The player moves left.
     */
    LEFT,

    /**
     * The player moves right.
     */
    RIGHT,

    /**
     * The player stayes still in the axis x.
     */
    STAY_X,

    /**
     * The player stayes still in the axis y.
     */
    STAY_Y;
}
