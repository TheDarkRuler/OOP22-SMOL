package it.unibo.smol.input;

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
    private static Directions movement;

    /**
     * Handles the events, selecting everytime the right KeyEvent.
     */
    @Override
    public void handle(final KeyEvent event) {

        if (event.getEventType().equals(KeyEvent.KEY_PRESSED) && !MouseInputs.isAnimationGoing()) {
            switch (event.getCode()) {
                case W:
                    movement = Directions.UP;
                    break;
                case A:
                    movement = Directions.LEFT;
                    break;
                case S:
                    movement = Directions.DOWN;
                    break;
                case D:
                    movement = Directions.RIGHT;
                    break;
                default:
                    break;
            }

        } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED) && !MouseInputs.isAnimationGoing()) {

            switch (event.getCode()) {
                case W:
                case S:
                    movement = Directions.STAY_Y;
                    break;
                case A:
                case D:
                    movement = Directions.STAY_X;
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
    public Directions getMovement() {
        return KeyInputs.movement;
    }

    /**
     * sets movement with the new paramenter letting the two input files communicate.
     * @param newMovement
     */
    public static void setMovement(final Directions newMovement) {
        KeyInputs.movement = newMovement;
    }
}
