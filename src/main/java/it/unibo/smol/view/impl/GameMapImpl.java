package it.unibo.smol.view.impl;

import it.unibo.smol.view.api.GameMap;
import javafx.stage.Screen;

/**
 * class that implements GameMap 
 */
public class GameMapImpl implements GameMap {
    private static final int HEIGHT_PROPORTION = 4;
    private static final int WIDTH_PROPORTION = 8;
    
    private double screeHeight;
    private double screeWidth;
    
    public GameMapImpl() {
        screeHeight = Screen.getPrimary().getBounds().getHeight();
        screeWidth = Screen.getPrimary().getBounds().getWidth();
    }

    @Override
    public double getHeight() {
        return Double.valueOf(screeHeight);
    }

    @Override
    public double getWidth() {
        return Double.valueOf(screeWidth);
    }

    @Override
    public double getBorderHeight() {
        return screeHeight - (screeHeight / HEIGHT_PROPORTION);
    }

    @Override
    public double getBorderWidth() {
        return screeHeight - (screeHeight / WIDTH_PROPORTION);
    }

    
}
