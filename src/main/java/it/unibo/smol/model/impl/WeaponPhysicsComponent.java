package it.unibo.smol.model.impl;

import it.unibo.smol.common.HitBox;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.PhysicsComponent;
import javafx.geometry.Point2D;

/**
 * The implementation of the {@link PhysicsComponent} rappresenting the Player behaviour.
 */
public class WeaponPhysicsComponent extends PhysicsComponent {

    /**
     * Constructor inherited by the super-class {@link PhysicsComponent}.
     * @param movementSpeed : See the super-Constructor
     * @param hitBox : See the super-Constructor
     */
    public WeaponPhysicsComponent(final Double movementSpeed, final HitBox hitBox) {
        super(movementSpeed, hitBox);
    }

    /**
     * This method receive a {@link Point2D} and translate it into actual movement.
     * @param move : the coordinate given
     */
    @Override
    public <A> void receiveMovement(final A move) {
        Point2D move1;
        if (move instanceof Point2D) {
            move1 = (Point2D) move;
        } else {
            throw new IllegalArgumentException("Point2D type expected");
        }

        super.setX(move1.getX() - super.getEntity().getCurrentX());
        super.setY(move1.getY() - super.getEntity().getCurrentY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void collisonEvent(final Entity entityCollided) {
        //This entity don't have any collisionEffect on himself 
    }
}
