package it.unibo.smol.controller.input;

import it.unibo.smol.controller.api.EnemyInput;
import it.unibo.smol.model.api.GameState;

/**
 * creates an angry enemy.
 */
public class EnemyAngryInput extends EnemyInput {

    private static final int ANGRY_ENEMY_MAX_SPAWNS = 2;

    /**
     * gives the enemy Max times spawn and the given game state.
     * @param gs
     */
    public EnemyAngryInput(final GameState gs) {
        super(ANGRY_ENEMY_MAX_SPAWNS, gs);
    }

}
