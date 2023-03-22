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

import it.unibo.smol.controller.api.GameState;
import it.unibo.smol.view.GameMap;
import javafx.geometry.Point2D;

/**
 * Create enemies randomly, gives an initial position and changes weight for the random
 * when the score increases. 
*/
public class EnemyCreation {

    private static final double DEF_RATE_BASIC = 1.0;
    private static final double DEF_RATE_HELMET = 0.45;
    private static final double DEF_RATE_ANGRY = 0.325;
    private static final double DEF_RATE_BOMB = 0.2;
    private static final double INC_RATE_ANGRY = 0.025;
    private static final double INC_RATE_HELMET = 0.05;
    private static final int DEF_MAX_TIME_SPAWN = 4000;
    private static final int DEF_MIN_TIME_SPAWN = 3000;
    private static final int INC_DIFFICULTY_PIVOT = 20;
    private static final int DIFFICULTY_LIMIT = 5;

    private final GameState gameState;
    private final Map<String, Double> entitiesMap;
    private final int minTimeEnemySpawn;
    private final int maxTimeEnemySpawn;

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
        this.minTimeEnemySpawn = DEF_MIN_TIME_SPAWN;
        this.maxTimeEnemySpawn = DEF_MAX_TIME_SPAWN;
        creationTimer();
    }

    /**
     * Change the spawn rate of the enemies.
     */
    private void changeDifficulty() {
        final int temp = gameState.getScore() / INC_DIFFICULTY_PIVOT;
        entitiesMap.put("Angry_mole", DEF_RATE_ANGRY + temp * INC_RATE_ANGRY);
        entitiesMap.put("Helmet_mole", DEF_RATE_HELMET + temp * INC_RATE_HELMET);
    }

    /**
     * Spawn the enemy.
     * @param enemyName : the name of the enemy
     */
    private void spawnEntity(final String enemyName) {
        switch (enemyName) {
            case "Mole":
                new EntityFactoryImpl().createBasicEnemy(initialEnemyPosition(), gameState.getWorld());
                break;
            case "Helmet_mole":
                new EntityFactoryImpl().createHelmetEnemy(initialEnemyPosition(), gameState.getWorld());
                break;
            case "Angry_mole":
                new EntityFactoryImpl().createAngryEnemy(initialEnemyPosition(), gameState.getWorld());
                break;
            case "Bomb_mole":
                //new EntityFactoryImpl().creatBombMole(initialEnemyPosition());
                break;
            default:
                break;
        }
    }

    /**
     * Timer that create moles with a certain delay (minTimeEnemySpawn and maxTimeEnemySpawn).
     */
    private void creationTimer() {
        final Timer enemyCreationTimer = new Timer(minTimeEnemySpawn + new Random()
            .nextInt(maxTimeEnemySpawn - minTimeEnemySpawn),
            new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    final List<Double> weightList = new ArrayList<>(entitiesMap.values());
                    final Double randomDouble = Math.random();
                    Collections.sort(weightList);

                    if (gameState.getScore() / INC_DIFFICULTY_PIVOT <= DIFFICULTY_LIMIT) {
                        changeDifficulty();
                    }

                    for (final Double spawnPercentual : weightList) {
                        if (spawnPercentual >= randomDouble) {
                           spawnEntity(entitiesMap.entrySet()
                                .stream()
                                .filter(s -> s.getValue().equals(spawnPercentual))
                                .findAny().get().getKey());
                        }
                    }
                }
            });
        enemyCreationTimer.start();
    }

    /**
     * sets a position on the fence where the enemy starts.
     * @return the initial position
     */
    private Point2D initialEnemyPosition() {
        if (new Random().nextBoolean()) {
            return new Point2D(randomBetweenTwo(GameMap.BORDER_WIDTH / 2,
                GameMap.BORDER_WIDTH / 2 + GameMap.MAP_WIDTH),
                GameMap.BORDER_HEIGHT / 2 + new Random()
                    .nextDouble(GameMap.MAP_HEIGHT - GameMap.BORDER_HEIGHT / 2));
        } else {
            return new Point2D(GameMap.BORDER_WIDTH / 2 + new Random()
            .nextDouble(GameMap.MAP_WIDTH - GameMap.BORDER_WIDTH / 2),
                randomBetweenTwo(GameMap.BORDER_HEIGHT / 2,
                GameMap.BORDER_HEIGHT / 2 + GameMap.MAP_HEIGHT));
        }
    }

    /**
     * returns a random between two double given number.
     * @param first
     * @param second
     * @return one of the two given parameter
     */
    private double randomBetweenTwo(final double first, final double second) {
        return new Random().nextBoolean() ? first : second;
    }
}
