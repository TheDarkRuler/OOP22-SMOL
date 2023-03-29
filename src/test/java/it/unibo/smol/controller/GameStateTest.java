package it.unibo.smol.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.smol.common.Constant;
import it.unibo.smol.controller.api.GameState;
import it.unibo.smol.controller.impl.GameStateImpl;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.World;
import it.unibo.smol.model.impl.WorldImpl;

public class GameStateTest {
    
    @Test
    public void testInitialization() {
        World w = new WorldImpl();
        GameState gs = new GameStateImpl(w);

        gs.initGame();
        assertNotNull(gs.getWorld());
        assertNotNull(gs.getEntityFactory());
        assertFalse(gs.getWorld().getEntities().isEmpty());
        assertNotNull(gs.getWorld().getPlayer());
        assertTrue(gs.getWorld().getEntities().stream()
            .filter(x -> x.getType() == Type.WEAPON)
            .findAny().isPresent());

        assertTrue(gs.getWorld().getEntities().stream()
            .filter(x -> x.getType() == Type.WALL)
            .toList().size() == 4);
        
        assertTrue(gs.getWorld().getEntities().stream()
            .filter(x -> x.getType() == Type.HEALTH)
            .toList().size() == Constant.NUM_PLANTS);
    }
}
