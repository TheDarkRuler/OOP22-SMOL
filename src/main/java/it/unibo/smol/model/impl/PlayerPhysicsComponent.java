package it.unibo.smol.model.impl;

import it.unibo.smol.common.Directions;
import it.unibo.smol.common.HitBox;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.PhysicsComponent;
/**
 * The implementation of the {@link PhysicsComponent} rappresenting the Player behaviour.
 */
public class PlayerPhysicsComponent extends PhysicsComponent {

    /**
     * Constructor inherited by the super-class {@link PhysicsComponent}.
     * @param movementSpeed : See the super-Constructor
     * @param hitBox : See the super-Constructor
     */
    public PlayerPhysicsComponent(final Double movementSpeed, final HitBox hitBox) {
        super(movementSpeed, hitBox);
    }

    /**
     * This method receive a {@link Directions} and translate it into actual movement.
     * @param move : the direction given
     */
    @Override
    public <A> void receiveMovement(final A move) {
        Directions move1;

        if (move instanceof Directions) {
            move1 = (Directions) move;
        } else {
            throw new IllegalArgumentException("Direction type expected");
        }

        switch (move1) {
            case UP:
                super.setY(super.getMovementSpeed());
                break;
            case DOWN:
            super.setY(-super.getMovementSpeed());
                break;
            case LEFT:
            super.setX(-super.getMovementSpeed());
                break;
            case RIGHT:
            super.setY(super.getMovementSpeed());
                break;
            case STAY_X:
            super.setX(0);
                break;
            case STAY_Y:
            super.setY(0);
                break;
            default:
                break;
        }
    }

    /**
     * Whenever this entity collide with a Enemy {@link Type} entity, it takes knockBack.
     */
    @Override
    protected void collisonEvent(final Entity entityCollided) {
        if (entityCollided.getType() == Type.ENEMY) {
            super.setX(-getX());
            super.setY(-getY());
        }
    } 
}
