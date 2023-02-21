package it.unibo.smol.common;
//preso spunto da game-as-a-lab

/**
 * Class that saves the position of the Objects
 */
public class Point2D {
    /**
     * inizialize the coordinates
     */
    private double x;
    private double y;

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Point2D(double x, double y) {
        this.x=x;
        this.y=y;
    }

    /**
     * Updates the position in order to move
     * @param v
     * @return
     */
    public Point2D sum(Vector2D v) {
        return new Point2D(v.getX()+x, v.getY()+y);
    }

    /**
     * get the current position
     * @return
     */
    public Point2D getPosition() {
        return this;
    }
}
