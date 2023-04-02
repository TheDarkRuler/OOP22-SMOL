package it.unibo.smol.controller.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.smol.common.Constant;
import javafx.scene.input.MouseEvent;

public class MouseInputsTest {

    private MouseInputs mouseInputs;

    /**
     * creates new MouseInput everytime.
     */
    @BeforeEach
    public void initMouseInput() {
        this.mouseInputs = new MouseInputs(Optional.of(new KeyInputs()));
    }
    
    /**
     * tests if the variable cursorOnScreen sets true when the cursor enter the screen.
     */
    @Test
    public void cursorOnScreen() {
        assertFalse(mouseInputs.isCursorOnScreen());
        final var cursorOnScreen = new MouseEvent(MouseEvent.MOUSE_ENTERED, 0, 0, 0, 0, null,
            0, false, false, false, false, false,
            false, false, false, false, false,
            false, false, null);
        mouseInputs.handle(cursorOnScreen);
        assertTrue(mouseInputs.isCursorOnScreen());
    }

    /**
     * tests if weaponSmashed is false before the weapon is smashing.
     */
    @Test
    public void mouseSmashed() {
        final var weaponSmash = new MouseEvent(MouseEvent.MOUSE_RELEASED, 0, 0, 0, 0, null,
            0, false, false, false, false, false,
            false, false, false, false, false,
            false, false, null);
        final var weaponIsSmashing = new MouseEvent(MouseEvent.MOUSE_PRESSED, 0, 0, 0, 0, null,
            0, false, false, false, false, false,
            false, false, false, false, false,
            false, false, null);
        assertFalse(mouseInputs.isWeaponSmashed());
        mouseInputs.handle(weaponSmash);
        assertFalse(mouseInputs.isWeaponSmashed());
        mouseInputs.handle(weaponIsSmashing);
        mouseInputs.handle(weaponSmash);
        assertTrue(mouseInputs.isWeaponSmashed());
        
    }

    /**
     * tests if the weapon expands correctly.
     */
    @Test
    public void weaponExpansion() {
        mouseInputs.setWeaponRange();
        assertEquals(Constant.DEF_WEAPON_RANGE, mouseInputs.getWeaponRange());
    }
}
