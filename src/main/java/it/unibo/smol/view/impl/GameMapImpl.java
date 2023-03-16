package it.unibo.smol.view.impl;

import it.unibo.smol.view.api.GameMap;
import javafx.stage.Screen;

/**
 * class that implements GameMap.
 */
public class GameMapImpl implements GameMap {
    private static final int HEIGHT_PROPORTION = 4;
    private static final int WIDTH_PROPORTION = 8;
    private final double screeHeight;
    private final double screeWidth;
    /**
     * constructor that defines map borders based on screen size.
     */
    public GameMapImpl() {
        screeHeight = Screen.getPrimary().getBounds().getHeight();
        screeWidth = Screen.getPrimary().getBounds().getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return Double.valueOf(screeHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return Double.valueOf(screeWidth);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getBorderHeight() {
        return screeHeight / HEIGHT_PROPORTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getBorderWidth() {
        return screeHeight / WIDTH_PROPORTION;
    }

    @Override
    public double getMapHeight() {
        return screeHeight - (screeHeight / HEIGHT_PROPORTION);
    }

    @Override
    public double getMapWidth() {
        return screeHeight - (screeHeight / WIDTH_PROPORTION);
    }

}
