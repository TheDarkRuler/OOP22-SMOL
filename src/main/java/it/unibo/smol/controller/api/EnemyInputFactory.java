package it.unibo.smol.controller.api;

import java.util.Optional;

import it.unibo.smol.model.api.World;
import javafx.geometry.Point2D;

/**
 * factory used to create the enemy inputs.
 */
public interface EnemyInputFactory {

    /**
     * creates an angry enemy input.
     * @param world
     * @param initialPosition
     * @return enemy input of the angry enemey
     */
    EnemyInput createAngryEnemyInput(Optional<World> world, Point2D initialPosition);

    /**
     * creates a basic enemy input.
     * @param world
     * @param initialPosition
     * @return enemy input of the basic enemey
     */
    EnemyInput createBasicEnemyInput(Optional<World> world, Point2D initialPosition);

    /**
     * creates an helmet enemy input.
     * @param world
     * @param initialPosition
     * @return enemy input of the helmet enemey
     */
    EnemyInput createHelmetEnemyInput(Optional<World> world, Point2D initialPosition);

    /**
     * creates a bomb enemy input.
     * @param world
     * @param initialPosition
     * @return enemy input of the bomb enemey
     */
    EnemyInput createBombEnemyInput(Optional<World> world, Point2D initialPosition);
}
