package it.unibo.smol.view.api;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * creates the health bar.
 */
public interface HealthBarTank {
    /**
     * gets the center of the health bar.
     * @return center of the gameBar
     */
    Point2D getCenter();

    /**
     * gets the health bar width.
     * @return the width of the health bar
     */
    Double getHealthBarWidth();

    /**
     * gets the health bar height.
     * @return the health bar height
     */
    Double getHealthBarHeight();

    /**
     * updates the width of the health bar.
     * @return current life percentage
     */
    Double updateHealthPercentage();

    /**
     * gets the border of the health bar.
     * @return border of the health bar
     */
    Double getHealthBarBorder();

    /**
     * manages the health bar color.
     * @return the health bar Color
     */
    Color healthBarColor();

}
