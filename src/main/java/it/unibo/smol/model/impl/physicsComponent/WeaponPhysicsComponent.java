package it.unibo.smol.model.impl.physicsComponent;

import it.unibo.smol.common.Directions;
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
    public WeaponPhysicsComponent(final HitBox hitBox) {
        super(0.0, hitBox);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void collisonEvent(final Entity entityCollided) {
        //This entity don't have any collisionEffect on himself 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveMovement(final Directions move) {
        //This component doesn't use this method
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveMovement(final Point2D move) {
        super.setX(move.getX() - super.getEntity().getCurrentX());
        super.setY(move.getY() - super.getEntity().getCurrentY());
    }
}
