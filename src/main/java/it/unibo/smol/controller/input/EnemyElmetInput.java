package it.unibo.smol.controller.input;

import it.unibo.smol.controller.api.EnemyInput;
import it.unibo.smol.model.api.GameState;

/**
 * creates a enemy with elmet.
 */
public class EnemyElmetInput extends EnemyInput {

    private static final int ELMET_ENEMY_MAX_SPAWNS = 3;

    /**
     * gives the enemy Max times spawn and the given game state.
     * @param gs
     */
    public EnemyElmetInput(final GameState gs) {
        super(ELMET_ENEMY_MAX_SPAWNS, gs);
    }
}
