package it.unibo.smol.model.api;

import it.unibo.smol.common.Directions;
import it.unibo.smol.common.HitBox;

/**
 * Abstract class rappresenting the template of the Physics component for the {@link Entity}.
 */
public abstract class PhysicsComponent {
    private double movementSpeed;
    private double x,y;
    private HitBox hitBox;
    private boolean isRigid;
    private Entity entity;

    /**
     * Constructor for the Physics component.
     * @param movementSpeed : the value that determine the speed of the entity
     * @param hitBox : the shape that rappresent the logic position of the entity
     */
    public PhysicsComponent(final Double movementSpeed, final HitBox hitBox) {
        this.movementSpeed = movementSpeed;
        this.hitBox = hitBox;
        this.isRigid = true;
    }

    /**
     * Update the position of the entity and check the collision with the other entity present
     */
    public void checkCollision() {
        this.entity.getWorld().getEntities().stream()
            .map(x -> x.getPhysicsComp())
            .filter(x -> hitBox.isColliding(x.getHitBox()))
            .forEach(x -> {
                    if ( this.isRigid() && x.isRigid() ) {
                        this.collisonEvent(x.getEntity());
                    }
                });
    }

    /**
     * Translate the command received from the {@link InputComponent} to actual movement.
     * @param direction : The command received
     */
    public void receiveDirection(final Directions direction) {
        switch (direction) {
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

    /**
     * Getter for the entity field.
     * @return The entity that use this component
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Set the entity associated with this component.
     * @param entity : The entity that use this component
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Set a new movement speed of the component.
     * @param movementSpeed the new Movement soeed to be set
     */
    public void setMovementSpeed(double movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    /**
     * Get the X coordinate.
     * @return the amount of movement in the X coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Get the Y coordinate.
     * @return the amount of movement in the Y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * Get the hitbox shape
     * @return the hitbox
     */
    public HitBox getHitBox() {
        return hitBox;
    }

    /**
     * Return the state of the {@link #hitBox}.
     * @return {@code True} if the hitbox can collide with other entities;
     * {@code False} otherwise
     */
    public boolean isRigid() {
        return isRigid;
    }

    /**
     * Change to the other state of the {@link #hitBox};
     * Check for more infos ( {@link #isRigid()} ).
     */
    public void setRigid() {
        if (isRigid) {
            isRigid = false;
        } else {
            isRigid = true;
        }
    }

    /**
     * Resolve the effect of a collision that happened.
     * @param entityCollided : The other entity that collided this one
     */
    protected abstract void collisonEvent(Entity entityCollided);
}
