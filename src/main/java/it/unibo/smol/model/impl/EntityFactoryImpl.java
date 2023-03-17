package it.unibo.smol.model.impl;

import java.util.Optional;

import it.unibo.smol.common.Constant;
import it.unibo.smol.common.hitbox.CircleHB;
import it.unibo.smol.common.hitbox.RectangleHB;
import it.unibo.smol.controller.impl.PlayerInputComponent;
import it.unibo.smol.controller.impl.WeaponInputComponent;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.EntityFactory;
import it.unibo.smol.model.impl.physicscomponent.PlayerPhysicsComponent;
import it.unibo.smol.model.impl.physicscomponent.WeaponPhysicsComponent;
import it.unibo.smol.model.impl.physicscomponent.EnemyPhysicsComponent;
import it.unibo.smol.model.impl.physicscomponent.LifePlantsPhysicsComponent;
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
        Optional.of(new HealthComponent(Constant.ENEMY_HP)), 
        null, 
        new EnemyPhysicsComponent(new RectangleHB(Constant.ENEMY_WIDTH, Constant.ENEMY_HEIGHT,
        new Point2D(x, y))),
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
        new PlayerPhysicsComponent(new RectangleHB(Constant.PLAYER_WIDTH, Constant.PLAYER_HEIGHT,
        new Point2D(x, y))),
        x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createLifePlants(final double x, final double y) {
        return new EntityImpl(Type.HEALTH,
        Optional.empty(),
        Optional.of(new HealthComponent(Constant.HEALTH_HP)),
        null,
        new LifePlantsPhysicsComponent(new RectangleHB(Constant.HEALTH_WIDTH, Constant.HEALTH_HEIGHT,
        new Point2D(x, y))),
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
        new WeaponPhysicsComponent(new CircleHB(new Point2D(x, y), Constant.WEAPON_RADIUS)),
        x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createElmetEnemy(final double x, final double y) {
        return new EntityImpl(Type.ENEMY,
        null,
        Optional.of(new HealthComponent(Constant.ENEMY_HELMET_HP)),
        null,
        new EnemyPhysicsComponent(new RectangleHB(Constant.ENEMY_WIDTH, Constant.ENEMY_HEIGHT,
        new Point2D(x, y))),
        x, y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createAngryEnemy(final double x, final double y) {
        return new EntityImpl(Type.ENEMY,
        null,
        Optional.of(new HealthComponent(Constant.ENEMY_HP)),
        null,
        new EnemyPhysicsComponent(new RectangleHB(Constant.ENEMY_WIDTH, Constant.ENEMY_HEIGHT,
        new Point2D(x, y))),
        x, y);
    }
}
