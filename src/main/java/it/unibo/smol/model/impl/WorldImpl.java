package it.unibo.smol.model.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import it.unibo.smol.controller.input.KeyInputs;
import it.unibo.smol.controller.input.MouseInputs;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.World;
import it.unibo.smol.model.Type;

/**
 * implementation of world interface.
 */
public class WorldImpl implements World {
    private static final Boolean OCCUPIED = true;
    private static final Boolean FREE = false;
    private final ConcurrentLinkedDeque<Entity> entities;
    private final Map<Entity, Boolean> occupiedPlants;
    private KeyInputs keyInputs;
    private MouseInputs mouseInputs;
    private int score;

    /**
     * constructor for game world.
     */
    public WorldImpl() {
        this.entities = new ConcurrentLinkedDeque<>();
        this.occupiedPlants = new HashMap<>();
        this.score = 0;
    }

    /**
     * copy constructor.
     * @param world is the world that we want to copy
     */
    public WorldImpl(final World world) {
        this.entities = world.getEntities();
        this.score = world.getScore();
        this.occupiedPlants = world.occupiedPlants();
        this.mouseInputs = world.getMouseInputs().orElseThrow();
        this.keyInputs = world.getKeyInputs().orElseThrow();
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
    public ConcurrentLinkedDeque<Entity> getEntities() {
        return new ConcurrentLinkedDeque<>(this.entities); // no references
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
    public void updateWorld() {
        this.getEntities().forEach(x -> x.update());
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
    public void addEntity(final Entity entity) {
        this.entities.add(entity);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void addFirstEntity(final Entity entity) {
        this.entities.addFirst(entity);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void incScore(final int quantity) {
        if (this.score + quantity > 0) {
            this.score = this.score + quantity;
        }
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    public ConcurrentMap<Entity, Boolean> occupiedPlants() {
        updateLifePlants();
        return new ConcurrentHashMap<>(this.occupiedPlants);
    }

    private void updateLifePlants() {
        getLifePlants().forEach(plant -> {
            if (!occupiedPlants.containsKey(plant)) {
                occupiedPlants.put(plant, FREE);
            }
        });
        checkRemoved();
    }

    private void checkRemoved() {
        occupiedPlants.keySet().forEach(plant -> {
            if (!getLifePlants().contains(plant)) {
                occupiedPlants.remove(plant);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlantFree(final Entity plant) {
        updateLifePlants();
        if (occupiedPlants.containsKey(plant)) {
            occupiedPlants.put(plant, FREE);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlantOccupied(final Entity plant) {
        updateLifePlants();
        if (occupiedPlants.containsKey(plant)) {
            occupiedPlants.put(plant, OCCUPIED);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<KeyInputs> getKeyInputs() {
        return Optional.of(this.keyInputs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MouseInputs> getMouseInputs() {
        return Optional.of(this.mouseInputs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInputs(final Optional<KeyInputs> keyInputs, final Optional<MouseInputs> mouseInputs) {
        this.mouseInputs = mouseInputs.orElseThrow();
        this.keyInputs = keyInputs.orElseThrow();
    }

}
