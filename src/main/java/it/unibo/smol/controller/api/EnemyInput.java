package it.unibo.smol.controller.api;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import it.unibo.smol.common.Constant;
import it.unibo.smol.common.HitBox;
import it.unibo.smol.common.hitbox.RectangleHB;
import it.unibo.smol.controller.input.EnemyMoves;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.GameState;
import it.unibo.smol.view.api.GameMap;
import it.unibo.smol.view.impl.GameMapImpl;
import javafx.geometry.Point2D;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class EnemyInput {

    private static final int DEFAULT_MIN_TIME_UP = 1500;
    private static final int DEFAULT_MAX_TIME_UP = 2500;

    private final GameMap mapDimension;
    protected int minTimeUp;
    protected int maxTimeUp;
    private int enemySection;
    protected int enemyTimesSpawn;
    private int maxTimesCanSpawn;
    protected Point2D enemyPosition;
    protected Point2D enemyNextPosition;
    protected Timer enemyTimeUp;
    protected EnemyMoves enemyMovement;
    protected GameState gs;
    private HitBox newPosHitBox;
    private boolean isNewPosViable;

    public EnemyInput(final int maxTimesCanSpawn, final GameState gs) {

        this.minTimeUp = DEFAULT_MIN_TIME_UP;
        this.maxTimeUp = DEFAULT_MAX_TIME_UP;

        this.isNewPosViable = true;
        this.gs = gs;
        this.maxTimesCanSpawn = maxTimesCanSpawn;
        this.mapDimension = new GameMapImpl();
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

    private double enemyRandX() {
        return (mapDimension.getBorderWidth() / 2)  + (Constant.ENEMY_WIDTH / 2) +
            (new Random().nextDouble((mapDimension.getMapWidth() / 2) - Constant.ENEMY_WIDTH)); 
    }

    private double enemyRandY() {
        return (mapDimension.getBorderHeight() / 2) + (Constant.ENEMY_HEIGHT / 2) +
            (new Random().nextDouble((mapDimension.getMapHeight() / 2) - Constant.ENEMY_HEIGHT));
    }

    private Point2D enemySetsPosition(final int choosenSegment) {
        this.enemySection = choosenSegment;
        Optional<Point2D> temp;
        do {
            this.isNewPosViable = true;
            switch (choosenSegment) {
                case 0:
                    temp = Optional.of(new Point2D(enemyRandX(),
                        enemyRandY()));
                case 1:
                    temp = Optional.of( new Point2D(enemyRandX() + (mapDimension.getMapWidth() / 2),
                        enemyRandY()));
                case 2:
                    temp = Optional.of( new Point2D(enemyRandX(),
                        enemyRandY() + (mapDimension.getMapHeight() / 2)));
                case 3:   
                    temp = Optional.of( new Point2D(enemyRandX() + (mapDimension.getMapWidth() / 2),
                        enemyRandY() + (mapDimension.getMapHeight() / 2))); 
                default:
                    temp = Optional.empty();
                    break;
            }
            newPosHitBox = new RectangleHB(Constant.ENEMY_WIDTH, Constant.ENEMY_HEIGHT, temp.get());
            gs.getWorld().getEntities().stream()
                .forEach(a -> {
                    if (newPosHitBox.isColliding(a.getPhysicsComp().getHitBox())) {
                        this.isNewPosViable = false;
                    }
                });
        } while (!isNewPosViable);
        return temp.get();
    }

    public void setEnemyPosition(final Point2D newPosition) {
        this.enemyPosition = newPosition;
    }

    public void enemyIsUp() {
        enemyTimesSpawn++;
        enemyStaysUpTimer();
    }

    public Point2D enemySearchNextPos() {
        int temp = enemySection;
        while (temp == enemySection) {
            temp = new Random().nextInt(4);
        }
        return enemySetsPosition(temp);
    }

    protected void enemyStaysUpTimer() {
        enemyTimeUp = new Timer(minTimeUp + new Random().nextInt(maxTimeUp - minTimeUp),
            new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (enemyTimesSpawn < maxTimesCanSpawn) {
                        enemyNextPosition = enemySearchNextPos();
                    } else if (enemyTimesSpawn <= maxTimesCanSpawn) {
                        enemyGoesOnPlants();
                    }
                    enemyMovement.positionUpdate(enemyPosition, enemyNextPosition);
                    enemyTimeUp.stop();
                }  
            });
        enemyTimeUp.start();
    }

    /*
     * da fare enemyGoesOnPlants, guarda QuadratoGUI.
     */
    private void enemyGoesOnPlants() {
        List<Entity> plants = gs.occupiedPlants().keySet().stream().toList();
        Collections.shuffle(plants);
        if (plants.stream().count() == gs.occupiedPlants().values()
            .stream()
            .filter(a -> a.equals(true))
            .count()) {
            this.enemyNextPosition = plants
                .stream()
                .findAny()
                .get()
                .getCurrentPosition();
        } else {
            Entity choosenPlant = plants
                .stream()
                .filter(a -> gs.occupiedPlants().get(a).equals(false))
                .findAny()
                .get();
            this.enemyNextPosition = choosenPlant.getCurrentPosition();
            gs.setPlantOccupied(choosenPlant);
        }
    }

    public boolean isEnemyOnPlant() {
        return enemyTimesSpawn <= maxTimesCanSpawn;
    }

    public Optional<Point2D> enemyUpdatePos() {
        return enemyMovement.enemyMove();
    }

    public boolean isEnemyUnder() {
        return enemyMovement.isEnemyUnderground();
    }
    
}
