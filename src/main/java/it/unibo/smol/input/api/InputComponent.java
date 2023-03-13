package it.unibo.smol.input.api;

import it.unibo.smol.common.Directions;

public interface InputComponent {
    Directions getDirection();
    /**
     * @param entity
     */
    InputComponent update();
}
