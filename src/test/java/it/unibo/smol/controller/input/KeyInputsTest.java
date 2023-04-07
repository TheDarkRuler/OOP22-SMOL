package it.unibo.smol.controller.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.smol.common.Directions;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * tests for the keyInputs.
 */
class KeyInputsTest {

    private KeyInputs keyInputs;

    /**
     * tests if the setMovement method actually insert a direction in the queue of movements.
     */
    @Test
    void testKeyPressed() {
        this.keyInputs = new KeyInputs();
        keyInputs.setMovement(Directions.UP);
        assertEquals(Directions.UP, keyInputs.getMovement().orElseThrow());
    }

    /**
     * tests if the player actually gets stunned.
     */
    @Test
    void testPlayerStunned() {
        this.keyInputs = new KeyInputs();
        final var up = new KeyEvent(KeyEvent.KEY_PRESSED, null, null, KeyCode.W,
            false, false, false, false);
        keyInputs.setPlayerStunned(true);
        keyInputs.handle(up);
        keyInputs.setPlayerStunned(false);
        assertEquals(Optional.empty(), keyInputs.getMovement());

    }

    /**
     * tests if the player stoppes when a key is released.
     */
    @Test
    void testKeyReleased() {
        this.keyInputs = new KeyInputs();
        final var upReleased = new KeyEvent(KeyEvent.KEY_RELEASED, null, null, KeyCode.W,
            false, false, false, false);
        keyInputs.handle(upReleased);
        assertEquals(Directions.STAY_Y, keyInputs.getMovement().orElseThrow());
    }

}
