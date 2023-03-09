package it.unibo.smol.model.impl;

import java.util.Optional;

import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.*;
/**
 * Implemention of the {@link EntityFactory} interface.
 */
public class EntityFactoryImpl implements EntityFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createBasicMole(double x, double y) {
        return new EntityImpl(Type.ENEMY, null, Optional.of(new HealthComponent(1)), null, null, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createPlayer(double x, double y) {
        return new EntityImpl(Type.PLAYER, null, Optional.empty(), null, null, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createLifePlants(double x, double y) {
        return new EntityImpl(Type.HEALTH, null, Optional.of(new HealthComponent(1000)), null, null, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createWeapon(double x, double y) {
        return new EntityImpl(Type.WEAPON, null, Optional.empty(), null, null, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createElmetMole(double x, double y) {
        return new EntityImpl(Type.ENEMY, null, Optional.of(new HealthComponent(2)), null, null, x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createAngryMole(double x, double y) {
        return new EntityImpl(Type.ENEMY, null, Optional.of(new HealthComponent(1)), null, null, x, y);
    }
    
}
