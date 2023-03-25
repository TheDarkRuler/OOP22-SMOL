package it.unibo.smol.model.impl.physicscomponent;

import it.unibo.smol.common.Constant;
import it.unibo.smol.common.Directions;
import it.unibo.smol.common.GameMap;
import it.unibo.smol.common.HitBox;
import it.unibo.smol.common.hitbox.RectangleHB;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.PhysicsComponent;
import it.unibo.smol.model.api.World;
import javafx.geometry.Point2D;
/**
 * The implementation of the {@link PhysicsComponent} rappresenting the Player behaviour.
 */
public class PlayerPhysicsComponent extends PhysicsComponent {

    /**
     * Constructor inherited by the super-class {@link PhysicsComponent}.
     * @param hitBox : See the super-Constructor
     */
    public PlayerPhysicsComponent(final HitBox hitBox) {
        super(Constant.PLAYER_MOVSPD, hitBox);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveMovement(final Directions move) {
        if (!this.getHitBox().isColliding(new RectangleHB(GameMap.MAP_WIDTH - this.getHitBox().getWidth()*2, GameMap.MAP_HEIGHT - this.getHitBox().getHeight()*2, new Point2D(GameMap.WIDTH / 2, GameMap.HEIGHT / 2)))) {
            System.out.println("AAAAAAAAAAAA\nAAAAAAAAAAAAAAAAAa\nAAAAAAAAAAAAA\n");
            super.setX(0);
            super.setY(0);
        } else {
            switch (move) {
                case UP:
                    super.setY(-super.getMovementSpeed());
                    break;
                case DOWN:
                    super.setY(super.getMovementSpeed());
                    break;
                case LEFT:
                    super.setX(-super.getMovementSpeed());
                    break;
                case RIGHT:
                    super.setX(super.getMovementSpeed());
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
    }

    /**
     * {@inheritDoc}
     */
    /*public void setX(final double x) {
        super.setX(super.getEntity().getCurrentX() + x);
    }*/

    /**
     * {@inheritDoc}
     */
    /*public void setY(final double y) {
        super.setY(super.getEntity().getCurrentY() + y);
    }*/

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveMovement(final Point2D move, final World world) {
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
