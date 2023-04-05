package it.unibo.smol.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.smol.model.impl.EntityFactoryImpl;
import it.unibo.smol.model.impl.WorldImpl;
import javafx.geometry.Point2D;

class WorldTest {

    private final WorldImpl world = new WorldImpl();

    @Test
    void testInitializeWorld() {
        assertNotNull(world);
        assertEquals(0, world.getScore());
        final var entities = world.getEntities();
        //Check if the entities deque is empty at the start
        assertTrue(entities.isEmpty());
        final var plants = world.getLifePlants();
        //Check if the map of plants is empty at the start
        assertEquals(0, plants.size());
    }

    @Test
    void testAddRemove() {
        //add a basic mole and a life plants in the same position
        world.addEntity(new EntityFactoryImpl().createBasicEnemy(new Point2D(0, 0), world));
        world.addEntity(new EntityFactoryImpl().createLifePlants(0, 0, world));
        assertFalse(world.getEntities().isEmpty());
        //Check if there are plants occupied
        assertFalse(world.occupiedPlants().isEmpty());
        //Check the last entity add to the world
        assertTrue(world.getLifePlants().contains(world.getEntities().getLast()));
        //Check remove entity
        world.remove(world.getLifePlants().get(0));
        assertTrue(world.getLifePlants().isEmpty());
        world.remove(world.getMoles().remove(0));
        assertTrue(world.getMoles().isEmpty());
    }
}
