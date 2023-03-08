package it.unibo.smol.common.hitbox;

import it.unibo.smol.common.HitBox;
import javafx.geometry.Point2D;

/**
 * HitBox for the player and for the camps.
 */
public class RectangleHB implements HitBox {

    private final double width;
    private final double height;
    private Point2D center;


    /**
     * Constructor for the RectangleHB.
     * @param width
     * @param height
     * @param center
     */
    public RectangleHB(final double width, final double height, final Point2D center) {
        this.width = width;
        this.height = height;
        this.center = center;
    }

    /**
     * Returns the Width.
     * @return width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height.
     * @return height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets the edge of the rectangle.
     * @return the edge
     */
    public Point2D getEdge() {
        return new Point2D(this.center.getX() - (this.width / 2), this.center.getY() - (this.height / 2));
    }

    /**
     * Returns the center of the rectangle.
     * @return center
     */
    @Override
    public Point2D getCenter() {
        return this.center;
    }

    /**
     * Sets the center of the rectangle with the new parameter.
     */
    @Override
    public void setCenter(final Point2D newCenter) {
        this.center = newCenter;
    }

    /**
     * Checks if the circle is colliding with the rectangle.
     * @param circle
     * @return if the circle and the rectangle is colliding
     */
    @Override
    public boolean isColliding(final CircleHB circle) {
        return circle.isColliding(this);
    }

    /**
     * Colliding check with two rectangles.
     * @param rectangle
     * @return if the two rectangles are colliding
     */
    @Override
    public boolean isColliding(final RectangleHB rectangle) {
        return Math.abs(this.center.getX() - rectangle.getCenter().getX()) <= (this.width + rectangle.getWidth()) / 2 
            && Math.abs(this.center.getY() - rectangle.getCenter().getY()) <= (this.height + rectangle.getHeight()) / 2;
    }

}
