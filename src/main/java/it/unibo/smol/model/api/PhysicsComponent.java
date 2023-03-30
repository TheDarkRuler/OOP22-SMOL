package it.unibo.smol.model.api;

import it.unibo.smol.common.Directions;
import it.unibo.smol.common.HitBox;
import javafx.geometry.Point2D;

/**
 * Abstract class rappresenting the template of the Physics component for the {@link Entity}.
 */
public abstract class PhysicsComponent {
    private double movementSpeed;
    private double x, y;
    private final HitBox hitBox;
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
     * Update the position of the entity and check the collision with the other entity present.
     */
    public void checkCollision() {
        if (this.entity != null) {
            this.entity.getWorld().getEntities().stream()
            .map(x -> x.getPhysicsComp())
            .filter(x -> !this.equals(x))
            .filter(x -> hitBox.isColliding(x.getHitBox()))
            .forEach(x -> {
                    if (this.isRigid() && x.isRigid()) {
                        //System.out.println("Entity "+this.entity.getType()+" has collided with "+x.entity.getType());
                        this.collisonEvent(x.getEntity());
                    }
                });
        } else {
            throw new IllegalStateException("Entity should be linked to his component");
        }
    }

    /**
     * This method receive a {@link Directions} and translate it into actual movement.
     * @param move : the direction given
     */
    public abstract void receiveMovement(Directions move);

    /**
     * This method receive a {@link Point2D} and translate it into actual movement.
     * @param move : the coordinate given
     * @param world
     */
    public abstract void receiveMovement(Point2D move, World world);

    /**
     * Getter for the entity field.
     * @return The entity that use this component
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * Set the entity associated with this component.
     * @param e : The entity that use this component
     */
    public void setEntity(final Entity e) {
        this.entity = e;
    }

    /**
     * Set a new movement speed of the component.
     * @param movementSpeed the new Movement soeed to be set
     */
    public void setMovementSpeed(final double movementSpeed) {
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
     * Get the hitbox shape.
     * @return the hitbox
     */
    public HitBox getHitBox() {
        return this.hitBox;
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
     * Resolve the effect of a collision that happened.
     * @param entityCollided : The other entity that collided this one
     */
    protected abstract void collisonEvent(Entity entityCollided);

    /**
     * Getter for the movementSpeed field.
     * @return the movementSpeed
     */
    public double getMovementSpeed() {
        return movementSpeed;
    }

    /**
     * Setter for the x field.
     * @param x the actual moevement in the X coordinate
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Setter for the y field.
     * @param y the actual moevement in the Y coordinate
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Setter for the rigidity of the hitbox.
     * @param isRigid {@code True} if rigid; {@code False} otherwise
     */
    public void setRigid(final boolean isRigid) {
        this.isRigid = isRigid;
    }

    /**
     * updates the hitbox of the enetities.
     * @param x
     * @param y
     */
    public void updateHitbox(final double x, final double y) {
        this.hitBox.setCenter(new Point2D(x, y));
    }

    /**
     * 
     * @return a copy of the current Physics component
     */
    public abstract PhysicsComponent makeCopy();

}
