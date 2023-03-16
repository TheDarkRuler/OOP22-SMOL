package it.unibo.smol.common;

import java.io.UnsupportedEncodingException;

/**
 * Class for storing all the major constant value of the game.
 */
public final class Constant {
    /**player width. */
    public static final int PLAYER_WIDTH = 90;
    /**player width. */
    public static final int PLAYER_HEIGHT = 160;
    /**player width. */
    public static final double PLAYER_MOVSPD = 5.0;
    /**player width. */
    public static final int PLAYER_DMG = -1;

    /**player width. */
    public static final int ENEMY_WIDTH = 90;
    /**player width. */
    public static final int ENEMY_HEIGHT = 160;
    /**player width. */
    public static final double ENEMY_MOVSPD = 5.0;
    /**player width. */
    public static final int ENEMY_HP = 100;
    /**player width. */
    public static final int ENEMY_HELMET_HP = 200;
    /**player width. */
    public static final int ENEMY_DMG = -1;

    /**player width. */
    public static final int HEALTH_WIDTH = 180;
    /**player width. */
    public static final int HEALTH_HEIGHT = 360;
    /**player width. */
    public static final double HEALTH_MOVSPD = 0.0;
    /**player width. */
    public static final int HEALTH_HP = 1000;

    /**player width. */
    public static final int WEAPON_RADIUS = 20;
    /**player width. */
    public static final double WEAPON_MOVSPD = 0.0;
    /**player width. */
    public static final int WEAPON_DMG = -100;

    private Constant() throws UnsupportedEncodingException {
        throw new UnsupportedEncodingException("This is a utility class");
    }
}
