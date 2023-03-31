package it.unibo.smol.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

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
        w.setKeyInputs(Optional.empty());
        w.setMouseInputs(Optional.empty());
        GameState gs = new GameStateImpl(w);

        gs.initGame();        
        assertNotNull(gs.getWorld());
        assertNotNull(gs.getEntityFactory());
        assertFalse(gs.getWorld().orElseThrow().getEntities().isEmpty());
        assertNotNull(gs.getWorld().orElseThrow().getPlayer());
        assertTrue(gs.getWorld().orElseThrow().getEntities().stream()
            .filter(x -> x.getType() == Type.WEAPON)
            .findAny().isPresent());

        assertTrue(gs.getWorld().orElseThrow().getEntities().stream()
            .filter(x -> x.getType() == Type.WALL)
            .toList().size() == 4);
        
        assertTrue(gs.getWorld().orElseThrow().getEntities().stream()
            .filter(x -> x.getType() == Type.HEALTH)
            .toList().size() == Constant.NUM_PLANTS);
    }
}
