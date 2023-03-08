package it.unibo.smol.model.api;

public interface GameState {
    /**
     * @return game world
     */
    World getWorld();

    /**
     * @return if game is Over or not
     */
    boolean isGameOver();

}
