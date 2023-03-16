package it.unibo.smol.controller.input;

import javafx.geometry.Point2D;

public class EnemyMoves {

    private Point2D from;
    private Point2D to;
    private double moveX;
    private double moveY;
    private Enemy enemyToMove;
    private boolean enemyIsUnder;

    public EnemyMoves(final Point2D from, final Point2D to, final Enemy enemyToMove) {
        this.from = from;
        this.to = to;
        this.enemyToMove = enemyToMove;

        setMovementVariable();
        enemyIsUnder = true;
    }

    public void enemyMovement() {
        if (enemyIsUnder){
            if (from.distance(to) > 1/*mettere la velocita della talpa qua*/) {
                from = from.add(from.getX() < to.getX() ? moveX : -moveX, 0);
                from = from.add(0, from.getY() < to.getY() ? moveY : -moveY);
            } else {
                notifyEnemyHasArrived();
            }
        }
    }

    private void notifyEnemyHasArrived() {
        this.enemyIsUnder = false;
        enemyToMove.enemyIsUp();
    }

    private void setMovementVariable() {
        this.moveX = getMovementSegments(Math.abs(from.getX() - to.getX()));
        this.moveY = getMovementSegments(Math.abs(from.getX() - to.getX()));
    }

    private double getMovementSegments(final double temp) {
        return temp / Math.max(Math.abs(from.getX() - to.getX()), Math.abs(from.getX() - to.getX()));
    }

    public void positionUpdate(final Point2D from, final Point2D to) {
        this.from = from;
        this.to = to;
        setMovementVariable();
    }

    /* positionUpdated in modo diverso, (guarda QuadratoGUI) */
    
}