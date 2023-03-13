package it.unibo.smol.model.impl;

import it.unibo.smol.common.HitBox;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.PhysicsComponent;
import javafx.geometry.Point2D;

public class WeaponPhysicsComponent extends PhysicsComponent{

    public WeaponPhysicsComponent(Double movementSpeed, HitBox hitBox) {
        super(movementSpeed, hitBox);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <A> void receiveMovement(A move) {
        Point2D move1;
        if (move instanceof Point2D) {
            move1 = (Point2D) move;
        } else {
            throw new IllegalArgumentException("Point2D type expected");
        }

        x = move1.getX() - entity.getCurrentX();
        y = move1.getY() - entity.getCurrentY();
    }

    @Override
    protected void collisonEvent(Entity entityCollided) {
        //This entity don't have any collisionEffect on himself 
    }
    
}
