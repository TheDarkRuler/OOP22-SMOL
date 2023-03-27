package it.unibo.smol.controller.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import it.unibo.smol.common.Constant;
import it.unibo.smol.common.HitBox;
import it.unibo.smol.common.hitbox.RectangleHB;
import it.unibo.smol.controller.input.EnemyMoves;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.World;
import it.unibo.smol.view.GameMap;
import javafx.geometry.Point2D;

/**
 * class that includes all the inputs for the different type of enemy and manages the enemies movement.
 */
public class EnemyInput {

    private final int minTimeUp;
    private final int maxTimeUp;
    private int enemySection;
    private int enemyTimesSpawn;
    private final int maxTimesCanSpawn;
    private Point2D enemyPosition;
    private Point2D enemyNextPosition;
    private final EnemyMoves enemyMovement;
    private final World world;
    private HitBox newPosHitBox;
    private boolean isNewPosViable;
    private final Random rand;
    private Entity entity;
    private final ScheduledExecutorService enemyStaysUpTime;

    /**
     * inizialize the first position and and the first movements ogf the enemy.
     * @param maxTimesCanSpawn
     * @param world
     * @param initialEnemyPosition
     */
    public EnemyInput(final int maxTimesCanSpawn, final World world,
        final Point2D initialEnemyPosition) {

        this.minTimeUp = Constant.DEFAULT_MIN_TIME_UP;
        this.maxTimeUp = Constant.DEFAULT_MAX_TIME_UP;

        this.isNewPosViable = true;
        this.enemyStaysUpTime = Executors.newSingleThreadScheduledExecutor();
        this.rand = new Random();
        this.world = world;
        this.maxTimesCanSpawn = maxTimesCanSpawn;
        this.enemyPosition = initialEnemyPosition;
        this.enemyNextPosition = enemySetsPosition(rand.nextInt(4)).get();
        this.enemyMovement = new EnemyMoves(enemyPosition, enemyNextPosition, this);
    }

    /**
     * sets the entity to move.
     * @param entity
     */
    public void setEntity(final Entity entity) {
        this.entity = entity;
    }

    /**
     * gets the entityt to move.
     * @return entity
     */
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * finds a random position in the x axis.
     * @return a random x position
     */
    private double enemyRandX() {
        return GameMap.BORDER_WIDTH / 2  + Constant.ENEMY_WIDTH / 2
            + rand.nextDouble(GameMap.MAP_WIDTH / 2 - Constant.ENEMY_WIDTH); 
    }

    /**
     * finds a random position in the y axis.
     * @return a random y position
     */
    private double enemyRandY() {
        return GameMap.BORDER_HEIGHT / 2 + Constant.ENEMY_HEIGHT / 2
            + rand.nextDouble(GameMap.MAP_HEIGHT / 2 - Constant.ENEMY_HEIGHT);
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
        if (enemyTimesSpawn < maxTimesCanSpawn) {
            //enemyStaysUpTimer();
            this.enemyStaysUpTime.schedule(enemyStaysUp(), minTimeUp + rand.nextInt(maxTimeUp - minTimeUp),
                TimeUnit.MILLISECONDS);
            enemyTimesSpawn++;
        }
    }

    /**
     * allowes the enemy to stay up for a period of time.
     */
    protected Runnable enemyStaysUp() {
        return new Runnable() {

            @Override
            public void run() {
                if (enemyTimesSpawn < maxTimesCanSpawn) {
                    enemyNextPosition = enemySearchNextPos();
                } else if (enemyTimesSpawn <= maxTimesCanSpawn) {
                    enemyNextPosition = enemyGoesOnPlants();
                    enemyTimesSpawn++;
                }
                enemyMovement.positionUpdate(enemyPosition, enemyNextPosition);
            }

        };
    }

    /**
     * search the next section where an enemy can go and the sets tha actual position in that section.
     * @return the next position of the enemy
     */
    protected Point2D enemySearchNextPos() {
        int temp = enemySection;
        while (temp == enemySection) {
            temp = rand.nextInt(4);
        }
        return enemySetsPosition(temp).get();
    }

    /**
     * chooses a free plant for the enemy to go, if all plants are occupied
     * it goes in a random plant where there's already a enemy.
     */
    private Point2D enemyGoesOnPlants() {
        final Point2D temp;
        final List<Entity> plants = new ArrayList<>(world.occupiedPlants().keySet().stream().toList());
        Collections.shuffle(plants);
        if (plants.stream().count() == world.occupiedPlants().values()
            .stream()
            .filter(a -> a.equals(true))
            .count()) {
            temp = plants
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
            temp = choosenPlant.getCurrentPosition();
            world.setPlantOccupied(choosenPlant);

        }
        return temp;
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
     * gets the World.
     * @return world
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
