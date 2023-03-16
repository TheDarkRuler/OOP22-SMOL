package it.unibo.smol.model.impl;

import it.unibo.smol.model.api.World;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

public class EnemyCreation {

    private static final double DEF_RATE_BASIC = 0.9;
    private static final double DEF_RATE_ELMET = 0.1;
    private static final double DEF_RATE_ANGRY = -0.1;
    private static final int NUM_DIFF_ENEM = 3;

    private final World world;

    private int minTimeEnemySpawn;
    private int maxTimeEnemySpawn;
    private List<Double> weightList;
    private Timer enemyCreationTimer;

    public EnemyCreation(final World world) {
        this.world = world;

        this.minTimeEnemySpawn = 3000;
        this.maxTimeEnemySpawn = 4000;
        this.weightList = new ArrayList<>(List.of(DEF_RATE_BASIC, DEF_RATE_ELMET, DEF_RATE_ANGRY));
        creationTimer();
    }

    private void creationTimer() {
        this.enemyCreationTimer = new Timer(minTimeEnemySpawn + new Random().nextInt(maxTimeEnemySpawn - minTimeEnemySpawn),
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Collections.sort(weightList);
                    Double temp = Math.random();
                    for (int i = 0; i < NUM_DIFF_ENEM; i++) {
                        if (temp < weightList.get(i)) {
                        }
                    }
                }
                
            });
        this.enemyCreationTimer.start();
    }
    
}
