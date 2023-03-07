package it.unibo.smol.input;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.unibo.smol.common.Directions;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

/**
 * Class that handles the MouseInputs events.
 */
public class MouseInputs implements EventHandler<MouseEvent> {

    private static final int HAMMER_INC_RATE = 2;
    private static final int HAMMER_MAX_RANGE = 100;
    private static final int HOLD_TIME = 300;
    private static final int HAMMER_SMASH_ANIM = 500;

    private static boolean animationGoing;
    private boolean hammerSmashed;
    private boolean hammerIsSmashing;
    private boolean cursorOnScreen;
    private int hammerIncrease;
    private int hammerRange;
    private final ScheduledExecutorService animationTime;
    private Point2D hammerLocation;

    /**
     * constructor that sets the default values.
     */
    public MouseInputs() {
        this.hammerSmashed = false;
        this.hammerIsSmashing = false;
        this.hammerRange = 0;
        this.hammerIncrease = 0;
        this.cursorOnScreen = false;
        this.animationTime = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * method that handles the Mouse Event.
     */
    @Override
    public void handle(final MouseEvent event) {
        if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED) && !this.hammerIsSmashing) {
            this.hammerIsSmashing = true;
            this.animationTime.schedule(hammerExpands(), HOLD_TIME, TimeUnit.MILLISECONDS);

        } else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED) && !this.hammerSmashed) {

            this.hammerSmashed = true;
            this.hammerIncrease = 0;
            this.hammerLocation = new Point2D(event.getX(), event.getY());
            animationGoing();
            animationTime.schedule(animation(), HAMMER_SMASH_ANIM, TimeUnit.MILLISECONDS);

        } else if (event.getEventType().equals(MouseEvent.MOUSE_MOVED) && !hammerSmashed && cursorOnScreen) {

            this.hammerLocation = new Point2D(event.getX(), event.getY());

        } else if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {

            this.cursorOnScreen = true;

        } else if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED) && !hammerSmashed && cursorOnScreen) {

            this.hammerLocation = new Point2D(event.getX(), event.getY());

        }
    }

    /**
     * sets hammerIncrease with the Hammer increase rate so that the hammer range expands.
     * @return a runnable that delays for HOLD_TIME millisec
     */
    private Runnable hammerExpands() {
        return new Runnable() {

            @Override
            public void run() {
                if (!hammerSmashed) {
                    hammerIncrease = HAMMER_INC_RATE;
                }
            }
        };
    }

    /**
     * Starts the animation of the hammer.
     * @return a runnable that delays for HAMMER_SMASH_ANIM millisec
     */
    private Runnable animation() {
        return new Runnable() {

            @Override
            public void run() {
                hammerRange = 0;
                animationDone();
            } 
        };
    }

    /**
     * starts the animation blocking player inputs.
     */
    private void animationGoing() {
        KeyInputs.setMovement(Directions.STAY_X);
        KeyInputs.setMovement(Directions.STAY_Y);
        MouseInputs.animationGoing = true;
    }

    /**
     * finishing the animation giving back the inputs to the player.
     */
    private void animationDone() {
        KeyInputs.setMovement(Directions.STAY_X);
        KeyInputs.setMovement(Directions.STAY_Y);
        MouseInputs.animationGoing = false;
        this.hammerSmashed = false;
        this.hammerIsSmashing = false;
    }

    /**
     * checks if the hammer is max range and if not increase the range og hit.
     */
    public void setHammerRange() {
        if (hammerRange <= HAMMER_MAX_RANGE) {
            hammerRange += hammerIncrease;
        }
    }

    /**
     * returns the hammerRange to draw it.
     * @return hammerRange
     */
    public int getHammerRange() {
        return this.hammerRange;
    }

    /**
     * returns the hammer location on the screen.
     * @return hammerLocation
     */
    public Point2D getHammerLocation() {
        return this.hammerLocation;
    }

    /**
     * returns true if the cursor is on screen.
     * @return if the cursor is on screen
     */
    public boolean isCursorOnScreen() {
        return this.cursorOnScreen;
    }

    /**
     * returns if the hammer has smashed.
     * @return hammerSmashed
     */
    public boolean isHammerSmashed() {
        return this.hammerSmashed;
    }

    /**
     * returns if the animation is going to let the two input files can communicate.
     * @return if the animation is going
     */
    protected static boolean isAnimationGoing() {
        return MouseInputs.animationGoing;
    }

}
