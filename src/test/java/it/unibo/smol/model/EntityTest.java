package it.unibo.smol.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.smol.model.impl.EntityFactoryImpl;
import it.unibo.smol.model.impl.WorldImpl;
import javafx.geometry.Point2D;

/**
 * test class for the enetities.
 */
public class EntityTest {

    private final WorldImpl world = new WorldImpl();

    @Test
    void testEntityComponent() {
        final var basicEnemy = new EntityFactoryImpl().createBasicEnemy(new Point2D(0, 0), world);
        //Check entity type
        assertEquals(Type.ENEMY, basicEnemy.getType());
        //Check if physics component is not empty
        assertNotEquals(Optional.empty(), basicEnemy.getPhysicsComp());

    }
}
