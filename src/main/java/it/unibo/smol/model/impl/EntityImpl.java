package it.unibo.smol.model.impl;

import javafx.geometry.Point2D;
import java.util.Optional;
import it.unibo.smol.controller.api.InputComponent;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.PhysicsComponent;
import it.unibo.smol.model.api.World;
import it.unibo.smol.view.api.GraphicComponent;

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
     * @param w
     */
    public EntityImpl(final Type type, final Optional<InputComponent> inputComp,
            final Optional<HealthComponent> healthComp,
            final GraphicComponent graphicComp, final PhysicsComponent physicsComp,
            final double currentX, final double currentY, World w) {
        this.type = type;
        this.inputComp = inputComp;
        this.healthComp = healthComp;
        this.graphicComp = graphicComp;
        this.physicsComp = physicsComp.makeCopy();
        this.currentX = currentX;
        this.currentY = currentY;
        this.world = new WorldImpl(w);
        physicsComp.setEntity(new EntityImpl(this));
        graphicComp.setEntity(this);
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
    public Point2D getCurrentPosition() {
        return new Point2D(currentX, currentY);
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
    public void setX(final double x) {
        currentX = x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setY(final double y) {
        currentY = y;
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
            inputCompPresent.getDirection().ifPresent(x -> physicsComp.receiveMovement(x));
            inputCompPresent.getPosition().ifPresent(x -> physicsComp.receiveMovement(x, world));
            physicsComp.setRigid(inputCompPresent.isHittable());
            if (this.type.equals(Type.WEAPON) || this.type.equals(Type.ENEMY)) {
                this.setX(physicsComp.getX());
                this.setY(physicsComp.getY());
            } else {
                this.setX(this.currentX + physicsComp.getX());
                this.setY(this.currentY + physicsComp.getY());
            }
            System.out.println("Entity "+this.type+" move to x:"+(int)this.currentX+" y:"+(int)this.currentY);
        }
        physicsComp.checkCollision();
        if (healthComp.isPresent() && healthComp.get().isDead()) {
            this.getWorld().remove(this);
        }
        //graphicComp.render();
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
    public GraphicComponent getGraphicComp() {
        return this.graphicComp;
    }
}
