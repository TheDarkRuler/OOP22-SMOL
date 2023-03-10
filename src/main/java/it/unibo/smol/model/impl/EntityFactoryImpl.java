package it.unibo.smol.model.impl;

import java.util.Optional;

import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.EntityFactory;
/**
 * Implemention of the {@link EntityFactory} interface.
 */
public class EntityFactoryImpl implements EntityFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createBasicMole(final double x, final double y) {
        return new EntityImpl(Type.ENEMY, null, Optional.of(new HealthComponent(1)), null, null, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createPlayer(final double x, final double y) {
        return new EntityImpl(Type.PLAYER, null, Optional.empty(), null, null, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createLifePlants(final double x, final double y) {
        return new EntityImpl(Type.HEALTH, null, Optional.of(new HealthComponent(1000)), null, null, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createWeapon(final double x, final double y) {
        return new EntityImpl(Type.WEAPON, null, Optional.empty(), null, null, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createElmetMole(final double x, final double y) {
        return new EntityImpl(Type.ENEMY, null, Optional.of(new HealthComponent(2)), null, null, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createAngryMole(final double x, final double y) {
        return new EntityImpl(Type.ENEMY, null, Optional.of(new HealthComponent(1)), null, null, x, y);
    }
}
