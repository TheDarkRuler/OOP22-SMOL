package it.unibo.smol.controller.input;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.smol.common.Directions;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * tests for the keyInputs
 */
public class KeyInputsTest {

    /**
     * tests if the setMovement method actually insert a direction in the queue of movements.
     */
    @Test
    public void testKeyPressed() {
        final var keyInputs = new KeyInputs();
        keyInputs.setMovement(Directions.UP);
        assertTrue(keyInputs.getMovement().orElseThrow().equals(Directions.UP));
    }

    /**
     * tests if the player actually gets stunned.
     */
    @Test
    public void testPlayerStunned() {
        final var keyInputs = new KeyInputs();
        final var up = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.W,
            false, false, false, false);
        keyInputs.serPlayerStunned(true);
        keyInputs.handle(up);
        keyInputs.serPlayerStunned(false);
        assertTrue(keyInputs.getMovement().equals(Optional.empty()));

    }

    /**
     * tests if the player stoppes when a key is released.
     */
    @Test
    public void testKeyReleased() {
        final var keyInputs = new KeyInputs();
        final var upReleased = new KeyEvent(KeyEvent.KEY_RELEASED, null, null, KeyCode.W,
            false, false, false, false);
        keyInputs.handle(upReleased);
        assertTrue(keyInputs.getMovement().orElseThrow().equals(Directions.STAY_Y));
    }
    
}
