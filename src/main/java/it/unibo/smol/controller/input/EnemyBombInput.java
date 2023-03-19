package it.unibo.smol.controller.input;

import it.unibo.smol.controller.api.EnemyInput;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import it.unibo.smol.model.api.GameState;
import javax.swing.Timer;

public class EnemyBombInput extends EnemyInput {

    private static final int BOMB_ENEMY_MAX_SPAWNS = 2;

    public EnemyBombInput(final GameState gs) {
        super(BOMB_ENEMY_MAX_SPAWNS, gs);
    }

    @Override
    protected void enemyStaysUpTimer() {
        enemyTimeUp = new Timer(minTimeUp + new Random().nextInt(maxTimeUp - minTimeUp),
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (enemyTimesSpawn < BOMB_ENEMY_MAX_SPAWNS) {
                        enemyNextPosition = enemySearchNextPos();
                        enemyMovement.positionUpdate(enemyPosition, enemyNextPosition);
                    } else {
                        gs.notifyDeath();
                    }
                    enemyTimeUp.stop();
                }  
            });
        enemyTimeUp.start();
    }
    
}
