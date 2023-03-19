package it.unibo.smol.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.World;
import it.unibo.smol.model.Type;

/**
 * implementation of world interface.
 */
public class WorldImpl implements World {
    private static final int INC_RATE = 20;
    private final List<Entity> entities;
    //private final GameMap map;
    private int score;

    /**
     * constructor for game world.
     */
    public WorldImpl() {
        this.entities = new ArrayList<>();
        //this.map = new GameMapImpl();
        this.score = 0;
    }

    /**
     * copy constructor
     */
    public WorldImpl (World world) {
        this.entities = world.getEntities();
        this.score = world.getScore();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Entity> getMoles() {
        return this.entities.stream().filter(entity -> entity.getType() == Type.ENEMY).collect(Collectors.toList());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Entity getPlayer() {
        return this.entities.stream().filter(entity -> entity.getType() == Type.PLAYER).findFirst().get();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Entity> getLifePlants() {
        return this.entities.stream().filter(entity -> entity.getType() == Type.HEALTH).collect(Collectors.toList());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Entity> getEntities() {
        return new ArrayList<>(this.entities); // no references
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(final Entity thisEntity) {
        entities.remove(thisEntity);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public World updateWorld() {
        return this;
    }
    /**
     * {@inheritDoc}}
     */
    @Override
    public int getScore() {
        return this.score;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int diffIncrement() {
        return getScore() / INC_RATE;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void addEntity(final Entity entity) {
        this.entities.add(entity);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void incScore() {
        this.score = this.score + 1;
    } 
}
