package it.unibo.smol.model.api;

/**
 * interface of the map of the game.
 */
public interface GameMap {
    /**
     * @return map height.
     */
    int getHeight();

    /**
     * @return map width.
     */
    int getWidth();

    /**
     * 
     */
    int getBorderHeight();

    /**
     * 
     */
    int getBorderWidth();
}
