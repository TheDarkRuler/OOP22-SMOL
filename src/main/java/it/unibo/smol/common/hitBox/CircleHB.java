package it.unibo.smol.common.hitbox;

import it.unibo.smol.common.HitBox;
import javafx.geometry.Point2D;

/**
 * HitBox for the player's hammer and for the moles.
 */
public class CircleHB implements HitBox {

    private Point2D center;
    private final double radius;

    /**
     * Constructor of the class.
     * @param center
     * @param radius
     */
    public CircleHB(final Point2D center, final double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Returns the radius of the circle.
     * @return radius
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * Returns the center of the circle.
     * @return center
     */
    @Override
    public Point2D getCenter() {
        return this.center;
    }

    /**
     * Sets the center of the circle with the new parameter.
     * @param newCenter
     */
    @Override
    public void setCenter(final Point2D newCenter) {
        this.center = newCenter;
    }

    /**
     * Checks if the two circle collides.
     * @param circle
     * @return if the circle collides with the parameter
     */
    @Override
    public boolean isColliding(final CircleHB circle) {
        return this.center.distance(circle.getCenter()) <= this.radius + circle.getRadius();
    }

    /**
     * Checks if the circle collides with a rectangle.
     * @param rectangle
     * @return if the circle collides with the parameter
     */
    @Override
    public boolean isColliding(final RectangleHB rectangle) {
        return this.radius >= Math.sqrt(Math.pow(this.distanceX(rectangle), 2) + Math.pow(this.distanceY(rectangle), 2));
    }

    /**
     * Helps with the calculates.
     * @param rectangle
     * @return distance in the x axis
     */
    private double distanceX(final RectangleHB rectangle) {

        if (this.center.getX() < rectangle.getEdge().getX()) {
            return rectangle.getEdge().getX();

        } else if (this.center.getX() > rectangle.getEdge().getX() + rectangle.getWidth()) {
            return rectangle.getEdge().getX() + rectangle.getWidth();

        } else {
            return this.getCenter().getX();
        }
    }

    /**
     * Helps with the calculates.
     * @param rectangle
     * @return distance in the y axis
     */
    private double distanceY(final RectangleHB rectangle) {

        if (this.center.getY() < rectangle.getEdge().getY()) {
            return rectangle.getEdge().getY();
        } else if (this.center.getY() > rectangle.getEdge().getY() + rectangle.getHeight()) {
            return rectangle.getEdge().getY() + rectangle.getHeight();
        } else {
            return this.getCenter().getY();
        }
    }
}
