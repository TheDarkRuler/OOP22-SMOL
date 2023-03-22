package it.unibo.smol.view;

import java.io.UnsupportedEncodingException;
import javafx.stage.Screen;

/**
 * class that implements GameMap.
 */
public final class GameMap {
    private static final int HEIGHT_PROPORTION = 4;
    private static final int WIDTH_PROPORTION = 8;
    private static double screeHeight = Screen.getPrimary().getBounds().getHeight();
    private static double screeWidth = Screen.getPrimary().getBounds().getWidth();

    /**
     * Height of the screen.
     */
    public static final double HEIGHT = Double.valueOf(screeHeight);

    /**
     * Width of the screen.
     */
    public static final double WIDTH = Double.valueOf(screeWidth);

    /**
     * Height of the border.
     */
    public static final double BORDER_HEIGHT = screeHeight / HEIGHT_PROPORTION;

    /**
     * Width of the border.
     */
    public static final double BORDER_WIDTH = screeWidth / WIDTH_PROPORTION;

    /**
     * Height of the map.
     */
    public static final double MAP_HEIGHT = screeHeight - BORDER_HEIGHT;

    /**
     * Width of the map.
     */
    public static final double MAP_WIDTH = screeWidth - BORDER_WIDTH;

    private GameMap() throws UnsupportedEncodingException {
        throw new UnsupportedEncodingException("This is a utility class");
    }
}
