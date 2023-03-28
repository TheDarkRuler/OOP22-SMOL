package it.unibo.smol.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import it.unibo.smol.common.Constant;
import it.unibo.smol.controller.api.GameState;
import it.unibo.smol.view.GameMap;
import javafx.geometry.Point2D;

/**
 * Create enemies randomly, gives an initial position and changes weight for the random
 * when the score increases. 
*/
public class EnemyCreation {

    

    private final GameState gameState;
    private final Map<String, Double> entitiesMap;
    private int minTimeEnemySpawn;
    private int maxTimeEnemySpawn;
    private final Random rand;
    private final Timer createEnemyTimer;

    /**
     * Constructor.
     * @param gameState
     */
    public EnemyCreation(final GameState gameState) {
        this.gameState = gameState;
        this.entitiesMap = new HashMap<>(Map.of("Mole", Constant.DEF_RATE_BASIC,
                                                "Helmet_mole", Constant.DEF_RATE_HELMET,
                                                "Angry_mole", Constant.DEF_RATE_ANGRY,
                                                "Bomb_mole", Constant.DEF_RATE_BOMB));
        this.minTimeEnemySpawn = Constant.DEF_MIN_TIME_SPAWN;
        this.maxTimeEnemySpawn = Constant.DEF_MAX_TIME_SPAWN;
        this.createEnemyTimer = new Timer();
        this.rand = new Random();
        creationTimer();
    }

    /**
     * Change the spawn rate of the enemies.
     */
    private void changeDifficulty() {
        final int temp = gameState.getScore() / Constant.INC_DIFFICULTY_PIVOT;
        entitiesMap.put("Angry_mole", Constant.DEF_RATE_ANGRY + (temp * Constant.INC_RATE_ANGRY));
        entitiesMap.put("Helmet_mole", Constant.DEF_RATE_HELMET + (temp * Constant.INC_RATE_HELMET));
        minTimeEnemySpawn -= temp * Constant.DEC_TIME_SPAWN;
        maxTimeEnemySpawn -= temp * Constant.DEC_TIME_SPAWN;
        System.out.println(minTimeEnemySpawn);
        System.out.println(maxTimeEnemySpawn);
    }

    /**
     * Spawn the enemy.
     * @param enemyName : the name of the enemy
     */
    private void spawnEntity(final String enemyName) {
        //System.out.println(enemyName);
        switch (enemyName) {
            case "Mole":
                gameState.getWorld()
                    .addEntity(gameState.getEntityFactory().createBasicEnemy(initialEnemyPosition(), gameState.getWorld()));
                break;
            case "Helmet_mole":
                gameState.getWorld()
                    .addEntity(gameState.getEntityFactory().createHelmetEnemy(initialEnemyPosition(), gameState.getWorld()));
                break;
            case "Angry_mole":
                gameState.getWorld()
                    .addEntity(gameState.getEntityFactory().createAngryEnemy(initialEnemyPosition(), gameState.getWorld()));
                break;
            case "Bomb_mole":
                gameState.getWorld()
                    .addEntity(gameState.getEntityFactory().createBombEnemy(initialEnemyPosition(), gameState.getWorld()));
                break;
            default:
                break;
        }
    }

    
    /**
     * Timer that create moles with a certain delay (minTimeEnemySpawn and maxTimeEnemySpawn).
     */
    private void creationTimer() {

        this.createEnemyTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (gameState.getScore() / Constant.INC_DIFFICULTY_PIVOT <= Constant.DIFFICULTY_LIMIT) {
                    changeDifficulty();
                }
                final List<Double> weightList = new ArrayList<>(entitiesMap.values().stream().sorted().toList());
                final Double randomDouble = Math.random();

                System.out.println(weightList);
                spawnEntity(entitiesMap.entrySet()
                    .stream()
                    .filter(s -> s.getValue().equals(weightList.stream().sorted()
                        .filter(x -> x >= randomDouble)
                        .findFirst().get()))
                    .findAny().get().getKey());
            }
            
        }, minTimeEnemySpawn + rand.nextInt(maxTimeEnemySpawn - minTimeEnemySpawn), 
        minTimeEnemySpawn + rand.nextInt(maxTimeEnemySpawn - minTimeEnemySpawn));
    }

    /**
     * sets a position on the fence where the enemy starts.
     * @return the initial position
     */
    private Point2D initialEnemyPosition() {
        if (new Random().nextBoolean()) {
            return new Point2D(randomBetweenTwo(GameMap.BORDER_WIDTH / 2,
                GameMap.BORDER_WIDTH / 2 + GameMap.MAP_WIDTH),
                GameMap.BORDER_HEIGHT / 2 + rand
                    .nextDouble(GameMap.MAP_HEIGHT - GameMap.BORDER_HEIGHT / 2));
        } else {
            return new Point2D(GameMap.BORDER_WIDTH / 2 + rand
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
        return rand.nextBoolean() ? first : second;
    }

    public void stopCreation() {
        this.createEnemyTimer.cancel();
    }
}
