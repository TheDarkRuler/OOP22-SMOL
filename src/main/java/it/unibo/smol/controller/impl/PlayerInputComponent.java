package it.unibo.smol.controller.impl;

import java.util.Optional;

import it.unibo.smol.common.Directions;
import it.unibo.smol.controller.api.EnemyInput;
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
     * @param input
     */
    public PlayerInputComponent(final Optional<KeyInputs> input) {
        this.input = input.orElseThrow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Directions> getDirection() {
        return input.getMovement();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Point2D> getPosition() {
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<EnemyInput> getEnemyInput() {
        //this component will not use this method.
        return Optional.empty();
    }
}
