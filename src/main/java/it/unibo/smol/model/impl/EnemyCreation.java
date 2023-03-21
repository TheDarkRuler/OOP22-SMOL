package it.unibo.smol.model.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.Timer;

import it.unibo.smol.model.api.GameState;

/**
 *
 */
public class EnemyCreation {

    private static final double DEF_RATE_BASIC = 1.0;
    private static final double DEF_RATE_HELMET = 0.45;
    private static final double DEF_RATE_ANGRY = 0.325;
    private static final double DEF_RATE_BOMB = 0.2;
    private static final int INC_DIFFICULTY_PIVOT = 20;
    private static final int DIFFICULTY_LIMIT = 5;

    private final GameState gameState;
    private Map<String, Double> entitiesMap;
    private int minTimeEnemySpawn;
    private int maxTimeEnemySpawn;
    private List<Double> weightList;
    private Timer enemyCreationTimer;

    /**
     * Constructor.
     * @param gameState
     */
    public EnemyCreation(final GameState gameState) {
        this.gameState = gameState;
        this.entitiesMap = new HashMap<>(Map.of("Mole", DEF_RATE_BASIC,
                                                "Helmet_mole", DEF_RATE_HELMET,
                                                "Angry_mole", DEF_RATE_ANGRY,
                                                "Bomb_mole", DEF_RATE_BOMB));
        this.minTimeEnemySpawn = 3000;
        this.maxTimeEnemySpawn = 4000;
        creationTimer();
    }

    /**
     * Change the spawn rate of the enemies.
     */
    private void changeDifficulty() {
        entitiesMap.put("Angry_mole", DEF_RATE_ANGRY + gameState.getScore() / INC_DIFFICULTY_PIVOT * 0.025);
        entitiesMap.put("Helmet_mole", DEF_RATE_HELMET + gameState.getScore() / INC_DIFFICULTY_PIVOT * 0.05);
    }

    /**
     * Spawn the enemy.
     * @param enemyName : the name of the enemy
     */
    private void spawnEntity(String enemyName) {
        switch (enemyName) {
            case "Mole":
                
                break;
            case "Helmet_mole":
                break;
            case "Angry_mole":
                break;
            case "Bomb_mole":
                break;
            default:
                break;
        }
    }

    private void creationTimer() {
        this.enemyCreationTimer = new Timer(minTimeEnemySpawn + new Random().nextInt(maxTimeEnemySpawn - minTimeEnemySpawn),
            new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    weightList = new ArrayList<>(entitiesMap.values());
                    Double randomDouble = Math.random();
                    Collections.sort(weightList);

                    if (gameState.getScore() / INC_DIFFICULTY_PIVOT <= DIFFICULTY_LIMIT) {
                        changeDifficulty();
                    }

                    for (Double spawnPercentual : weightList) {
                        if (spawnPercentual >= randomDouble) {
                           spawnEntity(entitiesMap.entrySet()
                                .stream()
                                .filter(s->s.getValue().equals(spawnPercentual))
                                .findAny().get().getKey());
                        }
                    }
                }
            });
        this.enemyCreationTimer.start();
    } 
}
