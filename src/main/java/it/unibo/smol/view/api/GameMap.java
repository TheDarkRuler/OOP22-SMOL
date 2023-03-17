package it.unibo.smol.view.api;

/**
 * interface of the map of the game.
 */
public interface GameMap {
    /**
     * @return screen height.
     */
    double getHeight();

    /**
     * @return screen width.
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

    /**
     * @return map height
     */
    double getMapHeight();

    /**
     * @return map width
     */
    double getMapWidth();
}
