package it.unibo.smol.common;

import java.io.UnsupportedEncodingException;

import javafx.stage.Screen;

/**
 * Class for storing all the major constant value of the game.
 */
public final class Constant {

    private static double screenHeight = Screen.getPrimary().getBounds().getHeight();
    private static double screenWidth = Screen.getPrimary().getBounds().getWidth();
    
    /**player width. */
    public static final double PLAYER_WIDTH = screenWidth * 0.100;
    /**player height. */
    public static final double PLAYER_HEIGHT = screenHeight * 0.100;
    /**player movement speed. */
    public static final double PLAYER_MOVSPD = screenWidth * 0.0005;
    /**player damage. */
    public static final int PLAYER_DMG = -1;

    /**enemy width. */
    public static final double ENEMY_WIDTH = screenWidth * 0.100;
    /**enemy height. */
    public static final double ENEMY_HEIGHT = screenHeight * 0.100;
    /**enemy movement speed. */
    public static final double ENEMY_MOVSPD = screenWidth * 0.010;
    /**enemy health point. */
    public static final int ENEMY_HP = 100;
    /**enemy with helmet health point. */
    public static final int ENEMY_HELMET_HP = 200;
    /**enemy damage. */
    public static final int ENEMY_DMG = -1;
    /**The time the player stay freeze when the bomb explode. */
    public static final int BOMB_EXPLOSION = 1000;

    /**lifeplant width. */
    public static final double HEALTH_WIDTH = screenWidth * 0.025;
    /**lifeplant height. */
    public static final double HEALTH_HEIGHT = screenWidth * 0.025;
    /**lifeplant movement speed. */
    public static final double HEALTH_MOVSPD = 0.0;
    /**lifeplant health point. */
    public static final int HEALTH_HP = 1000;

    /**weapon radius. */
    public static final double WEAPON_RADIUS = screenWidth * 0.025;
    /**weapon movement speed. */
    public static final double WEAPON_MOVSPD = 0.0;
    /**weapon damage. */
    public static final int WEAPON_DMG = -100;

    private Constant() throws UnsupportedEncodingException {
        throw new UnsupportedEncodingException("This is a utility class");
    }
}
