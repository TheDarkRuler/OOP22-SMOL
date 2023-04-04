package it.unibo.smol.model.impl.physicscomponent;

import java.util.Optional;
import it.unibo.smol.common.Constant;
import it.unibo.smol.common.Directions;
import it.unibo.smol.common.HitBox;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.PhysicsComponent;
import javafx.geometry.Point2D;
/**
 * The implementation of the {@link PhysicsComponent} rappresenting the Enemy behaviour.
 */
public class LifePlantsPhysicsComponent extends PhysicsComponent {

    /**
     * Constructor inherited by the super-class {@link PhysicsComponent}.
     * @param hitBox : See the super-Constructor
     */
    public LifePlantsPhysicsComponent(final HitBox hitBox) {
        super(Constant.HEALTH_MOVSPD, Optional.of(hitBox));
    }

    /**
     * Whenever this entity collide with a Player or Enemy {@link Type} entity, it takes 1 damage.
     */
    @Override
    protected void collisonEvent(final Entity entityCollided) {
        if (entityCollided.getType() == Type.ENEMY) {
            super.getEntity().orElseThrow().getHealthComp().orElseThrow().setHealth(Constant.ENEMY_DMG);
        } else if (entityCollided.getType() == Type.PLAYER) {
            super.getEntity().orElseThrow().getHealthComp().orElseThrow().setHealth(Constant.PLAYER_DMG);
        }

        if (super.getEntity().orElseThrow().getHealthComp().orElseThrow().isDead() && entityCollided.getType() == Type.ENEMY) {
            entityCollided.getWorld().orElseThrow().remove(entityCollided);
        }
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
