package it.unibo.smol.model.impl;

import java.util.Optional;

import it.unibo.smol.common.hitbox.CircleHB;
import it.unibo.smol.common.hitbox.RectangleHB;
import it.unibo.smol.controller.impl.PlayerInputComponent;
import it.unibo.smol.controller.impl.WeaponInputComponent;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.EntityFactory;
import it.unibo.smol.model.impl.physicsComponent.EnemyPhysicsComponent;
import it.unibo.smol.model.impl.physicsComponent.LifePlantsPhysicsComponent;
import it.unibo.smol.model.impl.physicsComponent.PlayerPhysicsComponent;
import it.unibo.smol.model.impl.physicsComponent.WeaponPhysicsComponent;
import javafx.geometry.Point2D;
/**
 * Implemention of the {@link EntityFactory} interface.
 */
public class EntityFactoryImpl implements EntityFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createBasicEnemy(final double x, final double y) {
        return new EntityImpl(Type.ENEMY, 
        null,
        Optional.of(new HealthComponent(1)), 
        null, 
        new EnemyPhysicsComponent(new RectangleHB(90, 160, new Point2D(x, y))),
        x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createPlayer(final double x, final double y) {
        return new EntityImpl(Type.PLAYER, 
        Optional.of(new PlayerInputComponent()),
        Optional.empty(),
        null,
        new PlayerPhysicsComponent(new RectangleHB(90, 160, new Point2D(x, y))),
        x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createLifePlants(final double x, final double y) {
        return new EntityImpl(Type.HEALTH,
        Optional.empty(),
        Optional.of(new HealthComponent(1000)),
        null,
        new LifePlantsPhysicsComponent(new RectangleHB(180, 320, new Point2D(x, y))),
        x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createWeapon(final double x, final double y) {
        return new EntityImpl(Type.WEAPON,
        Optional.of(new WeaponInputComponent()),
        Optional.empty(),
        null,
        new WeaponPhysicsComponent(new CircleHB(new Point2D(x, y), 20)),
        x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createElmetEnemy(final double x, final double y) {
        return new EntityImpl(Type.ENEMY,
        null,
        Optional.of(new HealthComponent(2)),
        null,
        new EnemyPhysicsComponent(new RectangleHB(90, 160, new Point2D(x, y))),
        x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createAngryEnemy(final double x, final double y) {
        return new EntityImpl(Type.ENEMY,
        null,
        Optional.of(new HealthComponent(1)),
        null,
        new EnemyPhysicsComponent(new RectangleHB(90, 160, new Point2D(x, y))),
        x, y);
    }
}
