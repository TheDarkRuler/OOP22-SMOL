package it.unibo.smol.input.api;

public interface inputController {
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
}
