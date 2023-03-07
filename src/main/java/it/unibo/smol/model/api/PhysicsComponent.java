package it.unibo.smol.model.api;

import it.unibo.smol.common.Directions;
import it.unibo.smol.common.HitBox;

/**
 * Abstract class rappresenting the template of the Physics component for the {@link Entity}.
 */
public abstract class PhysicsComponent {
    private int movementSpeed;
    private int x,y;
    private HitBox hitBox;
    private boolean isRigid;
    private Entity entity;

    /**
     * Constructor for the Physics component.
     * @param movementSpeed : the value that determine the speed of the entity
     * @param hitBox : the shape that rappresent the logic position of the entity
     */
    public PhysicsComponent(final int movementSpeed, final HitBox hitBox) {
        this.movementSpeed = movementSpeed;
        this.hitBox = hitBox;
        this.isRigid = true;
    }

    /**
     * Update the position of the entity and check the collision with the other entity present
     */
    public void update() {
        this.entity.getInputComp()//TODO
        ;
    }

    /**
     * 
     * @param direction
     */
    public void receiveDirection(final Directions direction) {
        switch (direction) {
            case UP:
                y += movementSpeed;
                break;
            case DOWN:
                y -= movementSpeed;
                break;
            case LEFT:
                x -= movementSpeed; 
                break;
            case RIGHT:
                x += movementSpeed;
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

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isRigid() {
        return isRigid;
    }

    public void setRigid() {
        if (isRigid) {
            isRigid = false;
        } else {
            isRigid = true;
        }
    }

    protected abstract void collisonEvent(Entity entityCollided);
}
