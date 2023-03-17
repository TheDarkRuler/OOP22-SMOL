package it.unibo.smol.controller.input;

import java.util.List;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.World;
import it.unibo.smol.view.api.GameMap;
import it.unibo.smol.view.impl.GameMapImpl;

public class EnemyManager {

    private final GameMap mapDimension;
    private World world;
    private Entity enemyType;

    public EnemyManager(final World world, final Entity enemyType, final GameMap mapDimension) {
        this.world = world;
        this.mapDimension = new GameMapImpl();
        this.enemyType = enemyType;

        setTypeVariable();
    }

    private void setTypeVariable() {
        
    }
}
