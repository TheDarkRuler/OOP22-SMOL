package it.unibo.smol.controller.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.smol.model.impl.WorldImpl;
import javafx.geometry.Point2D;

public class EnemyInputTest {

    private static final int MIN_TIMES_ENEMY_CAN_SPAWN = 2;

    /**
     * Tests  if the enemy spawns status is correct.
     */
    @Test
    public void enemySpawnStatus() {
        final var enemyInput = new EnemyInput(MIN_TIMES_ENEMY_CAN_SPAWN, Optional.of(new WorldImpl()), new Point2D(0, 0), 0);
        assertTrue(enemyInput.isEnemyUnder());
        assertFalse(enemyInput.isEnemyOnPlant());
        enemyInput.setEnemyPosition(new Point2D(1, 1));
        assertEquals(new Point2D(1, 1), enemyInput.getEnemyPosition());
    }

    /**
     * tests if the variable enemyTimesSpawn increase correctly.
     */
    @Test
    public void enemyGetsUp() {
        final var enemyInput = new EnemyInput(MIN_TIMES_ENEMY_CAN_SPAWN, Optional.of(new WorldImpl()), new Point2D(0, 0), 0);
        enemyInput.enemyIsUp();
        assertEquals(1, enemyInput.getEnemyTimesSpawn());
    }
}
