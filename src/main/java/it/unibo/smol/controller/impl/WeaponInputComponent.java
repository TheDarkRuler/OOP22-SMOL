package it.unibo.smol.controller.impl;

import java.util.Optional;

import it.unibo.smol.common.Directions;
import it.unibo.smol.controller.api.InputComponent;
import it.unibo.smol.controller.input.MouseInputs;
import javafx.geometry.Point2D;

/**
 * Implementation of the InputComponent of the weapon.
 */
public class WeaponInputComponent implements InputComponent {
    private final MouseInputs mouse;

    /**
     * constructor to initialize mouse inputs for weapon entity.
     */
    public WeaponInputComponent() {
        this.mouse = new MouseInputs();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Directions> getDirection() {
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Point2D> getPosition() {
        return Optional.of(mouse.getHammerLocation());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHittable() {
        return mouse.isHammerSmashed();
    }
}
