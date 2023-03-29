package it.unibo.smol.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

import it.unibo.smol.common.Constant;
import it.unibo.smol.model.api.World;
import it.unibo.smol.model.impl.EntityFactoryImpl;
import it.unibo.smol.model.impl.WorldImpl;

class WorldTest {

    @Test
    public void testScore() {
        World w = new WorldImpl();
        EntityFactoryImpl ef = new EntityFactoryImpl();

        assertEquals(w.getScore(), 0);
        w.incScore(Constant.ENEMY_SCORE);
        assertNotEquals(w.getScore(), 0);

    }

}
