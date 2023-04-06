package it.unibo.smol.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.smol.common.Constant;
import it.unibo.smol.controller.api.GameState;
import it.unibo.smol.controller.impl.GameStateImpl;
import it.unibo.smol.controller.input.KeyInputs;
import it.unibo.smol.controller.input.MouseInputs;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.World;
import it.unibo.smol.model.impl.WorldImpl;

/**
 * Test for game state.
 */
public class GameStateTest {

    @Test
    void testInitialization() {
        final World w = new WorldImpl();
        final var keyInputs = Optional.of(new KeyInputs());
        w.setInputs(keyInputs, Optional.of(new MouseInputs(keyInputs)));
        final GameState gs = new GameStateImpl(w);

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

    @Test
    void testGameOver() {
        final World w = new WorldImpl();
        final var keyInputs = Optional.of(new KeyInputs());
        w.setInputs(keyInputs, Optional.of(new MouseInputs(keyInputs)));
        final GameState gs = new GameStateImpl(w);

        var w2 = gs.getWorld().orElseThrow();
        List<Entity> plantList = w2.getLifePlants();
        plantList.forEach(x -> w2.remove(x));

        assertTrue(gs.isGameOver());
    }
}
