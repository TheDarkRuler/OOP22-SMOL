package it.unibo.smol.model.api;

import java.util.Optional;

/**
 * Abstract class rappresenting the template of the health component for the {@link GameObject}.
 */
public abstract class HealthComponent {

    private Optional<Integer> currentHealth;
    private final Optional<Integer> maxHealth;

    /**
     * Constructor for the Health Component.
     * @param health : if present rappresent the health of the the {@link GameObject};
     * otherwise the {@link GameObject} doesn't use this component
     */
    public HealthComponent(final Optional<Integer> health) {
        this.currentHealth = health;
        maxHealth = health;
    }

    /**
     * Update the current state of the {@link HealthComponent}.
     */
    public abstract void update();

    /**
     * Change the {@link #currentHealth} field.
     * @param healthChange : the amount of health to either add or subtract
     */
    public void setHealth(final int healthChange) {
        final int newHealth = currentHealth.orElseThrow() + healthChange;
        if (newHealth > maxHealth.orElseThrow()) {
            currentHealth = maxHealth;
        } else {
            currentHealth = Optional.of(newHealth);
        }
    }

    /**
     * Getter for the {@link #currentHealth} field.
     * @return {@link #currentHealth}
     */
    public int getCurrentHealth() {
        return currentHealth.orElseThrow();
    }

    /**
     * Test if the Entity is dead.
     * @return {@code True} if the entity have more of 0 health; {@code False} otherwise
     */
    protected boolean isDead() {
        return currentHealth.orElseThrow() <= 0;
    }
}
