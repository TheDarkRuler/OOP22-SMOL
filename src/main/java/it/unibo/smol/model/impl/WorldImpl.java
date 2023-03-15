package it.unibo.smol.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.World;
import it.unibo.smol.view.api.GameMap;
import it.unibo.smol.model.Type;

/**
 * implementation of world interface.
 */
public class WorldImpl implements World {
    private final List<Entity> entities;
    private GameMap map;
    private final int score;
    /**
     * constructor for game world.
     * @param basicMoles
     * @param elmetMoles 
     * @param angryMoles
     * @param lifePlants
     * @param score
     */
    public WorldImpl(final int basicMoles, final int elmetMoles, final int angryMoles, final int lifePlants, final int score) {
        //TODO better dry if possible
        this.entities = new ArrayList<>();
        //moles
        for (int i = 0; i < basicMoles; i++) {
            this.entities.add(new EntityFactoryImpl().createBasicMole(i, i));
        }

        for (int i = 0; i < elmetMoles; i++) {
            this.entities.add(new EntityFactoryImpl().createElmetMole(i, i));
        }

        for (int i = 0; i < angryMoles; i++) {
            this.entities.add(new EntityFactoryImpl().createAngryMole(i, i));
        }

        //lifePlants
        for (int i = 0; i < lifePlants; i++) {
            this.entities.add(new EntityFactoryImpl().createLifePlants(i, i));
        }

        //TODO decide place for player
        //player
        this.entities.add(new EntityFactoryImpl().createPlayer(elmetMoles, angryMoles));

        //gamemap

        //score
        this.score = score;
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
    public GameMap getMap() {
        return this.map;
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
}
