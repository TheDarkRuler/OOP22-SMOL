package it.unibo.smol.model.impl;

import java.util.Optional;

import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.GraphicComponent;
import it.unibo.smol.model.api.PhysicsComponent;
import it.unibo.smol.model.api.World;
import it.unibo.smol.input.api.InputComponent;

/**
 * The implementation that rappresent everything present in the game world.
 */
public class EntityImpl implements Entity {
    private final Type type;
    private InputComponent inputComp;
    private Optional<HealthComponent> healthComp;
    private GraphicComponent graphicComp;
    private PhysicsComponent physicsComp;
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
    public EntityImpl(final Type type, final InputComponent inputComp,
            final Optional<HealthComponent> healthComp,
            final GraphicComponent graphicComp, final PhysicsComponent physicsComp,
            final double currentX, final double currentY) {
        this.type = type;
        this.inputComp = inputComp;
        this.healthComp = healthComp;
        this.graphicComp = graphicComp;
        this.physicsComp = physicsComp;
        this.currentX = currentX;
        this.currentY = currentY;
        physicsComp.setEntity(this);
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
        return world;
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
        this.world = w;
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
        return physicsComp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        physicsComp.receiveMovement(inputComp.getDirection());
        this.moveX(physicsComp.getX());
        this.moveY(physicsComp.getY());
        physicsComp.checkCollision();
        if (healthComp.isPresent() && healthComp.get().isDead()) {
            this.getWorld().removeLifePlants(); //TODO : has to be a general remove
        }
        graphicComp.update();
    } 
}
