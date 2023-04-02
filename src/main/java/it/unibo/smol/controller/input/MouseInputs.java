package it.unibo.smol.controller.input;

import java.util.Optional;
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

    private final ScheduledExecutorService animationTime;
    private final KeyInputs keyInputs;
    private boolean playerFreeze;
    private boolean playerStunned;
    private boolean weaponSmashed;
    private boolean weaponIsSmashing;
    private boolean cursorOnScreen;
    private boolean weaponHits;
    private double weaponIncrease;
    private double weaponRange;
    private Point2D weaponLocation;

    /**
     * constructor that sets the default values.
     * @param keyInputs
     */
    public MouseInputs(final Optional<KeyInputs> keyInputs) {
        this.weaponSmashed = false;
        this.weaponHits = false;
        this.weaponIsSmashing = false;
        this.cursorOnScreen = false;
        this.playerStunned = false;
        this.playerFreeze = false;
        this.weaponRange = 0;
        this.weaponIncrease = 0;
        this.animationTime = Executors.newSingleThreadScheduledExecutor();
        this.weaponLocation = new Point2D(GameMap.WIDTH / 2, GameMap.HEIGHT / 2);
        this.keyInputs = keyInputs.orElseThrow();
        this.keyInputs.setPlayerFreezed(playerFreeze);
        this.keyInputs.setPlayerStunned(playerStunned);
    }

    /**
     * method that handles the Mouse Event.
     */
    @Override
    public void handle(final MouseEvent event) {
        if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED) 
            && !this.weaponIsSmashing && !this.weaponSmashed
            && !this.playerFreeze && !this.playerStunned) {

            this.weaponIsSmashing = true;
            this.animationTime.schedule(weaponExpands(), Constant.HOLD_TIME, TimeUnit.MILLISECONDS);

        } else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED) 
            && !this.weaponSmashed && this.weaponIsSmashing
            && !this.playerFreeze && !this.playerStunned) {

            this.weaponSmashed = true;
            this.weaponHits = true;
            this.weaponIncrease = 0;
            this.weaponLocation = new Point2D(event.getX(), event.getY());
            freezeInputs(Constant.WEAPON_ATTACK_ANIM);

        } else if (event.getEventType().equals(MouseEvent.MOUSE_MOVED) 
            && !this.weaponSmashed && this.cursorOnScreen
            && !this.playerFreeze && !this.playerStunned) {
            this.weaponLocation = new Point2D(event.getX(), event.getY());

        } else if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {

            this.cursorOnScreen = true;

        } else if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED) 
            && !this.weaponSmashed && this.cursorOnScreen
            && !this.playerFreeze && !this.playerStunned) {
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
        keyInputs.setMovement(Directions.STAY_X);
        keyInputs.setMovement(Directions.STAY_Y);
        this.playerStunned = true;
        this.keyInputs.setPlayerStunned(playerStunned);
    }

    /**
     * starts the animation blocking player inputs.
     */
    private void playerBlock() {
        keyInputs.setMovement(Directions.STAY_X);
        keyInputs.setMovement(Directions.STAY_Y);
        this.playerFreeze = true;
        this.keyInputs.setPlayerFreezed(playerFreeze);
    }

    /**
     * player release from stun.
     */
    private void playerReleasedFromStun() {
        keyInputs.setMovement(Directions.STAY_X);
        keyInputs.setMovement(Directions.STAY_Y);
        this.playerStunned = false;
        this.keyInputs.setPlayerStunned(playerStunned);
        this.weaponSmashed = false;
        this.weaponIsSmashing = false;
    }

    /**
     * finishing the animation giving back the inputs to the player.
     */
    private void playerReleased() {
        keyInputs.setMovement(Directions.STAY_X);
        keyInputs.setMovement(Directions.STAY_Y);
        this.playerFreeze = false;
        this.keyInputs.setPlayerFreezed(playerFreeze);
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
        return new Point2D(weaponLocation.getX() / GameMap.SCREEN_PROP_X, weaponLocation.getY() / GameMap.SCREEN_PROP_Y);
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
}
