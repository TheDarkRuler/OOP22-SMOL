package it.unibo.smol.controller.impl;

import java.util.Optional;

import it.unibo.smol.common.Directions;
import it.unibo.smol.controller.api.EnemyInput;
import it.unibo.smol.controller.api.InputComponent;
import javafx.geometry.Point2D;

/**
 * Implementation of the InputComponent of any Enemies Entity.
 */
public class EnemyInputComponent implements InputComponent {

    private EnemyInput input;

    /**
     * constructor to initialize the AI inputs for any enemy entities.
     */
    public EnemyInputComponent(EnemyInput input) {
        this.input = input;
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
        return input.enemyUpdatePos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHittable() {
        return input.isEnemyUnder();
    }

    @Override
    public void freezeInput(int millisec) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'freezeInput'");
    }
    
}