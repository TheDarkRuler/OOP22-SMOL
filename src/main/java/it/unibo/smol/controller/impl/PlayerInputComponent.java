package it.unibo.smol.controller.impl;

import java.util.Optional;

import it.unibo.smol.common.Directions;
import it.unibo.smol.controller.api.InputComponent;
import it.unibo.smol.controller.input.KeyInputs;
import javafx.geometry.Point2D;

/**
 * Implementation of the InputComponent of the player.
 */
public class PlayerInputComponent implements InputComponent {
    private final KeyInputs input;

    /**
     * constructor to initialize keyboard inputs for player entity.
     */
    public PlayerInputComponent() {
        input = new KeyInputs();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Optional<Directions> getDirection() {
        return Optional.of(input.getMovement());
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Optional<Point2D> getPosition() {
        return Optional.empty();
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public boolean isHittable() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void freezeInput(final int millisec) {
        //This component don't use this feature yet.
    }
}
