package it.unibo.smol.model.impl;

import java.util.Optional;

import it.unibo.smol.controller.api.InputComponent;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.GraphicComponent;
import it.unibo.smol.model.api.PhysicsComponent;
import it.unibo.smol.model.api.World;

/**
 * The implementation that rappresent everything present in the game world.
 */
public class EntityImpl implements Entity {
    private final Type type;
    private final Optional<InputComponent> inputComp;
    private final Optional<HealthComponent> healthComp;
    private final GraphicComponent graphicComp;
    private final PhysicsComponent physicsComp;
    private double currentX;
    private double currentY;
    private World world;

    /**
     * Constructor for creating entities utilizing the entity factory.
     * @param type
     * @param inputComp
     * @param healthComp
     * @param graphicComp
     * @param physicsComp
     * @param currentX
     * @param currentY
     */
    public EntityImpl(final Type type, final Optional<InputComponent> inputComp,
            final Optional<HealthComponent> healthComp,
            final GraphicComponent graphicComp, final PhysicsComponent physicsComp,
            final double currentX, final double currentY) {
        this.type = type;
        this.inputComp = inputComp;
        this.healthComp = healthComp;
        this.graphicComp = graphicComp;
        this.physicsComp = physicsComp.makeCopy();
        this.currentX = currentX;
        this.currentY = currentY;
        physicsComp.setEntity(new EntityImpl(this));
    }

    /**
     * Copy constructor.
     * @param entity
     */
    public EntityImpl(final Entity entity) {
        this.type = entity.getType();
        this.inputComp = entity.getInputComp();
        this.healthComp = entity.getHealthComp();
        this.graphicComp = null;
        this.currentX = entity.getCurrentX();
        this.currentY = entity.getCurrentY();
        this.world = entity.getWorld();
        this.physicsComp = entity.getPhysicsComp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCurrentX() {
        return currentX;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCurrentY() {
        return currentY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public World getWorld() {
        return new WorldImpl(world);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveX(final double x) {
        currentX += x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveY(final double y) {
        currentY += y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWorld(final World w) {
        this.world = new WorldImpl(w);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Type getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<HealthComponent> getHealthComp() {
        return healthComp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhysicsComponent getPhysicsComp() {
        return physicsComp.makeCopy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (inputComp.isPresent()) {
            final InputComponent inputCompPresent = inputComp.orElseThrow();
            if (inputCompPresent.getDirection().isPresent()) {
                physicsComp.receiveMovement(inputCompPresent.getDirection().orElseThrow());
            }
            if (inputCompPresent.getPosition().isPresent()) {
                physicsComp.receiveMovement(inputCompPresent.getPosition().orElseThrow());
            }
            physicsComp.setRigid(inputCompPresent.isHittable());
            this.moveX(physicsComp.getX());
            this.moveY(physicsComp.getY());
        }
        physicsComp.checkCollision();
        if (healthComp.isPresent() && healthComp.get().isDead()) {
            this.getWorld().remove(this);
        }
        graphicComp.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<InputComponent> getInputComp() {
        return this.inputComp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicComponent getGraphicComponent() {
        //TODO adjust graphic component when properly defined
        return null;
    } 
}
