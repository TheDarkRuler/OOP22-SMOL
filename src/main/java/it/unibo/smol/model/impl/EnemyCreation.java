package it.unibo.smol.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    private static final Random RANDOM = new Random();

    private final GameState gameState;
    private final Map<String, Double> entitiesMap;
    private Timer createEnemyTimer;
    private int difficultyLevel;
    private int minTimeEnemySpawn;
    private int maxTimeEnemySpawn;

    /**
     * Constructor.
     * @param gameState
     */
    public EnemyCreation(final Optional<GameState> gameState) {
        this.gameState = gameState.orElseThrow();
        this.entitiesMap = new HashMap<>(Map.of("Mole", Constant.DEF_RATE_BASIC,
                                                "Helmet_mole", Constant.DEF_RATE_HELMET,
                                                "Angry_mole", Constant.DEF_RATE_ANGRY,
                                                "Bomb_mole", Constant.DEF_RATE_BOMB));
        this.minTimeEnemySpawn = Constant.DEF_MIN_TIME_SPAWN;
        this.maxTimeEnemySpawn = Constant.DEF_MAX_TIME_SPAWN;
        this.difficultyLevel = 0;
    }

    /**
     * Change the spawn rate of the enemies and the time spawn between two moles.
     */
    private void changeDifficulty() {
        final int temp = gameState.getScore() / Constant.INC_DIFFICULTY_PIVOT;
        entitiesMap.put("Angry_mole", Constant.DEF_RATE_ANGRY + (temp * Constant.INC_RATE_ANGRY));
        entitiesMap.put("Helmet_mole", Constant.DEF_RATE_HELMET + (temp * Constant.INC_RATE_HELMET));
        minTimeEnemySpawn = Constant.DEF_MIN_TIME_SPAWN - temp * Constant.DEC_TIME_SPAWN;
        maxTimeEnemySpawn = Constant.DEF_MAX_TIME_SPAWN - temp * Constant.DEC_TIME_SPAWN;
        difficultyLevel++;
        this.createEnemyTimer.cancel();
        creationTimer();
    }

    /**
     * Spawn the enemy.
     * @param enemyName : the name of the enemy
     */
    private void spawnEntity(final String enemyName) {
        //System.out.println(enemyName);
        switch (enemyName) {
            case "Mole":
                gameState.getWorld().orElseThrow()
                    .addEntity(gameState.getEntityFactory()
                        .createBasicEnemy(initialEnemyPosition(), gameState.getWorld().orElseThrow()));
                break;
            case "Helmet_mole":
                gameState.getWorld().orElseThrow()
                    .addEntity(gameState.getEntityFactory()
                        .createHelmetEnemy(initialEnemyPosition(), gameState.getWorld().orElseThrow()));
                break;
            case "Angry_mole":
                gameState.getWorld().orElseThrow()
                    .addEntity(gameState.getEntityFactory()
                        .createAngryEnemy(initialEnemyPosition(), gameState.getWorld().orElseThrow()));
                break;
            case "Bomb_mole":
                gameState.getWorld().orElseThrow()
                    .addEntity(gameState.getEntityFactory()
                        .createBombEnemy(initialEnemyPosition(), gameState.getWorld().orElseThrow()));
                break;
            default:
                break;
        }
    }

    /**
     * Timer that create moles with a certain delay (minTimeEnemySpawn and maxTimeEnemySpawn).
     */
    private void creationTimer() {

        this.createEnemyTimer = new Timer();
        this.createEnemyTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                if (gameState.getScore() / Constant.INC_DIFFICULTY_PIVOT <= Constant.DIFFICULTY_LIMIT
                    && difficultyLevel < gameState.getScore() / Constant.INC_DIFFICULTY_PIVOT) {
                    changeDifficulty();
                }
                final List<Double> weightList = new ArrayList<>(entitiesMap.values().stream().sorted().toList());
                final Double randomDouble = Math.random();

                spawnEntity(entitiesMap.entrySet()
                    .stream()
                    .filter(s -> s.getValue().equals(weightList.stream().sorted()
                        .filter(x -> x >= randomDouble)
                        .findFirst().get()))
                    .findAny().get().getKey());
            }

        }, minTimeEnemySpawn + RANDOM.nextInt(maxTimeEnemySpawn - minTimeEnemySpawn),
        minTimeEnemySpawn + RANDOM.nextInt(maxTimeEnemySpawn - minTimeEnemySpawn));
    }

    /**
     * sets a position on the fence where the enemy starts.
     * @return the initial position
     */
    private Point2D initialEnemyPosition() {
        if (RANDOM.nextBoolean()) {
            return new Point2D(randomBetweenTwo(GameMap.BORDER_WIDTH / 2,
                GameMap.BORDER_WIDTH / 2 + GameMap.MAP_WIDTH),
                GameMap.BORDER_HEIGHT / 2 + RANDOM
                    .nextDouble(GameMap.MAP_HEIGHT - GameMap.BORDER_HEIGHT / 2));
        } else {
            return new Point2D(GameMap.BORDER_WIDTH / 2 + RANDOM
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
        return RANDOM.nextBoolean() ? first : second;
    }

    /**
     * stops the creation of enemies.
     */
    public void stopCreation() {
        this.createEnemyTimer.cancel();
    }

    /**
     * starts the creation of the enemies.
     */
    public void startCreation() {
        creationTimer();
    }
}
