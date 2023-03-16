package it.unibo.smol.controller.input;

import java.util.ArrayList;
import java.util.List;
import it.unibo.smol.view.api.GameMap;
import it.unibo.smol.view.impl.GameMapImpl;

public class EnemiesManager {
    
    private List<Enemy> enemies;
    private GameMap mapDimension;

    public EnemiesManager() {
        this.mapDimension = new GameMapImpl();
        this.enemies = new ArrayList<>();
    }

    public void addEnemy() {
        this.enemies.add(new Enemy(mapDimension));
    }

    public void removeEnemy(final Enemy enemy) {
        enemies.remove(enemies.indexOf(enemy));
    }

}
