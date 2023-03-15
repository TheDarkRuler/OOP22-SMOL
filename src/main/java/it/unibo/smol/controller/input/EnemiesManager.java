package it.unibo.smol.controller.input;

import java.util.ArrayList;
import java.util.List;
import it.unibo.smol.view.api.GameMap;
import it.unibo.smol.view.impl.GameMapImpl;

public class EnemiesManager {
    
    private List<Enemie> enemies;
    private GameMap mapDimension;

    public EnemiesManager() {
        this.mapDimension = new GameMapImpl();
        this.enemies = new ArrayList<>();
    }

    public void addEnemie() {
        this.enemies.add(new Enemie(mapDimension));
    }

    public void removeEnemie(final Enemie enemie) {
        enemies.remove(enemies.indexOf(enemie));
    }

}
