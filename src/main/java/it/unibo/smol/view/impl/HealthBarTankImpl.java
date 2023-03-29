package it.unibo.smol.view.impl;

import it.unibo.smol.common.Constant;
import it.unibo.smol.controller.api.GameState;
import it.unibo.smol.view.GameMap;
import it.unibo.smol.view.api.HealthBarTank;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * class that implements HealthBarTank.
 */
public class HealthBarTankImpl implements HealthBarTank {
    private Double centerWidth;
    private Double currentLife;
    private GameState gameState;

    //proportion variable
    private static final int CENTER_WIDTH_PROPORTION = 2;
    private static final int CENTER_HEIGHT_PROPORTION = 4;
    private static final int WIDTH_PROPORTION = 3;
    private static final int HEIGHT_PROPORTION = 10;
    private static final int BORDER_PROPORTION = 35;
    /* public static final double CENTER_WIDTH_PROPORTION = Double.valueOf(GameMap.BORDER_WIDTH / 2);
    public static final double CENTER_HEIGHT = Double.valueOf(GameMap.BORDER_WIDTH / 4);

    public static final double WIDTH = Double.valueOf(GameMap.BORDER_WIDTH * 2);

    public static final double HEIGHT = Double.valueOf(GameMap.BORDER_WIDTH / 10); */

    public HealthBarTankImpl(GameState gameState) {
        this.centerWidth = Double.valueOf(GameMap.BORDER_WIDTH / CENTER_WIDTH_PROPORTION)*GameMap.SCREEN_PROP_X;
        this.currentLife = Double.valueOf(0);
        this.gameState = gameState;
    }

    @Override
    public Point2D getCenter() {
        return new Point2D(this.centerWidth , (Double.valueOf(GameMap.BORDER_WIDTH / CENTER_HEIGHT_PROPORTION))*GameMap.SCREEN_PROP_Y);
    }

    @Override
    public Double getHealthBarWidth() {
        return Double.valueOf(GameMap.BORDER_WIDTH * WIDTH_PROPORTION) * GameMap.SCREEN_PROP_X;
    }

    @Override
    public Double getHealthBarHeight() {
       return Double.valueOf(GameMap.BORDER_HEIGHT / HEIGHT_PROPORTION) * GameMap.SCREEN_PROP_Y;
    }

    @Override
    public Double updateHealthPercentage() {
        this.currentLife = Double.valueOf(0);
        gameState.getWorld().getLifePlants().forEach(x -> {
            if (x.getHealthComp().isPresent()) {
                currentLife = currentLife + x.getHealthComp().get().getCurrentHealth();
            }
        });
        return Double.valueOf(currentLife / (Constant.NUM_PLANTS * Constant.HEALTH_HP));
    }

    @Override
    public Double getHealthBarBorder() {
        return Double.valueOf(GameMap.BORDER_WIDTH / BORDER_PROPORTION)*GameMap.SCREEN_PROP_X;
    }

    @Override
    public Color healthBarColor() {
        return Color.rgb(0, 153, 51);
    }

}
