package it.unibo.smol.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.smol.model.impl.HealthComponent;
/**
 * Test for Health.
 */
class HealthTest {

    private static final int MAX_HEALTH = 10;
    private final HealthComponent health = new HealthComponent(MAX_HEALTH);

    @Test
    void initializeHealthTest() {
        assertEquals(MAX_HEALTH, health.getCurrentHealth()); 
        assertFalse(health.isDead()); 
    }

    @Test
    void decreaseHealthTest() {
        health.setHealth(-1);
        assertNotEquals(MAX_HEALTH, health.getCurrentHealth());
        assertEquals(MAX_HEALTH - 1, health.getCurrentHealth()); 
        assertFalse(health.isDead()); 
    }

    @Test
    void increaseHealthTest() {
        health.setHealth(1);
        assertNotEquals(MAX_HEALTH - 1, health.getCurrentHealth());
        assertEquals(MAX_HEALTH, health.getCurrentHealth()); 
        assertFalse(health.isDead()); 
    }

    @Test
    void maxHealthTest() {
    health.setHealth(1);
        assertNotEquals(MAX_HEALTH + 1, health.getCurrentHealth());
    }

    @Test
    void deathTest() {
    health.setHealth(-MAX_HEALTH);
        assertTrue(health.isDead());
    }
}
