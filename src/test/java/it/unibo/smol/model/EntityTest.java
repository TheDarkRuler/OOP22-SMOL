package it.unibo.smol.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.smol.common.Constant;
import it.unibo.smol.common.hitbox.RectangleHB;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.PhysicsComponent;
import it.unibo.smol.model.impl.EntityFactoryImpl;
import it.unibo.smol.model.impl.EntityImpl;
import it.unibo.smol.model.impl.WorldImpl;
import it.unibo.smol.model.impl.physicscomponent.EnemyPhysicsComponent;
import it.unibo.smol.model.impl.physicscomponent.PlayerPhysicsComponent;
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
