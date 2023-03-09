package it.unibo.smol.input.api;

import it.unibo.smol.model.api.Entity;

public interface inputComponent {
    /**
     * @param entity
     */
    void update(Entity entity);
}
