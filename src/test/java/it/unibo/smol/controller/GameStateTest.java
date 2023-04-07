package it.unibo.smol.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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
class GameStateTest {

    private final World w = new WorldImpl();
    private final Optional<KeyInputs> keyInputs = Optional.of(new KeyInputs());
    private GameState gs;

    @BeforeEach
    public void initGameState() {
       this.w.setInputs(keyInputs, Optional.of(new MouseInputs(keyInputs)));
       this.gs = new GameStateImpl(this.w);
    }

    @Test
    void testInitialization() {
        this.gs.initGame();
        assertNotNull(this.gs.getWorld());
        assertNotNull(this.gs.getEntityFactory());
        assertFalse(this.gs.getWorld().orElseThrow().getEntities().isEmpty());

        assertNotNull(this.gs.getWorld().orElseThrow().getPlayer());
        //check if the weapon entity is present at the start
        assertTrue(this.gs.getWorld().orElseThrow().getEntities().stream()
            .filter(x -> x.getType() == Type.WEAPON)
            .findAny().isPresent());
        //check if the four walls are present at the start
        assertEquals(4, gs.getWorld().orElseThrow().getEntities().stream()
            .filter(x -> x.getType() == Type.WALL)
            .toList().size());
        //check if the plants are present at the start
        assertEquals(Constant.NUM_PLANTS, gs.getWorld().orElseThrow().getEntities().stream()
            .filter(x -> x.getType() == Type.HEALTH)
            .toList().size());
    }

    @Test
    void testScore() {
        final var w2 = gs.getWorld().orElseThrow();
        final int score = gs.getScore();

        assertEquals(0, score);
        //increase
        w2.incScore(Constant.ENEMY_SCORE);
        assertNotEquals(score, gs.getScore());
        //decrease
        w2.incScore(-Constant.ENEMY_SCORE);
        assertEquals(score, gs.getScore());
    }

    @Test
    void testGameOver() {
        final var w2 = gs.getWorld().orElseThrow();
        final List<Entity> plantList = w2.getLifePlants();
        plantList.forEach(x -> w2.remove(x));

        assertTrue(gs.isGameOver());
    }
}
