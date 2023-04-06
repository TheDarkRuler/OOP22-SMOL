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
public class HealthTest {

    private final int maxHealth = 10;
    private HealthComponent health = new HealthComponent(maxHealth);

    @Test
    void initializeHealthTest() {
        assertEquals(maxHealth, health.getCurrentHealth()); 
        assertFalse(health.isDead()); 
    }

    @Test
    void decreaseHealthTest() {
        health.setHealth(-1);
        assertNotEquals(maxHealth, health.getCurrentHealth());
        assertEquals(maxHealth - 1, health.getCurrentHealth()); 
        assertFalse(health.isDead()); 
    }

    @Test
    void increaseHealthTest() {
        health.setHealth(1);
        assertNotEquals(maxHealth - 1, health.getCurrentHealth());
        assertEquals(maxHealth, health.getCurrentHealth()); 
        assertFalse(health.isDead()); 
    }

    @Test
    void maxHealthTest() {
    health.setHealth(1);
        assertNotEquals(maxHealth + 1, health.getCurrentHealth());
    }

    @Test
    void deathTest() {
    health.setHealth(-maxHealth);
        assertTrue(health.isDead());
    }
}
