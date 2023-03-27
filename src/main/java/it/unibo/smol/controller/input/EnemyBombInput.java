package it.unibo.smol.controller.input;

import it.unibo.smol.common.Constant;
import it.unibo.smol.controller.api.EnemyInput;

import it.unibo.smol.model.api.World;
import javafx.geometry.Point2D;

/**
 * creates an enemy with a bomb.
 */
public class EnemyBombInput extends EnemyInput {

    /**
     * gives the enemy Max times spawn and the given game state.
     * @param world
     * @param initialEnemyPosition
     */
    public EnemyBombInput(final World world, final Point2D initialEnemyPosition) {
        super(Constant.BOMB_ENEMY_MAX_SPAWNS, world, initialEnemyPosition);
    }

    /**
     * similar to the method in EnemyInput class, but because it is a bomb enemy, it dies
     * when it should go on plants.
     */
    @Override
    protected Runnable enemyStaysUp() {
        return new Runnable() {

            @Override
            public void run() {
                if (getEnemyTimesSpawn() < Constant.BOMB_ENEMY_MAX_SPAWNS) {
                    setEnemyNextPosition(enemySearchNextPos());
                } else {
                    getWorld().remove(getEntity());
                }
                getEnemyMovement().positionUpdate(getEnemyPosition(), getEnemyNextPosition());
            }

        };
    }
}
