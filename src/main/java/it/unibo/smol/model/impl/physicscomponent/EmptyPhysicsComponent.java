package it.unibo.smol.model.impl.physicscomponent;

import java.util.Optional;
import it.unibo.smol.common.Constant;
import it.unibo.smol.common.HitBox;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.PhysicsComponent;
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
