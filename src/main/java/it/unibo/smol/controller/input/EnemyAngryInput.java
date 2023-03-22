package it.unibo.smol.controller.input;

import it.unibo.smol.controller.api.EnemyInput;
import it.unibo.smol.model.api.World;
import javafx.geometry.Point2D;

/**
 * creates an angry enemy.
 */
public class EnemyAngryInput extends EnemyInput {

    private static final int ANGRY_ENEMY_MAX_SPAWNS = 2;

    /**
     * gives the enemy Max times spawn and the given game state.
     * @param world
     * @param initialEnemyPosition
     */
    public EnemyAngryInput(final World world, final Point2D initialEnemyPosition) {
        super(ANGRY_ENEMY_MAX_SPAWNS, world, initialEnemyPosition);
    }

}
