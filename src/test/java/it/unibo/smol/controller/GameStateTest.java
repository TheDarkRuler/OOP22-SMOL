package it.unibo.smol.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.smol.controller.api.GameState;
import it.unibo.smol.controller.impl.GameStateImpl;
import it.unibo.smol.model.api.World;
import it.unibo.smol.model.impl.WorldImpl;

public class GameStateTest {
    
    @Test
    public void testInitialization() {
        World w = new WorldImpl();
        GameState gs = new GameStateImpl(w);

        gs.initGame();
        assertFalse(gs.getWorld().getEntities().isEmpty());
        assertNotNull(gs.);
    }
}
