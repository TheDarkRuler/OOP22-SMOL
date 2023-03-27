package it.unibo.smol.controller.input;

import it.unibo.smol.controller.api.EnemyInput;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import it.unibo.smol.model.api.World;
import javafx.geometry.Point2D;
import javax.swing.Timer;

/**
 * creates an enemy with a bomb.
 */
public class EnemyBombInput extends EnemyInput {

    private static final int BOMB_ENEMY_MAX_SPAWNS = 2;

    private static Timer enemyTimeUp;

    /**
     * gives the enemy Max times spawn and the given game state.
     * @param world
     * @param initialEnemyPosition
     */
    public EnemyBombInput(final World world, final Point2D initialEnemyPosition) {
        super(BOMB_ENEMY_MAX_SPAWNS, world, initialEnemyPosition);
    }

    /**
     * similar to the Timer in EnemyInput class, but because it is a bomb enemy, it dies
     * when it should go on plants.
     */
    @Override
    protected void enemyStaysUpTimer() {
        enemyTimeUp = new Timer(getMinTimeUp() + new Random().nextInt(getMaxTimeUp() - getMinTimeUp()),
            new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    if (getEnemyTimesSpawn() < BOMB_ENEMY_MAX_SPAWNS) {
                        setEnemyNextPosition(enemySearchNextPos());
                        getEnemyMovement().positionUpdate(getEnemyPosition(), getEnemyNextPosition());
                    } else {
                        removeBomb();
                    }
                    enemyTimeUp.stop();
                }
            });
        enemyTimeUp.start();
    }

    /**
     * removes the bomb from the world.
     */
    private void removeBomb() {
        getWorld().remove(getWorld().getMoles().stream()
            .filter(x -> x.getInputComp().get().getEnemyInput().equals(this)).findAny().get());
    }
}
