package it.unibo.smol.model.impl.physicsComponent;

import it.unibo.smol.common.Directions;
import it.unibo.smol.common.HitBox;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.PhysicsComponent;
import javafx.geometry.Point2D;

/**
 * The implementation of the {@link PhysicsComponent} rappresenting the Enemy behaviour.
 */
public class EnemyPhysicsComponent extends PhysicsComponent {

    /**
     * Constructor inherited by the super-class {@link PhysicsComponent}.
     * @param movementSpeed : See the super-Constructor
     * @param hitBox : See the super-Constructor
     */
    public EnemyPhysicsComponent(final HitBox hitBox) {
        super(5.0, hitBox);
    }

    /**
     * Whenever this entity collide with a Weapon {@link Type} entity, it takes 1 damage.
     */
    @Override
    protected void collisonEvent(final Entity entityCollided) {
        if (entityCollided.getType() == Type.WEAPON) {
            super.getEntity().getHealthComp().orElseThrow().setHealth(-1);
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
        super.setX(move.getX() - super.getEntity().getCurrentX());
        super.setY(move.getY() - super.getEntity().getCurrentY());
    }
}
