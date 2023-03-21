package it.unibo.smol.controller.input;

import it.unibo.smol.controller.api.EnemyInput;
import it.unibo.smol.model.api.World;

/**
 * creates a enemy with elmet.
 */
public class EnemyHelmetInput extends EnemyInput {

    private static final int HELMET_ENEMY_MAX_SPAWNS = 3;

    /**
     * gives the enemy Max times spawn and the given game state.
     * @param world
     */
    public EnemyHelmetInput(final World world) {
        super(HELMET_ENEMY_MAX_SPAWNS, world);
    }
}
