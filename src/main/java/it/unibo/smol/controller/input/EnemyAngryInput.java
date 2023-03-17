package it.unibo.smol.controller.input;

import it.unibo.smol.controller.api.EnemyInput;
import it.unibo.smol.model.api.World;

public class EnemyAngryInput extends EnemyInput {

    private static final int ANGRY_ENEMY_MAX_SPAWNS = 2;

    public EnemyAngryInput(final World world) {
        super(ANGRY_ENEMY_MAX_SPAWNS, world);
    }

}
