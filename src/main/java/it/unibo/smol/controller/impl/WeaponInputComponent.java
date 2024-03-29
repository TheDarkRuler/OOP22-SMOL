package it.unibo.smol.controller.impl;

import java.util.Optional;

import it.unibo.smol.common.Directions;
import it.unibo.smol.controller.api.EnemyInput;
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
     * @param mouse
     */
    public WeaponInputComponent(final Optional<MouseInputs> mouse) {
        this.mouse = mouse.orElseThrow();
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
        mouse.setWeaponRange();
        return Optional.of(mouse.getWeaponLocation());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHittable() {
        return mouse.isWeaponSmashed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void freezeInput(final int millisec) {
        mouse.freezeInputs(millisec);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<EnemyInput> getEnemyInput() {
        //this component will not use this method.
        return Optional.empty();
    }
}
