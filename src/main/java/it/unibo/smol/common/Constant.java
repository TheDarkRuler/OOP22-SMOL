package it.unibo.smol.common;

import java.io.UnsupportedEncodingException;

/**
 * Class for storing all the major constant value of the game.
 */
public final class Constant {
    /**player width. */
    public static final int PLAYER_WIDTH = 90;
    /**player height. */
    public static final int PLAYER_HEIGHT = 160;
    /**player movement speed. */
    public static final double PLAYER_MOVSPD = 5.0;
    /**player damage. */
    public static final int PLAYER_DMG = -1;

    /**enemy width. */
    public static final int ENEMY_WIDTH = 90;
    /**enemy height. */
    public static final int ENEMY_HEIGHT = 160;
    /**enemy movement speed. */
    public static final double ENEMY_MOVSPD = 5.0;
    /**enemy health point. */
    public static final int ENEMY_HP = 100;
    /**enemy with helmet health point. */
    public static final int ENEMY_HELMET_HP = 200;
    /**enemy damage. */
    public static final int ENEMY_DMG = -1;
    /**The time the player stay freeze when the bomb explode. */
    public static final int BOMB_EXPLOSION = 1000;

    /**lifeplant width. */
    public static final int HEALTH_WIDTH = 180;
    /**lifeplant height. */
    public static final int HEALTH_HEIGHT = 360;
    /**lifeplant movement speed. */
    public static final double HEALTH_MOVSPD = 0.0;
    /**lifeplant health point. */
    public static final int HEALTH_HP = 1000;

    /**weapon radius. */
    public static final int WEAPON_RADIUS = 20;
    /**weapon movement speed. */
    public static final double WEAPON_MOVSPD = 0.0;
    /**weapon damage. */
    public static final int WEAPON_DMG = -100;

    private Constant() throws UnsupportedEncodingException {
        throw new UnsupportedEncodingException("This is a utility class");
    }
}
