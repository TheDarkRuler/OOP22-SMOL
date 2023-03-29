package it.unibo.smol.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.smol.model.api.World;
import it.unibo.smol.model.impl.EntityFactoryImpl;
import it.unibo.smol.model.impl.WorldImpl;

class WorldTest {
    private World world;
    WorldTest() {
        this.world = new WorldImpl();
    }

    @Test
    public void testInitialization() {
        var wd = this.world;
        var ef = new EntityFactoryImpl();
        wd.addEntity(ef.createPlayer(0, 0, world));
        assertTrue(!wd.getEntities().isEmpty());

    }

}
