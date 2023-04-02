package it.unibo.smol.controller.input;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.smol.common.Directions;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * tests for the keyInputs
 */
public class KeyInputsTest {

    private KeyInputs keyInputs;

    @BeforeEach
    public void initKeyInputs() {
        this.keyInputs = new KeyInputs();
    }

    /**
     * tests if the setMovement method actually insert a direction in the queue of movements.
     */
    @Test
    public void testKeyPressed() {
        keyInputs.setMovement(Directions.UP);
        assertEquals(Directions.UP, keyInputs.getMovement().orElseThrow());
    }

    /**
     * tests if the player actually gets stunned.
     */
    @Test
    public void testPlayerStunned() {
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
    public void testKeyReleased() {
        final var upReleased = new KeyEvent(KeyEvent.KEY_RELEASED, null, null, KeyCode.W,
            false, false, false, false);
        keyInputs.handle(upReleased);
        assertEquals(Directions.STAY_Y, keyInputs.getMovement().orElseThrow());
    }
    
}
