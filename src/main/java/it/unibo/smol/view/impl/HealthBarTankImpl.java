package it.unibo.smol.view.impl;

import it.unibo.smol.common.Constant;
import it.unibo.smol.controller.api.GameState;
import it.unibo.smol.controller.impl.GameStateImpl;
import it.unibo.smol.view.GameMap;
import it.unibo.smol.view.api.HealthBarTank;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

/**
 * class that implements HealthBarTank.
 */
public class HealthBarTankImpl implements HealthBarTank {

    //proportion variable
    private static final int CENTER_WIDTH_PROPORTION = 2;
    private static final int CENTER_HEIGHT_PROPORTION = 4;
    private static final int WIDTH_PROPORTION = 3;
    private static final int HEIGHT_PROPORTION = 10;
    private static final int BORDER_PROPORTION = 35;
    private static final int GREEN_VALUE_RGB = 153;
    private static final int BLUE_VALUE_RGB = 51;
    private static final int RED_VALUE_RGB = 0;

    private final Double centerWidth;
    private Double currentLife;
    private final GameState gameState;

    /**
     * constructor that gets the gamestate and sets the centerWidth and the currentLife.
     * @param gameState
     */
    public HealthBarTankImpl(final GameState gameState) {
        this.centerWidth = Double.valueOf(GameMap.BORDER_WIDTH / CENTER_WIDTH_PROPORTION) * GameMap.SCREEN_PROP_X;
        this.currentLife = Double.valueOf(0);
        this.gameState = new GameStateImpl(gameState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point2D getCenter() {
        return new Point2D(this.centerWidth,
            (Double.valueOf(GameMap.BORDER_WIDTH / CENTER_HEIGHT_PROPORTION)) * GameMap.SCREEN_PROP_Y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getHealthBarWidth() {
        return Double.valueOf(GameMap.BORDER_WIDTH * WIDTH_PROPORTION) * GameMap.SCREEN_PROP_X;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getHealthBarHeight() {
       return Double.valueOf(GameMap.BORDER_HEIGHT / HEIGHT_PROPORTION) * GameMap.SCREEN_PROP_Y;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getHealthBarBorder() {
        return Double.valueOf(GameMap.BORDER_WIDTH / BORDER_PROPORTION) * GameMap.SCREEN_PROP_X;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color healthBarColor() {
        return Color.rgb(RED_VALUE_RGB, GREEN_VALUE_RGB, BLUE_VALUE_RGB);
    }

}
