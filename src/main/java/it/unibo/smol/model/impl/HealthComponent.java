package it.unibo.smol.model.impl;

import it.unibo.smol.model.api.Entity;

/**
 * The class rappresenting the health component of the {@link Entity}.
 * This component is Optional and could be not present if the {@link Entity} doesn't need it
 */
public class HealthComponent {

    private int currentHealth;
    private final int maxHealth;

    /**
     * Constructor for the Health Component.
     * @param health : Rappresent the health of the the {@link Entity};
     */
    public HealthComponent(final int health) {
        this.currentHealth = health;
        maxHealth = health;
    }

    /**
     * Change the {@link #currentHealth} field.
     * @param healthChange : the amount of health to either add or subtract
     */
    public void setHealth(final int healthChange) {
        final int newHealth = currentHealth + healthChange;
        if (newHealth > maxHealth) {
            currentHealth = maxHealth;
        } else {
            currentHealth = newHealth;
        }
    }

    /**
     * Getter for the {@link #currentHealth} field.
     * @return {@link #currentHealth}
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Test if the Entity is dead.
     * @return {@code True} if the entity have more of 0 health; {@code False} otherwise
     */
    protected boolean isDead() {
        return currentHealth <= 0;
    }
}
