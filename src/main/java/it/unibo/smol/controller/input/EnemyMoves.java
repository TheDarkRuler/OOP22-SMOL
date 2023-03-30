package it.unibo.smol.controller.input;

import java.util.Optional;
import it.unibo.smol.controller.api.EnemyInput;
import javafx.geometry.Point2D;

/**
 * Class that gets the position of the enemy to move and the position in which it has to move
 * and actually moves it.
 */
public class EnemyMoves {

    private Point2D from;
    private Point2D to;
    private double moveX;
    private double moveY;
    private final double movSpeed;
    private final EnemyInput enemyToMove;
    private boolean enemyIsUnder;

    /**
     * sets the movement variable and enemyisUnder true so it notifies that the enemy can move.
     * @param from
     * @param to
     * @param enemyToMove
     * @param movSpeed
     */
    public EnemyMoves(final Point2D from, final Point2D to, final EnemyInput enemyToMove, final double movSpeed) {
        this.from = from;
        this.to = to;
        this.movSpeed = movSpeed;
        this.enemyToMove = enemyToMove;
        setMovementVariable();
        this.enemyIsUnder = true;
    }

    /**
     * changes the initial position until it doesn't get as close as possible
     * to the next position.
     * @return an empty if the enemy is not underground and the progressive enemy position if it is
     */
    public Optional<Point2D> enemyMove() {
        if (this.enemyIsUnder) {
            if (this.from.distance(this.to) > movSpeed) {
                this.from = this.from.add(this.from.getX() < this.to.getX() ? this.moveX * movSpeed : -this.moveX * movSpeed, 0);
                this.from = this.from.add(0, this.from.getY() < this.to.getY() ? this.moveY * movSpeed : -this.moveY * movSpeed);
                enemyToMove.setEnemyPosition(from);
            } else {
                notifyEnemyHasArrived();
            }
        } else {
            return Optional.empty();
        }
        return Optional.of(from);
    }

    /**
     * notifies that the enemy has arrived to the next spawn position.
     */
    private void notifyEnemyHasArrived() {
        this.enemyIsUnder = false;
        enemyToMove.enemyIsUp();
    }

    /**
     * sets the movement variable that the enemy uses to move.
     */
    private void setMovementVariable() {
        this.moveX = getMovementSegments(Math.abs(from.getX() - to.getX()));
        this.moveY = getMovementSegments(Math.abs(from.getY() - to.getY()));
    }

    /**
     * gets the segment that is used to move the enemy.
     * @param temp
     * @return a double used by the enemy to move in the axis
     */
    private double getMovementSegments(final double temp) {
        return temp / Math.max(Math.abs(from.getX() - to.getX()), Math.abs(from.getY() - to.getY()));
    }

    /**
     * update the positions from and to, and notify that the enemy is underground and can move.
     * @param from
     * @param to
     */
    public void positionUpdate(final Point2D from, final Point2D to) {
        this.from = from;
        this.to = to;
        setMovementVariable();
        this.enemyIsUnder = true;
    }

    /**
     * tells if the enemy is underground.
     * @return if the enemy is underground
     */
    public boolean isEnemyUnderground() {
        return enemyIsUnder;
    }
}
