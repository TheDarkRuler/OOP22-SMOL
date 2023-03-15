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
     * @return top and bottom border of game fence.
     */
    double getBorderHeight();

    /**
     * @return left and right border of game fence.
     */
    double getBorderWidth();
}
