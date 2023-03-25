package it.unibo.smol.controller.input;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import it.unibo.smol.common.Directions;
import it.unibo.smol.common.GameMap;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

/**
 * Class that handles the MouseInputs events.
 */
public class MouseInputs implements EventHandler<MouseEvent> {

    private static final int WEAPON_INC_RATE = 2;
    private static final int DEF_WEAPON_RANGE = 100;
    private static final int WEAPON_MAX_RANGE = DEF_WEAPON_RANGE + 100;
    private static final int HOLD_TIME = 300;
    private static final int WEAPON_ATTACK_ANIM = 500;

    private static boolean playerFreeze;
    private boolean weaponSmashed;
    private boolean weaponIsSmashing;
    private boolean cursorOnScreen;
    private int weaponIncrease;
    private int weaponRange;
    private final ScheduledExecutorService animationTime;
    private Point2D weaponLocation;

    /**
     * constructor that sets the default values.
     */
    public MouseInputs() {
        this.weaponSmashed = false;
        this.weaponIsSmashing = false;
        this.weaponRange = 0;
        this.weaponIncrease = 0;
        this.cursorOnScreen = false;
        this.animationTime = Executors.newSingleThreadScheduledExecutor();
        this.weaponLocation = new Point2D(GameMap.WIDTH / 2, GameMap.HEIGHT / 2);
    }

    /**
     * method that handles the Mouse Event.
     */
    @Override
    public void handle(final MouseEvent event) {
        if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED) && !this.weaponIsSmashing) {
            this.weaponIsSmashing = true;
            this.animationTime.schedule(weaponExpands(), HOLD_TIME, TimeUnit.MILLISECONDS);

        } else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED) && !this.weaponSmashed) {

            this.weaponSmashed = true;
            this.weaponIncrease = 0;
            this.weaponLocation = new Point2D(event.getX(), event.getY());
            freezeInputs(WEAPON_ATTACK_ANIM);

        } else if (event.getEventType().equals(MouseEvent.MOUSE_MOVED) && !weaponSmashed && cursorOnScreen) {
            if (!weaponSmashed && cursorOnScreen){
                this.weaponLocation = new Point2D(event.getX(), event.getY());
            }

        } else if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {

            this.cursorOnScreen = true;

        } else if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED) && !weaponSmashed && cursorOnScreen) {
            if (!weaponSmashed && cursorOnScreen){
                this.weaponLocation = new Point2D(event.getX(), event.getY());
            }
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
     * sets weaponIncrease with the weapon increase rate so that the weapon range expands.
     * @return a runnable that delays for HOLD_TIME millisec
     */
    private Runnable weaponExpands() {
        return new Runnable() {

            @Override
            public void run() {
                if (!weaponSmashed) {
                    weaponIncrease = WEAPON_INC_RATE;
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
     * starts the animation blocking player inputs.
     */
    private void playerBlock() {
        KeyInputs.setMovement(Directions.STAY_X);
        KeyInputs.setMovement(Directions.STAY_Y);
        MouseInputs.playerFreeze = true;
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
        if (DEF_WEAPON_RANGE + weaponRange <= WEAPON_MAX_RANGE) {
            weaponRange += weaponIncrease;
        }
    }

    /**
     * returns the weaponRange to draw it.
     * @return weaponRange
     */
    public int getWeaponRange() {
        return this.weaponRange + DEF_WEAPON_RANGE;
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
        return this.weaponSmashed;
    }

    /**
     * returns if the player status is going to let the two input files communicate.
     * @return if the player is freezed
     */
    protected static boolean isPlayerFreezed() {
        return MouseInputs.playerFreeze;
    }

}
