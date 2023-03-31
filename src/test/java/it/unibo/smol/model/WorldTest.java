package it.unibo.smol.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.smol.model.api.World;
import it.unibo.smol.model.impl.WorldImpl;

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
}
