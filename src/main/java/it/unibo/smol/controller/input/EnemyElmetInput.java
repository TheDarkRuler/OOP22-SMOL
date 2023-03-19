package it.unibo.smol.controller.input;

import it.unibo.smol.controller.api.EnemyInput;
import it.unibo.smol.model.api.GameState;

public class EnemyElmetInput extends EnemyInput {

    private static final int ELMET_ENEMY_MAX_SPAWNS = 3;

    public EnemyElmetInput(final GameState gs) {
        super(ELMET_ENEMY_MAX_SPAWNS, gs);
    }
    
}
