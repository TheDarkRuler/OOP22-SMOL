package it.unibo.smol.controller.input;

import it.unibo.smol.controller.api.EnemyInput;
import it.unibo.smol.model.api.World;

import java.util.Optional;

import it.unibo.smol.common.Constant;
import javafx.geometry.Point2D;

/**
 * creates a basic enemy.
 */
public class EnemyBasicInput extends EnemyInput {

    /**
     * gives the enemy Max times spawn and the given game state.
     * @param world
     * @param initialEnemyPosition
     */
    public EnemyBasicInput(final Optional<World> world, final Point2D initialEnemyPosition) {
        super(Constant.BASIC_ENEMY_MAX_SPAWNS, world, initialEnemyPosition, Constant.BASIC_ENEMY_SPEED);
    }
}
