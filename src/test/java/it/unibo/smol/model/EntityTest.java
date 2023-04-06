package it.unibo.smol.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.smol.model.api.PhysicsComponent;
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
        //Check physics component
        assertTrue(basicEnemy.getPhysicsComp().isPresent());
        assertTrue(PhysicsComponent.class.isInstance(basicEnemy.getPhysicsComp().get()));
    }

    @Test
    void testEntityComponentIsEmpty() {
        final var lifeplants = new EntityFactoryImpl().createLifePlants(0, 0, world);
        //Check entity type
        assertEquals(Type.HEALTH, lifeplants.getType());
        assertTrue(lifeplants.getInputComp().isEmpty());
    }
}
