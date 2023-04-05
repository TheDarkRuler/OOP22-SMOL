package it.unibo.smol.controller.impl;

import java.util.Optional;

import it.unibo.smol.common.Constant;
import it.unibo.smol.controller.api.EnemyInput;
import it.unibo.smol.controller.api.EnemyInputFactory;
import it.unibo.smol.controller.input.EnemyBombInput;
import it.unibo.smol.model.api.World;
import javafx.geometry.Point2D;

/**
 * creates the enemy inputs giving some different constants for each type of enemy.
 */
public class EnemyInputFactoryImpl implements EnemyInputFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyInput createAngryEnemyInput(final Optional<World> world, final Point2D initialPosition) {
        return new EnemyInput(Constant.ANGRY_ENEMY_MAX_SPAWNS, world, initialPosition, Constant.ANGRY_ENEMY_SPEED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyInput createBasicEnemyInput(final Optional<World> world, final Point2D initialPosition) {
        return new EnemyInput(Constant.BASIC_ENEMY_MAX_SPAWNS, world, initialPosition, Constant.BASIC_ENEMY_SPEED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyInput createHelmetEnemyInput(final Optional<World> world, final Point2D initialPosition) {
        return new EnemyInput(Constant.BOMB_ENEMY_MAX_SPAWNS, world, initialPosition, Constant.HELMET_ENEMY_SPEED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnemyInput createBombEnemyInput(final Optional<World> world, final Point2D initialPosition) {
        return new EnemyBombInput(world, initialPosition);
    }
}
