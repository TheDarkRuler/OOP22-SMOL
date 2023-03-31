package it.unibo.smol.model.impl.physicscomponent;

import java.util.Optional;
import it.unibo.smol.common.Constant;
import it.unibo.smol.common.Directions;
import it.unibo.smol.common.HitBox;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.PhysicsComponent;
import it.unibo.smol.model.api.World;
import javafx.geometry.Point2D;
/**
 * The implementation of the {@link PhysicsComponent} rappresenting a wall of the map.
 */
public class WallPhysicsComponent extends PhysicsComponent {

    /**
     * Constructor inherited by the super-class {@link PhysicsComponent}.
     * @param hitBox : See the super-Constructor
     */
    public WallPhysicsComponent(final HitBox hitBox) {
        super(Constant.HEALTH_MOVSPD, Optional.of(hitBox));
    }

    @Override
    public void receiveMovement(final Directions move) {
        //This component doesn't use this method
    }

    @Override
    public void receiveMovement(final Point2D move, final World world) {
        //This component doesn't use this method
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void collisonEvent(final Entity entityCollided) {
        if (entityCollided.getType() == Type.PLAYER) {
            entityCollided.setX(entityCollided.getCurrentX() - entityCollided.getPhysicsComp().orElseThrow().getX());
            entityCollided.setY(entityCollided.getCurrentY() - entityCollided.getPhysicsComp().orElseThrow().getY());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhysicsComponent makeCopy() {
        return this;
    }
}
