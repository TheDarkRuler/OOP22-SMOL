package it.unibo.smol.model.impl;

import java.util.Optional;

import it.unibo.smol.common.Constant;
import it.unibo.smol.common.HitBox;
import it.unibo.smol.common.hitbox.CircleHB;
import it.unibo.smol.common.hitbox.RectangleHB;
import it.unibo.smol.controller.impl.EnemyInputComponent;
import it.unibo.smol.controller.impl.PlayerInputComponent;
import it.unibo.smol.controller.impl.WeaponInputComponent;
import it.unibo.smol.controller.input.EnemyAngryInput;
import it.unibo.smol.controller.input.EnemyBasicInput;
import it.unibo.smol.controller.input.EnemyBombInput;
import it.unibo.smol.controller.input.EnemyHelmetInput;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.EntityFactory;
import it.unibo.smol.model.api.World;
import it.unibo.smol.model.impl.physicscomponent.BombEnemyPhysicsComponent;
import it.unibo.smol.model.impl.physicscomponent.EnemyPhysicsComponent;
import it.unibo.smol.model.impl.physicscomponent.LifePlantsPhysicsComponent;
import it.unibo.smol.model.impl.physicscomponent.PlayerPhysicsComponent;
import it.unibo.smol.model.impl.physicscomponent.WallPhysicsComponent;
import it.unibo.smol.model.impl.physicscomponent.WeaponPhysicsComponent;
import it.unibo.smol.view.impl.graphiccomponent.AngryEnemyGraphicComponent;
import it.unibo.smol.view.impl.graphiccomponent.BasicEnemyGraphicComponent;
import it.unibo.smol.view.impl.graphiccomponent.BombEnemyGraphicComponent;
import it.unibo.smol.view.impl.graphiccomponent.HelmetEnemyGraphicComponent;
import it.unibo.smol.view.impl.graphiccomponent.LifePlantsGraphicComponent;
import it.unibo.smol.view.impl.graphiccomponent.PlayerGraphicComponent;
import it.unibo.smol.view.impl.graphiccomponent.WeaponGraphicComponent;
import javafx.geometry.Point2D;
/**
 * Implemention of the {@link EntityFactory} interface.
 */
public class EntityFactoryImpl implements EntityFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createPlayer(final double x, final double y, final World w) {
        return new EntityImpl(Type.PLAYER, 
        Optional.of(new PlayerInputComponent(w.getKeyInputs())),
        Optional.empty(),
        Optional.of(new PlayerGraphicComponent(Constant.PLAYER_WIDTH, Constant.PLAYER_HEIGHT)),
        new PlayerPhysicsComponent(new RectangleHB(Constant.PLAYER_WIDTH, Constant.PLAYER_HEIGHT,
            new Point2D(x, y))),
        x, y, w);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createLifePlants(final double x, final double y, final World w) {
        return new EntityImpl(Type.HEALTH,
        Optional.empty(),
        Optional.of(new HealthComponent(Constant.HEALTH_HP)),
        Optional.of(new LifePlantsGraphicComponent(Constant.HEALTH_WIDTH, Constant.HEALTH_HEIGHT)),
        new LifePlantsPhysicsComponent(new RectangleHB(Constant.HEALTH_WIDTH, Constant.HEALTH_HEIGHT,
            new Point2D(x, y))),
        x, y, w);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createWeapon(final double x, final double y, final World w) {
        return new EntityImpl(Type.WEAPON,
        Optional.of(new WeaponInputComponent(w.getMouseInputs())),
        Optional.empty(),
        Optional.of(new WeaponGraphicComponent(2 * Constant.WEAPON_RADIUS, 2 * Constant.WEAPON_RADIUS)),
        new WeaponPhysicsComponent(new CircleHB(new Point2D(x, y), Constant.WEAPON_RADIUS)),
        x, y, w);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createBasicEnemy(final Point2D initialPosition, final World w) {
        return new EntityImpl(Type.ENEMY, 
        Optional.of(new EnemyInputComponent(new EnemyBasicInput(w, initialPosition))),
        Optional.of(new HealthComponent(Constant.ENEMY_HP)), 
        Optional.of(new BasicEnemyGraphicComponent(Constant.ENEMY_WIDTH, Constant.ENEMY_HEIGHT)), 
        new EnemyPhysicsComponent(new RectangleHB(Constant.ENEMY_WIDTH, Constant.ENEMY_HEIGHT,
        initialPosition), Constant.BASIC_ENEMY_SPEED),
        initialPosition.getX(), initialPosition.getY(), w);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createHelmetEnemy(final Point2D initialPosition, final World w) {
        return new EntityImpl(Type.ENEMY,
        Optional.of(new EnemyInputComponent(new EnemyHelmetInput(w, initialPosition))),
        Optional.of(new HealthComponent(Constant.ENEMY_HELMET_HP)),
        Optional.of(new HelmetEnemyGraphicComponent(Constant.ENEMY_WIDTH, Constant.ENEMY_HEIGHT)),
        new EnemyPhysicsComponent(new RectangleHB(Constant.ENEMY_WIDTH, Constant.ENEMY_HEIGHT,
        initialPosition), Constant.HELMET_ENEMY_SPEED),
        initialPosition.getX(), initialPosition.getY(), w);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createAngryEnemy(final Point2D initalPosition, final World w) {
        return new EntityImpl(Type.ENEMY,
        Optional.of(new EnemyInputComponent(new EnemyAngryInput(w, initalPosition))),
        Optional.of(new HealthComponent(Constant.ENEMY_HP)),
        Optional.of(new AngryEnemyGraphicComponent(Constant.ENEMY_WIDTH, Constant.ENEMY_HEIGHT)),
        new EnemyPhysicsComponent(new RectangleHB(Constant.ENEMY_WIDTH, Constant.ENEMY_HEIGHT,
        initalPosition), Constant.ANGRY_ENEMY_SPEED),
        initalPosition.getX(), initalPosition.getY(), w);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createBombEnemy(final Point2D initialPosition, final World w) {
        return new EntityImpl(Type.ENEMY, 
        Optional.of(new EnemyInputComponent(new EnemyBombInput(w, initialPosition))),
        Optional.of(new HealthComponent(Constant.ENEMY_HP)), 
        Optional.of(new BombEnemyGraphicComponent(Constant.ENEMY_WIDTH, Constant.ENEMY_HEIGHT)), 
        new BombEnemyPhysicsComponent(new RectangleHB(Constant.ENEMY_WIDTH, Constant.ENEMY_HEIGHT,
        initialPosition), Constant.BOMB_ENEMY_SPEED),
        initialPosition.getX(), initialPosition.getY(), w);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entity createWall(final HitBox hitbox, final World w) {
        return new EntityImpl(Type.WALL,
        Optional.empty(),
        Optional.empty(),
        Optional.empty(),
        new WallPhysicsComponent(hitbox),
        hitbox.getCenter().getX(), hitbox.getCenter().getY(), w);
    }
}
