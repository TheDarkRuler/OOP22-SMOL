package it.unibo.smol.controller.input;

import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import it.unibo.smol.common.Directions;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Class that creates an EventHandler for the keyboards events.
 */
public class KeyInputs implements EventHandler<KeyEvent> {
    /**
     * Direction of the player.
     */
    private static Queue<Directions> movement = new PriorityQueue<>();
    private final Stage stage;

    public KeyInputs(final Stage stage) {
        this.stage = stage;
    }

    /**
     * Handles the events, selecting everytime the right KeyEvent.
     */
    @Override
    public void handle(final KeyEvent event) {

        if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {

            if (event.getCode().equals(KeyCode.F11)) {
                if (stage.isFullScreen()) {
                    stage.setFullScreen(false);
                } else {
                    stage.setFullScreen(true);
                }
            }

            if (!MouseInputs.isPlayerFreezed() && !MouseInputs.isPlayerStunned()) {
            switch (event.getCode()) {
                case W:
                    KeyInputs.setMovement(Directions.UP);
                    break;
                case A:
                    KeyInputs.setMovement(Directions.LEFT);
                    break;
                case S:
                    KeyInputs.setMovement(Directions.DOWN);
                    break;
                case D:
                    KeyInputs.setMovement(Directions.RIGHT);
                    break;
                default:
                    break;
                }
            }

        } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED) && !MouseInputs.isPlayerFreezed()
            && !MouseInputs.isPlayerStunned()) {

            switch (event.getCode()) {
                case W:
                case S:
                    KeyInputs.setMovement(Directions.STAY_Y);
                    break;
                case A:
                case D:
                    KeyInputs.setMovement(Directions.STAY_X);
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
        if (!movement.isEmpty()) {
            var temp = movement.poll();
            return temp!=null ? Optional.of(temp) : Optional.empty();
        }
        return Optional.empty();
    }

    /**
     * sets movement with the new paramenter letting the two input files communicate.
     * @param newMovement
     */
    public static void setMovement(final Directions newMovement) {
        KeyInputs.movement.add(newMovement);
    }
}
