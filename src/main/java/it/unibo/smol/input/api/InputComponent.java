package it.unibo.smol.input.api;

import it.unibo.smol.model.api.Entity;

public interface InputComponent {
    /**
     * @return if the entity is moving Up
     */
    boolean isMoveUp();
    /**
     * @return if the entity is moving Down
     */
    boolean isMoveDown();
    /**
     * @return if the entity is moving Left
     */
    boolean isMoveLeft();
    /**
     * @return if the entity is moving Right
     */
    boolean isMoveRight();
    /**
     * @param entity
     */
    void update(Entity entity);
}
