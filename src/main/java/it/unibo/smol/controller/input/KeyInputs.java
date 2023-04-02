package it.unibo.smol.controller.input;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.unibo.smol.common.Directions;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Class that creates an EventHandler for the keyboards events.
 */
public class KeyInputs implements EventHandler<KeyEvent> {
    /**
     * Direction of the player.
     */
    private final Queue<Directions> movement = new LinkedList<>();
    private static Logger logger = Logger.getLogger("keyInputLogger");
    private boolean playerFreeze;
    private boolean playerStunned;

    /**
     * Handles the events, selecting everytime the right KeyEvent.
     */
    @Override
    public void handle(final KeyEvent event) {

        if (event.getEventType().equals(KeyEvent.KEY_PRESSED) 
            && !this.playerFreeze && !this.playerStunned) {
            switch (event.getCode()) {
                case W:
                    this.setMovement(Directions.UP);
                    break;
                case A:
                    this.setMovement(Directions.LEFT);
                    break;
                case S:
                    this.setMovement(Directions.DOWN);
                    break;
                case D:
                    this.setMovement(Directions.RIGHT);
                    break;
                default:
                    break;
            }

        } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED) && !this.playerFreeze
            && !this.playerStunned) {

            switch (event.getCode()) {
                case W:
                case S:
                    this.setMovement(Directions.STAY_Y);
                    break;
                case A:
                case D:
                    this.setMovement(Directions.STAY_X);
                    break; 
                default:
                    break;
            }
        }
    }

    /**
     * gives the Direction assigned earlier.
     * @return the movement Direction
     */
    public Optional<Directions> getMovement() {
        final var temp = movement.poll();
        return temp != null ? Optional.of(temp) : Optional.empty();

    }

    /**
     * sets movement with the new paramenter letting the two input files communicate.
     * @param newMovement
     */
    public void setMovement(final Directions newMovement) {
        try {
            movement.offer(newMovement);
        } catch (NoSuchElementException e) {
            logger.log(Level.FINE, "KeyInputError::", e);
        }
    }

    /**
     * sets if the player is freezed.
     * @param playerFreeze
     */
    public void setPlayerFreezed(final boolean playerFreeze) {
        this.playerFreeze = playerFreeze;
    }

    /**
     * sets if the player is stunned.
     * @param playerStunned
     */
    public void serPlayerStunned(final boolean playerStunned) {
        this.playerStunned = playerStunned;
    }
}
