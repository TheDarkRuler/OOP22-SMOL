package it.unibo.smol.controller.input;

import it.unibo.smol.common.Constant;
import it.unibo.smol.controller.api.EnemyInput;
import it.unibo.smol.model.api.World;
import javafx.geometry.Point2D;

/**
 * creates a enemy with elmet.
 */
public class EnemyHelmetInput extends EnemyInput {

    /**
     * gives the enemy Max times spawn and the given game state.
     * @param world
     * @param initialEnemyPosition
     */
    public EnemyHelmetInput(final World world, final Point2D initialEnemyPosition) {
        super(Constant.HELMET_ENEMY_MAX_SPAWNS, world, initialEnemyPosition, Constant.HELMET_ENEMY_SPEED);
    }
}
