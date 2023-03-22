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
import it.unibo.smol.model.api.World;
import it.unibo.smol.view.GameMap;
import javafx.geometry.Point2D;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * class that includes all the inputs for the different type of enemy and manages the enemies movement.
 */
public class EnemyInput {

    private static final int DEFAULT_MIN_TIME_UP = 1500;
    private static final int DEFAULT_MAX_TIME_UP = 2500;

    private final int minTimeUp;
    private final int maxTimeUp;
    private int enemySection;
    private int enemyTimesSpawn;
    private final int maxTimesCanSpawn;
    private Point2D enemyPosition;
    private Point2D enemyNextPosition;
    private Timer enemyTimeUp;
    private final EnemyMoves enemyMovement;
    private final World world;
    private HitBox newPosHitBox;
    private boolean isNewPosViable;

    /**
     * inizialize the first position and and the first movements ogf the enemy.
     * @param maxTimesCanSpawn
     * @param world
     * @param initialEnemyPosition
     */
    public EnemyInput(final int maxTimesCanSpawn, final World world, final Point2D initialEnemyPosition) {

        this.minTimeUp = DEFAULT_MIN_TIME_UP;
        this.maxTimeUp = DEFAULT_MAX_TIME_UP;

        this.isNewPosViable = true;
        this.world = world;
        this.maxTimesCanSpawn = maxTimesCanSpawn;
        this.enemyPosition = initialEnemyPosition;
        this.enemyNextPosition = enemySetsPosition(new Random().nextInt(4)).get();
        this.enemyMovement = new EnemyMoves(enemyPosition, enemyNextPosition, this);
    }

    /**
     * finds a random position in the x axis.
     * @return a random x position
     */
    private double enemyRandX() {
        return GameMap.BORDER_WIDTH / 2  + Constant.ENEMY_WIDTH / 2
            + new Random().nextDouble(GameMap.MAP_WIDTH / 2 - Constant.ENEMY_WIDTH); 
    }

    /**
     * finds a random position in the y axis.
     * @return a random y position
     */
    private double enemyRandY() {
        return GameMap.BORDER_HEIGHT / 2 + Constant.ENEMY_HEIGHT / 2
            + new Random().nextDouble(GameMap.MAP_HEIGHT / 2 - Constant.ENEMY_HEIGHT);
    }

    /**
     * sets the next position where the enemy will go and checks if is valid.
     * @param choosenSegment
     * @return the next enemy position
     */
    private Optional<Point2D> enemySetsPosition(final int choosenSegment) {
        this.enemySection = choosenSegment;
        Optional<Point2D> temp;
        do {
            this.isNewPosViable = true;
            switch (choosenSegment) {
                case 0:
                    temp = Optional.of(new Point2D(enemyRandX(),
                        enemyRandY()));
                    break;
                case 1:
                    temp = Optional.of(new Point2D(enemyRandX() + (GameMap.MAP_WIDTH / 2),
                        enemyRandY()));
                    break;
                case 2:
                    temp = Optional.of(new Point2D(enemyRandX(),
                        enemyRandY() + (GameMap.MAP_HEIGHT / 2)));
                    break;
                case 3:
                    temp = Optional.of(new Point2D(enemyRandX() + (GameMap.MAP_WIDTH / 2),
                        enemyRandY() + (GameMap.MAP_HEIGHT / 2))); 
                    break;
                default:
                    temp = Optional.empty();
                    break;
            }
            newPosHitBox = new RectangleHB(Constant.ENEMY_WIDTH, Constant.ENEMY_HEIGHT, temp.get());
            this.world.getEntities().stream()
                .forEach(a -> {
                    if (newPosHitBox.isColliding(a.getPhysicsComp().getHitBox())) {
                        this.isNewPosViable = false;
                    }
                });
        } while (!isNewPosViable);
        return temp;
    }

    /**
     * sets the enemy position with the given param.
     * @param newPosition
     */
    public void setEnemyPosition(final Point2D newPosition) {
        this.enemyPosition = newPosition;
    }

    /**
     * notifies that the enemy is not underground anymore.
     */
    public void enemyIsUp() {
        enemyTimesSpawn++;
        enemyStaysUpTimer();
    }

    /**
     * search the next section where an enemy can go and the sets tha actual position in that section.
     * @return the next position of the enemy
     */
    protected Point2D enemySearchNextPos() {
        int temp = enemySection;
        while (temp == enemySection) {
            temp = new Random().nextInt(4);
        }
        return enemySetsPosition(temp).get();
    }

    /**
     * allowes the enemy to stay up for a period of time.
     */
    protected void enemyStaysUpTimer() {
        enemyTimeUp = new Timer(minTimeUp + new Random().nextInt(maxTimeUp - minTimeUp),
            new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
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

    /**
     * chooses a free plant for the enemy to go, if all plants are occupied
     * it goes in a random plant where there's already a enemy.
     */
    private void enemyGoesOnPlants() {
        final List<Entity> plants = world.occupiedPlants().keySet().stream().toList();
        Collections.shuffle(plants);
        if (plants.stream().count() == world.occupiedPlants().values()
            .stream()
            .filter(a -> a.equals(true))
            .count()) {
            this.enemyNextPosition = plants
                .stream()
                .findAny()
                .get()
                .getCurrentPosition();
        } else {
            final Entity choosenPlant = plants
                .stream()
                .filter(a -> world.occupiedPlants().get(a).equals(false))
                .findAny()
                .get();
            this.enemyNextPosition = choosenPlant.getCurrentPosition();
            world.setPlantOccupied(choosenPlant);
        }
    }

    /**
     * gets the min time a enemy can stay up.
     * @return minTimeUp
     */
    protected int getMinTimeUp() {
        return this.minTimeUp;
    }

    /**
     * gets the max time a enemy can stay up.
     * @return minTimeUp
     */
    protected int getMaxTimeUp() {
        return this.maxTimeUp;
    }

    /**
     * gets the numer of times the enemy has spawn.
     * @return enemyTimeSpawn
     */
    protected int getEnemyTimesSpawn() {
        return this.enemyTimesSpawn;
    }

    /**
     * gets the enemy Position.
     * @return enemyPosition
     */
    protected Point2D getEnemyPosition() {
        return this.enemyPosition;
    }

    /**
     * gets the next position to go, choosen by the enemy.
     * @return enemyNextPosition
     */
    protected Point2D getEnemyNextPosition() {
        return this.enemyNextPosition;
    }

    /**
     * sets the next position for the enemy to go.
     * @param enemyNextPosition
     */
    protected void setEnemyNextPosition(final Point2D enemyNextPosition) {
        this.enemyNextPosition = enemyNextPosition;
    }

    /**
     * gets the enemy movement that manages the movement an enemy does.
     * @return enemyMovement
     */
    protected EnemyMoves getEnemyMovement() {
        return this.enemyMovement;
    }

    /**
     * gets the Game State.
     * @return gs
     */
    protected World getWorld() {
        return this.world;
    }

    /**
     * says if the enemy is on a plant.
     * @return if the enmy is on a plant
     */
    public boolean isEnemyOnPlant() {
        return enemyTimesSpawn <= maxTimesCanSpawn;
    }

    /**
     * updates the position of the enemy.
     * @return empty if the enemy is up and a Point2D if the enemy is underground
     */
    public Optional<Point2D> enemyUpdatePos() {
        return enemyMovement.enemyMove();
    }

    /**
     * tells if the enemy is underground.
     * @return if the enemy is underground
     */
    public boolean isEnemyUnder() {
        return enemyMovement.isEnemyUnderground();
    }
}
