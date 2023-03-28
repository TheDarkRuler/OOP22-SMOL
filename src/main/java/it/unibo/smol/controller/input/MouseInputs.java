package it.unibo.smol.controller.input;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.unibo.smol.common.Constant;
import it.unibo.smol.common.Directions;
import it.unibo.smol.view.GameMap;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

/**
 * Class that handles the MouseInputs events.
 */
public class MouseInputs implements EventHandler<MouseEvent> {

    

    private static boolean playerFreeze;
    private static boolean playerStunned;
    private boolean weaponSmashed;
    private boolean weaponIsSmashing;
    private boolean cursorOnScreen;
    private boolean weaponHits;
    private double weaponIncrease;
    private double weaponRange;
    private final ScheduledExecutorService animationTime;
    private Point2D weaponLocation;

    /**
     * constructor that sets the default values.
     */
    public MouseInputs() {
        this.weaponSmashed = false;
        this.weaponHits = false;
        this.weaponIsSmashing = false;
        this.weaponRange = 0;
        this.weaponIncrease = 0;
        this.cursorOnScreen = false;
        MouseInputs.playerStunned = false;
        this.animationTime = Executors.newSingleThreadScheduledExecutor();
        this.weaponLocation = new Point2D(GameMap.WIDTH / 2, GameMap.HEIGHT / 2);
    }

    /**
     * method that handles the Mouse Event.
     */
    @Override
    public void handle(final MouseEvent event) {
        if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED) 
            && !this.weaponIsSmashing && !this.weaponSmashed
            && !MouseInputs.playerFreeze && !MouseInputs.playerStunned) {
            this.weaponIsSmashing = true;
            this.animationTime.schedule(weaponExpands(), Constant.HOLD_TIME, TimeUnit.MILLISECONDS);

        } else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED) && !this.weaponSmashed
            && !MouseInputs.playerFreeze && !MouseInputs.playerStunned) {

            this.weaponSmashed = true;
            this.weaponHits = true;
            this.weaponIncrease = 0;
            this.weaponLocation = new Point2D(event.getX(), event.getY());
            freezeInputs(Constant.WEAPON_ATTACK_ANIM);

        } else if (event.getEventType().equals(MouseEvent.MOUSE_MOVED) && !weaponSmashed && cursorOnScreen
            && !MouseInputs.playerFreeze && !MouseInputs.playerStunned) {
            this.weaponLocation = new Point2D(event.getX(), event.getY());

        } else if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {

            this.cursorOnScreen = true;

        } else if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED) && !weaponSmashed && cursorOnScreen
            && !MouseInputs.playerFreeze && !MouseInputs.playerStunned) {
            this.weaponLocation = new Point2D(event.getX(), event.getY());
        }
    }

    /**
     * freezes the player's inputs for a given period of time in milliseconds.
     * @param freezeTime
     */
    public void freezeInputs(final int freezeTime) {
        playerBlock();
        animationTime.schedule(releaseInput(), freezeTime, TimeUnit.MILLISECONDS);
    }

    /**
     * freezes the player's inputs for a given period of time in milliseconds.
     * @param freezeTime
     */
    public void freezeInputsFromBomb(final int freezeTime) {
        playerGetStunned();
        animationTime.schedule(releaseStun(), freezeTime, TimeUnit.MILLISECONDS);
    }

    /**
     * sets weaponIncrease with the weapon increase rate so that the weapon range expands.
     * @return a runnable that delays for HOLD_TIME millisec
     */
    private Runnable weaponExpands() {
        return new Runnable() {

            @Override
            public void run() {
                if (!weaponSmashed) {
                    weaponIncrease = Constant.WEAPON_INC_RATE;
                }
            }
        };
    }

    /**
     * Starts the animation of the weapon.
     * @return a runnable that delays for WEAPON_ATTACK_ANIM millisec
     */
    private Runnable releaseInput() {
        return new Runnable() {

            @Override
            public void run() {
                weaponRange = 0;
                playerReleased();
            } 
        };
    }

    /**
     * Realease the player from the stun status.
     * @return a runnable
     */
    private Runnable releaseStun() {
        return new Runnable() {

            @Override
            public void run() {
                weaponRange = 0;
                playerReleasedFromStun();
            }
            
        };
    }

    /**
     * player get stunned.
     */
    private void playerGetStunned() {
        KeyInputs.setMovement(Directions.STAY_X);
        KeyInputs.setMovement(Directions.STAY_Y);
        MouseInputs.playerStunned = true;
    }

    /**
     * starts the animation blocking player inputs.
     */
    private void playerBlock() {
        KeyInputs.setMovement(Directions.STAY_X);
        KeyInputs.setMovement(Directions.STAY_Y);
        MouseInputs.playerFreeze = true;
    }

    /**
     * player release from stun.
     */
    private void playerReleasedFromStun() {
        KeyInputs.setMovement(Directions.STAY_X);
        KeyInputs.setMovement(Directions.STAY_Y);
        MouseInputs.playerStunned = false;
        this.weaponSmashed = false;
        this.weaponIsSmashing = false;
    }

    /**
     * finishing the animation giving back the inputs to the player.
     */
    private void playerReleased() {
        KeyInputs.setMovement(Directions.STAY_X);
        KeyInputs.setMovement(Directions.STAY_Y);
        MouseInputs.playerFreeze = false;
        this.weaponSmashed = false;
        this.weaponIsSmashing = false;
    }

    /**
     * checks if the weapon is max range and if not increase the range og hit.
     */
    public void setWeaponRange() {
        if (Constant.DEF_WEAPON_RANGE + weaponRange <= Constant.WEAPON_MAX_RANGE) {
            weaponRange += weaponIncrease;
        }
    }

    /**
     * returns the weaponRange to draw it.
     * @return weaponRange
     */
    public double getWeaponRange() {
        return this.weaponRange + Constant.DEF_WEAPON_RANGE;
    }

    /**
     * returns the weapon location on the screen.
     * @return weaponLocation
     */
    public Point2D getWeaponLocation() {
        return this.weaponLocation;
    }

    /**
     * returns true if the cursor is on screen.
     * @return if the cursor is on screen
     */
    public boolean isCursorOnScreen() {
        return this.cursorOnScreen;
    }

    /**
     * returns if the weapon has smashed.
     * @return weaponSmashed
     */
    public boolean isWeaponSmashed() {
        if (weaponHits) {
            this.weaponHits = false;
            return true;
        }
        return false;
    }

    /**
     * returns if the player status is going to let the two input files communicate.
     * @return if the player is freezed
     */
    protected static boolean isPlayerFreezed() {
        return MouseInputs.playerFreeze;
    }

    /**
     * returns if the player status is going to let the two input files communicate.
     * @return if the player is stunned
     */
    protected static boolean isPlayerStunned() {
        return MouseInputs.playerStunned;
    }

}
