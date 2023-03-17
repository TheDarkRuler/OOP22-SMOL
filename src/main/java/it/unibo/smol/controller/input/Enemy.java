package it.unibo.smol.controller.input;

import java.util.Random;

import it.unibo.smol.view.api.GameMap;
import javafx.geometry.Point2D;
import javax.swing.Timer;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Enemy {

    private static final int DEFAULT_MIN_TIME_UP = 1500;
    private static final int DEFAULT_MAX_TIME_UP = 2500;
    private static final int DEFAULT_MAX_TIME_CAN_SPAWN = 2;

    private final GameMap mapDimension;
    private int minTimeUp;
    private int maxTimeUp;
    private int enemySection;
    private int enemyTimesSpawn;
    private int maxTimesCanSpawn;
    private Point2D enemyPosition;
    private Point2D enemyNextPosition;
    private Timer enemyTimeUp;
    private EnemyMoves enemyMovement;

    public Enemy(final GameMap mapDimension) {

        this.minTimeUp = DEFAULT_MIN_TIME_UP;
        this.maxTimeUp = DEFAULT_MAX_TIME_UP;
        this.maxTimesCanSpawn = DEFAULT_MAX_TIME_CAN_SPAWN;

        this.mapDimension = mapDimension;
        this.enemyPosition = initialEnemyPosition();
        this.enemyNextPosition = enemySetsPosition(new Random().nextInt(4));
        this.enemyMovement = new EnemyMoves(enemyPosition, enemyNextPosition, this);
    }

    private Point2D initialEnemyPosition() {
        if (new Random().nextBoolean()) {
            return new Point2D(randomBetweenTwo(mapDimension.getBorderWidth() / 2,
                ((mapDimension.getBorderWidth() / 2) + mapDimension.getMapWidth())),
                (mapDimension.getBorderHeight() / 2) + new Random()
                    .nextDouble(mapDimension.getMapHeight() - (mapDimension.getBorderHeight() / 2)));
        } else {
            return new Point2D((mapDimension.getBorderWidth() / 2) + new Random()
            .nextDouble(mapDimension.getMapWidth() - (mapDimension.getBorderWidth() / 2)),
                randomBetweenTwo(mapDimension.getBorderHeight() / 2,
                ((mapDimension.getBorderHeight() / 2) + mapDimension.getMapHeight())));
        }
    }

    private double randomBetweenTwo(final double first, final double second) {
        return new Random().nextBoolean() ? first : second;
    }

    private Point2D enemySetsPosition(final int choosenSegment) {
        this.enemySection = choosenSegment;
        switch (choosenSegment) {
            case 0:
                return new Point2D(enemyRandX(),
                    enemyRandY());
            case 1:
                return new Point2D(enemyRandX() + (mapDimension.getMapWidth() / 2),
                    enemyRandY());
            case 2:
                return new Point2D(enemyRandX(),
                    enemyRandY() + (mapDimension.getMapHeight() / 2));
            case 3:   
                return new Point2D(enemyRandX() + (mapDimension.getMapWidth() / 2),
                    enemyRandY() + (mapDimension.getMapHeight() / 2)); 
            default:
                return null;
        }
    }

    private double enemyRandX() {
        return (mapDimension.getBorderWidth() / 2)  /*+ Enemy size / 2 */+
            (new Random().nextDouble(mapDimension.getMapWidth() / 2 /*- enemy size */)); 
    }

    private double enemyRandY() {
        return (mapDimension.getBorderHeight() / 2)  /*+ Enemy size / 2 */+
            (new Random().nextDouble(mapDimension.getMapHeight() / 2));
    }

    public void setEnemyPosition(final Point2D newPosition) {
        this.enemyPosition = newPosition;
    }

    public void enemyIsUp() {
        enemyStaysUpTimer();
    }

    public Point2D enemySearchNextPos() {
        int temp = enemySection;
        while (temp == enemySection) {
            temp = new Random().nextInt(4);
        }
        enemyTimesSpawn++;
        return enemySetsPosition(temp);
    }

    private void enemyStaysUpTimer() {
        enemyTimeUp = new Timer(minTimeUp + new Random().nextInt(maxTimeUp - minTimeUp),
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (enemyTimesSpawn < maxTimesCanSpawn) {
                        enemyNextPosition = enemySearchNextPos();
                        enemyMovement.positionUpdate(enemyPosition, enemyNextPosition);
                    } else if (enemyTimesSpawn <= maxTimesCanSpawn) {
                        //enemyGoesOnPlants();
                    }
                    enemyTimeUp.stop();
                }  
            });
        enemyTimeUp.start();
    }
    /*
     * da fare enemyGoesOnPlants, guarda QuadratoGUI.
     */
    /*private void enemyGoesOnPlants() {
        Collections.shuffle(plants);
        if (plants.stream().count() == plants.stream().filter(a -> a.getPhysicsComp().))
    }*/

    public boolean isEnemyOnPlant() {
        return enemyTimesSpawn <= maxTimesCanSpawn;
    } 
}
