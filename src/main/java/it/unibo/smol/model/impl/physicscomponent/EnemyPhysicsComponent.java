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
 * The implementation of the {@link PhysicsComponent} rappresenting the Enemy behaviour.
 */
public class EnemyPhysicsComponent extends PhysicsComponent {

    /**
     * Constructor inherited by the super-class {@link PhysicsComponent}.
     * @param hitBox : See the super-Constructor
     * @param movSpeed
     */
    public EnemyPhysicsComponent(final HitBox hitBox, final double movSpeed) {
        super(movSpeed, Optional.of(hitBox));
    }

    /**
     * Whenever this entity collide with a Weapon {@link Type} entity, it takes 1 damage.
     */
    @Override
    protected void collisonEvent(final Entity entityCollided) {
        if (entityCollided.getType() == Type.WEAPON) {
            super.getEntity().orElseThrow().getHealthComp().orElseThrow().setHealth(Constant.WEAPON_DMG);
            if (super.getEntity().orElseThrow().getHealthComp().orElseThrow().isDead()) {
                super.getEntity().orElseThrow().getWorld().orElseThrow().incScore(Constant.ENEMY_SCORE);
            }
        }

        if (entityCollided.getType() == Type.PLAYER) {
            entityCollided.setX(entityCollided.getCurrentX() - entityCollided.getPhysicsComp().orElseThrow().getX());
            entityCollided.setY(entityCollided.getCurrentY() - entityCollided.getPhysicsComp().orElseThrow().getY());
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
    public void receiveMovement(final Point2D move, final World world) {
        super.setX(move.getX());
        super.setY(move.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhysicsComponent makeCopy() {
        return this;
    }
}
