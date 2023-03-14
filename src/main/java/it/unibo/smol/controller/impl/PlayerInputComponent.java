package it.unibo.smol.controller.impl;

import java.util.Optional;

import it.unibo.smol.common.Directions;
import it.unibo.smol.controller.api.InputComponent;
import it.unibo.smol.controller.input.*;
import javafx.geometry.Point2D;

public class PlayerInputComponent implements InputComponent {
    private KeyInputs input;

    public PlayerInputComponent(){
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
    
}
