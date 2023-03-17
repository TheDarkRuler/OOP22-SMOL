package it.unibo.smol.common;
/**
 * interface made for the HitBoxes.
 */

import it.unibo.smol.common.hitbox.CircleHB;
import it.unibo.smol.common.hitbox.RectangleHB;
import javafx.geometry.Point2D;

/**
 * interface for the hitboxes shapes.
 */
public interface HitBox {

    /**
     * returns the center of the shape.
     * @return center
     */
    Point2D getCenter();

    /**
     * sets the center of the shape with the new parameter.
     * @param newCenter
     */
    void setCenter(Point2D newCenter);

    /**
     * generic isColliding to make the others work.
     * @param hitBox
     * @return if the shapes are colliding
     */
    boolean isColliding(HitBox hitBox);

    /**
     * checks if the shapes are colliding.
     * @param circle
     * @return if the two shapes are colliding
     */
    boolean isColliding(CircleHB circle);

    /**
     * checks if the two shapes are colliding.
     * @param rectangle
     * @return if the two shapes are colliding
     */
    boolean isColliding(RectangleHB rectangle);

    /**
     * clone object.
     * @return a copy
     */
    HitBox copyOf();

}
