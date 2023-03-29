package it.unibo.smol.view;

import java.io.UnsupportedEncodingException;
import javafx.stage.Screen;

/**
 * class that implements GameMap.
 */
public final class GameMap {
    private static final int HEIGHT_PROPORTION = 8;
    private static final int WIDTH_PROPORTION = 16;
    private static double screenHeight = Screen.getPrimary().getBounds().getHeight();
    private static double screenWidth = Screen.getPrimary().getBounds().getWidth();

    /**
     * Height of the screen.
     */
    public static final double HEIGHT = 900;

    /**
     * Width of the screen.
     */
    public static final double WIDTH = 1600;

    /**
     * proportion between the width of the screen and the logical width of the view.
     */
    public static final double SCREEN_PROP_X =  screenWidth / WIDTH;

    /**
     * proportion between the height of the screen and the logical height of the view.
     */
    public static final double SCREEN_PROP_Y =  screenHeight / HEIGHT;

    /**
     * Height of the border.
     */
    public static final double BORDER_HEIGHT = HEIGHT / HEIGHT_PROPORTION;

    /**
     * Width of the border.
     */
    public static final double BORDER_WIDTH = WIDTH / WIDTH_PROPORTION;

    /**
     * Height of the map.
     */
    public static final double MAP_HEIGHT = HEIGHT - BORDER_HEIGHT;

    /**
     * Width of the map.
     */
    public static final double MAP_WIDTH = WIDTH - BORDER_WIDTH;

    private GameMap() throws UnsupportedEncodingException {
        throw new UnsupportedEncodingException("This is a utility class");
    }
}
