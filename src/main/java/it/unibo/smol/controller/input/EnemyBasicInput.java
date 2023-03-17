package it.unibo.smol.controller.input;

import it.unibo.smol.controller.api.EnemyInput;
import it.unibo.smol.model.api.World;

public class EnemyBasicInput extends EnemyInput {

    private static final int BASIC_ENEMY_MAX_SPAWNS = 3;

    public EnemyBasicInput(final World world) {
        super(BASIC_ENEMY_MAX_SPAWNS, world);
    }
    
}
