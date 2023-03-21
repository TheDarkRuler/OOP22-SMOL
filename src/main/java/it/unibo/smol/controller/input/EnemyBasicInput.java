package it.unibo.smol.controller.input;

import it.unibo.smol.controller.api.EnemyInput;
import it.unibo.smol.model.api.World;

/**
 * creates a basic enemy.
 */
public class EnemyBasicInput extends EnemyInput {

    private static final int BASIC_ENEMY_MAX_SPAWNS = 3;

    /**
     * gives the enemy Max times spawn and the given game state.
     * @param gs
     */
    public EnemyBasicInput(final World world) {
        super(BASIC_ENEMY_MAX_SPAWNS, world);
    }
}
