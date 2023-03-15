package it.unibo.smol.view.api;

/**
 * interface of the map of the game.
 */
public interface GameMap {
    /**
     * @return map height.
     */
    double getHeight();

    /**
     * @return map width.
     */
    double getWidth();

    /**
     * @return 
     */
    double getBorderHeight();

    /**
     * 
     */
    double getBorderWidth();
}
