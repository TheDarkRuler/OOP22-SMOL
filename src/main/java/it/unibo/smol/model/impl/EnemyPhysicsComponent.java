package it.unibo.smol.model.impl;

import it.unibo.smol.common.Directions;
import it.unibo.smol.common.HitBox;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.PhysicsComponent;

public class EnemyPhysicsComponent extends PhysicsComponent {

    public EnemyPhysicsComponent(Double movementSpeed, HitBox hitBox) {
        super(movementSpeed, hitBox);
    }

    @Override
    public <A> void receiveMovement(A move) {
        Directions move1;

        if (move instanceof Directions) {
            move1 = (Directions) move;
        } else {
            throw new IllegalArgumentException("Direction type expected");
        }

        switch (move1) {
            case UP:
                y = movementSpeed;
                break;
            case DOWN:
                y = -movementSpeed;
                break;
            case LEFT:
                x = -movementSpeed; 
                break;
            case RIGHT:
                x = movementSpeed;
                break;
            case STAY_X:
                x = 0;
                break;
            case STAY_Y:
                y = 0;
                break;
            default:
                break;
        }
    }

    @Override
    protected void collisonEvent(Entity entityCollided) {
        if (entityCollided.getType() == Type.WEAPON) {
            entity.getHealthComp().orElseThrow().setHealth(-1);
        }
    }
    
}
