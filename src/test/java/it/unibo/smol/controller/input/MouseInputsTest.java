package it.unibo.smol.controller.input;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.smol.common.Constant;
import javafx.scene.input.MouseEvent;

public class MouseInputsTest {

    private MouseInputs mouseInputs;

    @BeforeEach
    public void initMouseInput() {
        this.mouseInputs = new MouseInputs(Optional.of(new KeyInputs()));
    }
    
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

    @Test
    public void weaponExpansion() {
        mouseInputs.setWeaponRange();
        assertTrue(mouseInputs.getWeaponRange() == Constant.DEF_WEAPON_RANGE);
    }
}
