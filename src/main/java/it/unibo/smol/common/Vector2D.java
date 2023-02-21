package it.unibo.smol.common;
//preso spunto da game-as-a-lab.

/**
 * Class that saves the velocity of the Objects.
 */
public class Vector2D {

    /**
     * inizialize the coordinates.
     */
    private final double x;
    private final double y;

    /**
     * Constructor.
     * @param x
     * @param y
     */
    public Vector2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * sum up the velocity.
     * @param v
     * @return the new velocity
     */
    public Vector2D sum(final Vector2D v) {
        return new Vector2D(x + v.x, y + v.x);
    }

    /**
     * gets x.
     * @return x
     */
    public double getX() {
        return this.x;
    }

    /**
     * gets y.
     * @return y
     */
    public double getY() {
        return this.y;
    }


}
