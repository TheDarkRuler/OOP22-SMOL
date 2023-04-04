package it.unibo.smol.model.impl.physicscomponent;

import java.util.Optional;
import it.unibo.smol.common.Constant;
import it.unibo.smol.common.Directions;
import it.unibo.smol.common.HitBox;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.PhysicsComponent;
import javafx.geometry.Point2D;
/**
 * The implementation of the {@link PhysicsComponent} rappresenting a wall of the map.
 */
public class EmptyPhysicsComponent extends PhysicsComponent {

    /**
     * Constructor inherited by the super-class {@link PhysicsComponent}.
     * @param hitBox : See the super-Constructor
     */
    public EmptyPhysicsComponent(final HitBox hitBox) {
        super(Constant.HEALTH_MOVSPD, Optional.of(hitBox));
    }

    @Override
    public void receiveMovement(final Directions move) {
        //This component doesn't use this method
    }

    @Override
    public void receiveMovement(final Point2D move) {
        //This component doesn't use this method
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void collisonEvent(final Entity entityCollided) {
        //This component doesn't use this method
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhysicsComponent makeCopy() {
        return this;
    }
}
