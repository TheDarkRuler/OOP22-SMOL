package it.unibo.smol.model.impl;

import it.unibo.smol.common.HitBox;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.PhysicsComponent;

public class LifePLantsPhysicsComponent extends PhysicsComponent {

    public LifePLantsPhysicsComponent(Double movementSpeed, HitBox hitBox) {
        super(movementSpeed, hitBox);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <A> void receiveMovement(A move) {
        //This entity never move
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void collisonEvent(Entity entityCollided) {
        if (entityCollided.getType() == Type.ENEMY || entityCollided.getType() == Type.PLAYER ) {
            entity.getHealthComp().orElseThrow().setHealth(-1);
        }
    }
    
}
