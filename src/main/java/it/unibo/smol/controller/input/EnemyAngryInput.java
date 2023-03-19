package it.unibo.smol.controller.input;

import it.unibo.smol.controller.api.EnemyInput;
import it.unibo.smol.model.api.GameState;

public class EnemyAngryInput extends EnemyInput {

    private static final int ANGRY_ENEMY_MAX_SPAWNS = 2;

    public EnemyAngryInput(final GameState gs) {
        super(ANGRY_ENEMY_MAX_SPAWNS, gs);
    }

}
