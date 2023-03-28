package it.unibo.smol.common;

import java.io.UnsupportedEncodingException;

import javafx.stage.Screen;

/**
 * Class for storing all the major constant value of the game.
 */
public final class Constant {

    private static double screenHeight = Screen.getPrimary().getBounds().getHeight();
    private static double screenWidth = Screen.getPrimary().getBounds().getWidth();

    /**-----------------------
     * PLAYER UTILITIES VALUES
     * -----------------------*/
    /**player width. */
    public static final double PLAYER_WIDTH = screenWidth * 0.060;
    /**player height. */
    public static final double PLAYER_HEIGHT = screenHeight * 0.060;
    /**player movement speed. */
    public static final double PLAYER_MOVSPD = screenWidth * 0.001;
    /**player damage. */
    public static final int PLAYER_DMG = -1;

    /**----------------------
     * ENEMY UTILITIES VALUES
     * ----------------------*/
    /**enemy width. */
    public static final double ENEMY_WIDTH = screenWidth * 0.060;
    /**enemy height. */
    public static final double ENEMY_HEIGHT = screenHeight * 0.060;
    /**enemy health point. */
    public static final int ENEMY_HP = 100;
    /**enemy with helmet health point. */
    public static final int ENEMY_HELMET_HP = 200;
    /**enemy damage. */
    public static final int ENEMY_DMG = -1;
    /**The time the player stay freeze when the bomb explode. */
    public static final int BOMB_EXPLOSION = 2000;
    /**The amount of point for killing an enemy */
    public static final int ENEMY_SCORE = 100;
    /**Max time a Bomb mole can spawn */
    public static final int BOMB_ENEMY_MAX_SPAWNS = 3;
    /**Max time a Helmet mole can spawn */
    public static final int HELMET_ENEMY_MAX_SPAWNS = 3;
    /**Max time a Angry mole can spawn */
    public static final int ANGRY_ENEMY_MAX_SPAWNS = 2;
    /**Max time a Basic mole can spawn */
    public static final int BASIC_ENEMY_MAX_SPAWNS = 3;
    /**Speed of the Bomb mole */
    public static final double BOMB_ENEMY_SPEED = screenWidth * 0.0005;
    /**Speed of the Helmet mole */
    public static final double HELMET_ENEMY_SPEED = screenWidth * 0.0005;
    /**Speed of the Angry mole */
    public static final double ANGRY_ENEMY_SPEED = screenWidth * 0.001;
    /**Speed of the Basic mole */
    public static final double BASIC_ENEMY_SPEED = screenWidth * 0.0005;
    /**Min time a enemy stays up */
    public static final int DEFAULT_MIN_TIME_UP = 1500;
    /**Max time a enemy stays up */
    public static final int DEFAULT_MAX_TIME_UP = 2500;

    /**---------------------------
     * LIFEPLANTS UTILITIES VALUES 
     * ---------------------------*/
    /**lifeplant width. */
    public static final double HEALTH_WIDTH = screenWidth * 0.100;
    /**lifeplant height. */
    public static final double HEALTH_HEIGHT = screenWidth * 0.100;
    /**lifeplant movement speed. */
    public static final double HEALTH_MOVSPD = 0.0;
    /**lifeplant health point. */
    public static final int HEALTH_HP = 1000;
    /**number of plants present at spawn. */
    public static final int NUM_PLANTS = 4;

    /**-----------------------
     * WEAPON UTILITIES VALUES 
     * -----------------------*/
    /**weapon radius. */
    public static final double WEAPON_RADIUS = screenWidth * 0.015;
    /**weapon movement speed. */
    public static final double WEAPON_MOVSPD = 0.0;
    /**weapon damage. */
    public static final int WEAPON_DMG = -100;
    /**the increase rate of the weapon range. */
    public static final double WEAPON_INC_RATE = screenWidth * 0.0015;
    /**The basic weapon range. */
    public static final double DEF_WEAPON_RANGE = screenWidth * 0.100;
    /**the max weapon range. */
    public static final double WEAPON_MAX_RANGE = DEF_WEAPON_RANGE*4;
    /**the waiting time before the hammer start increasing range. */
    public static final int HOLD_TIME = 300;
    /**the waiting after the hammer get smashed. */
    public static final int WEAPON_ATTACK_ANIM = 250;

    /**----------------------
     * ENEMY SPAWN RATE VALUE 
     * ----------------------*/
    /** Default spawn rate for basic enemy. */
    public static final double DEF_RATE_BASIC = 1.0;
    /** Default spawn rate for helmet enemy.*/
    public static final double DEF_RATE_HELMET = 0.45;
    /** Default spawn rate for angry enemy.*/
    public static final double DEF_RATE_ANGRY = 0.325;
    /** Default spawn rate for bomb enemy.*/
    public static final double DEF_RATE_BOMB = 0.2;
    /** Increase spawn rate for angry enemy.*/
    public static final double INC_RATE_ANGRY = 0.025;
    /** Increase spawn rate for helmet enemy.*/
    public static final double INC_RATE_HELMET = 0.05;
    /** Decrease time spawn.*/
    public static final int DEC_TIME_SPAWN = 200; 
    /** Default max time of spawn.*/
    public static final int DEF_MAX_TIME_SPAWN = 3500;
    /** Default minimum time of spawn*/
    public static final int DEF_MIN_TIME_SPAWN = 2500;
    /** Whenever the score reaches a multiple of this value the difficulty increase.*/
    public static final int INC_DIFFICULTY_PIVOT = 2000;
    /** The number of difficulty stage.*/
    public static final int DIFFICULTY_LIMIT = 5;

    private Constant() throws UnsupportedEncodingException {
        throw new UnsupportedEncodingException("This is a utility class");
    }
}
