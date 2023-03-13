package it.unibo.smol.model.api;
/**
 *  Interface for control the state of the game.
 */
public interface GameState {
    /**
     * Get the current world.
     * @return game world
     */
    World getWorld();

    /**
     * @return if game is Over or not.
     */
    boolean isGameOver();

    /**
     *  Notify the world to increase the score.
     */
    void incScore();

    /**
     * Notify the world to decrease the score.
     */
    void decScore();

    /**
     * Getter for the score.
     * @return the current score
     */
    int getScore();

    /**
     *  Notify the world to kill entities.
     */
    void notifyDeath();
}
