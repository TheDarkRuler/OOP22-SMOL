package it.unibo.smol.common;
//preso spunto da game-as-a-lab

/**
 * Class that saves the velocity of the Objects
 */
public class Vector2D {
    
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
    public Vector2D(double x, double y) {
        this.x=x;
        this.y=y;
    }

    /**
     * sum up the velocity
     * @param v
     * @return
     */
    public Vector2D sum(Vector2D v) {
        return new Vector2D(x+v.x, y+v.x);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }


}
