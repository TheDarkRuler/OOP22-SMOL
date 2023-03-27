package it.unibo.smol.view.api;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public interface HealthBarTank {
    /**
     * @return center of the gameBar
     */
    Point2D getCenter();

    /**
     * @return the width of the health bar
     * TODO add this in relation https://gamedev.stackexchange.com/questions/168035/javafx-how-do-i-create-a-health-bar
     */
    Double getHealthBarWidth();

    /**
     * @return
     */
    Double getHealthBarHeight();

    /**
     * @return current life percentage
     */
    Double updateHealthPercentage();

    /**
     * @return border of the health bar
     */
    Double getHealthBarBorder();

    /**
     * @return
     */
    Color healthBarColor();
    
}
