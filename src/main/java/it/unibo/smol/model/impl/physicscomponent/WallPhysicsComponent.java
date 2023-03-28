package it.unibo.smol.model.impl.physicscomponent;

import it.unibo.smol.common.Constant;
import it.unibo.smol.common.Directions;
import it.unibo.smol.common.HitBox;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.PhysicsComponent;
import it.unibo.smol.model.api.World;
import javafx.geometry.Point2D;

public class WallPhysicsComponent extends PhysicsComponent {

    public WallPhysicsComponent(HitBox hitBox) {
        super(Constant.HEALTH_MOVSPD, hitBox);
    }

    @Override
    public void receiveMovement(Directions move) {
        //This component doesn't use this method
    }

    @Override
    public void receiveMovement(Point2D move, World world) {
        //This component doesn't use this method
    }

    @Override
    protected void collisonEvent(Entity entityCollided) {
        if (entityCollided.getType() == Type.PLAYER) {
            getEntity().getWorld().getPlayer().getPhysicsComp().setX(-getX());
            getEntity().getWorld().getPlayer().getPhysicsComp().setY(-getY());
        }
    }

    @Override
    public PhysicsComponent makeCopy() {
        return this;
    }
    
}
