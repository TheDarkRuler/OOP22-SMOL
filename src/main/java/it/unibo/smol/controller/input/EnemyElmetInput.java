package it.unibo.smol.controller.input;

import it.unibo.smol.controller.api.EnemyInput;
import it.unibo.smol.model.api.World;

public class EnemyElmetInput extends EnemyInput {

    private static final int ELMET_ENEMY_MAX_SPAWNS = 3;

    public EnemyElmetInput(final World world) {
        super(ELMET_ENEMY_MAX_SPAWNS, world);
    }
    
}
