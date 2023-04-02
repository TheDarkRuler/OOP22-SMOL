package it.unibo.smol.common;

import java.io.UnsupportedEncodingException;

/**
 * Class for storing all the major constant value of the game.
 */
public final class Constant {

    /**-----------------------
     * PLAYER UTILITIES VALUES
     * -----------------------*/
    /**player width. */
    public static final double PLAYER_WIDTH = 70;
    /**player height. */
    public static final double PLAYER_HEIGHT = 70;
    /**player movement speed. */
    public static final double PLAYER_MOVSPD = 1.5;
    /**player damage. */
    public static final int PLAYER_DMG = -1;

    /**----------------------
     * ENEMY UTILITIES VALUES
     * ----------------------*/
    /**enemy width. */
    public static final double ENEMY_WIDTH = 70;
    /**enemy height. */
    public static final double ENEMY_HEIGHT = 70;
    /**enemy health point. */
    public static final int ENEMY_HP = 100;
    /**enemy with helmet health point. */
    public static final int ENEMY_HELMET_HP = 200;
    /**enemy damage to the lifeplants. */
    public static final int ENEMY_DMG = -1;
    /**The time the player stay freeze when the bomb explode. */
    public static final int BOMB_EXPLOSION = 2000;
    /**The amount of point for killing an enemy. */
    public static final int ENEMY_SCORE = 100;
    /**The number of times a bomb enemy comes out of the ground before leaving.*/
    public static final int BOMB_ENEMY_MAX_SPAWNS = 3;
    /**The number of times a helmet enemy comes out of the ground before attacking the lifeplants.*/
    public static final int HELMET_ENEMY_MAX_SPAWNS = 3;
    /**The number of times a angry enemy comes out of the ground before attacking the lifeplants.*/
    public static final int ANGRY_ENEMY_MAX_SPAWNS = 2;
    /**The number of times a basic enemy comes out of the ground before attacking the lifeplants.*/
    public static final int BASIC_ENEMY_MAX_SPAWNS = 3;
    /**Speed of the Bomb enemy. */
    public static final double BOMB_ENEMY_SPEED = 1;
    /**Speed of the Helmet enemy. */
    public static final double HELMET_ENEMY_SPEED = 1;
    /**Speed of the Angry enemy. */
    public static final double ANGRY_ENEMY_SPEED = 1.5;
    /**Speed of the Basic enemy. */
    public static final double BASIC_ENEMY_SPEED = 1;
    /**Min time a enemy stays up. */
    public static final int DEFAULT_MIN_TIME_UP = 1500;
    /**Max time a enemy stays up. */
    public static final int DEFAULT_MAX_TIME_UP = 2500;

    /**---------------------------
     * LIFEPLANTS UTILITIES VALUES 
     * ---------------------------*/
    /**lifeplant width. */
    public static final double HEALTH_WIDTH = 160;
    /**lifeplant height. */
    public static final double HEALTH_HEIGHT = 160;
    /**lifeplant movement speed. */
    public static final double HEALTH_MOVSPD = 0.0;
    /**lifeplant health point. */
    public static final int HEALTH_HP = 1000;
    /**number of plants present at the start of the game. */
    public static final int NUM_PLANTS = 4;

    /**-----------------------
     * WEAPON UTILITIES VALUES 
     * -----------------------*/
    /**weapon radius. */
    public static final double WEAPON_RADIUS = 24;
    /**weapon movement speed. */
    public static final double WEAPON_MOVSPD = 0.0;
    /**weapon damage. */
    public static final int WEAPON_DMG = -100;
    /**the increase rate of the weapon range. */
    public static final double WEAPON_INC_RATE = 2.5;
    /**The basic weapon range. */
    public static final double DEF_WEAPON_RANGE = 160;
    /**the max weapon range. */
    public static final double WEAPON_MAX_RANGE = DEF_WEAPON_RANGE * 4;
    /**the waiting time before the hammer start increasing range. */
    public static final int HOLD_TIME = 200;
    /**the waiting after the hammer get smashed. */
    public static final int WEAPON_ATTACK_ANIM = 350;

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
    /** Default minimum time of spawn.*/
    public static final int DEF_MIN_TIME_SPAWN = 2500;
    /** Whenever the score reaches a multiple of this value the difficulty increase.*/
    public static final int INC_DIFFICULTY_PIVOT = 2000;
    /** The number of difficulty stage.*/
    public static final int DIFFICULTY_LIMIT = 5;

    /**----------------
     * SKIN FOLDER KEYS
     * ----------------*/
    /**key of the pixel skins. */
    public static final String KEY_PIXEL_SKINS = "Pixel_Skins";
    /**key of the vectorial skins. */
    public static final String KEY_VECTORIAL_SKINS = "Vectorial_Skins";
    /**key of the common images folder. */
    public static final String KEY_COMMON_FOLDER = "Common_Skins";

    private Constant() throws UnsupportedEncodingException {
        throw new UnsupportedEncodingException("This is a utility class");
    }
}
