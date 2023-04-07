package it.unibo.smol.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.smol.common.HitBox;
import it.unibo.smol.common.hitbox.CircleHB;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.World;
import it.unibo.smol.model.impl.EntityImpl;
import it.unibo.smol.model.impl.HealthComponent;
import it.unibo.smol.model.impl.WorldImpl;
import it.unibo.smol.model.impl.physicscomponent.EmptyPhysicsComponent;
import it.unibo.smol.model.impl.physicscomponent.EnemyPhysicsComponent;
import it.unibo.smol.model.impl.physicscomponent.LifePlantsPhysicsComponent;
import it.unibo.smol.model.impl.physicscomponent.PlayerPhysicsComponent;
import javafx.geometry.Point2D;

/**
 * Physics test for collision.
 */
class PhysicsTest {
    private final World w = new WorldImpl();
    private static final double X1 = 1, X2 = 3;
    private static final double Y1 = 3, Y2 = 3;
    private final HitBox hb1 = new CircleHB(new Point2D(X1, Y1), 5);
    private final HitBox hb2 = new CircleHB(new Point2D(X2, Y2), 5);

    @Test
    void collisionEnemyPlants() {
        final Entity e = new EntityImpl(Type.HEALTH,
            Optional.empty(),
            Optional.of(new HealthComponent(1)),
            Optional.empty(),
            Optional.of(new LifePlantsPhysicsComponent(hb2)),
            X2, Y2, Optional.of(w));
        final Entity e2 = new EntityImpl(Type.ENEMY,
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.of(new EnemyPhysicsComponent(hb1, 0)),
            X1, Y2, Optional.of(w));
        w.addEntity(e2);
        w.addEntity(e);

        assertTrue(e.getPhysicsComp().isPresent());
        assertTrue(e2.getPhysicsComp().isPresent());

        e.getPhysicsComp().orElseThrow().checkCollision();
        e.checkHealth();
        assertTrue(e.getHealthComp().orElseThrow().isDead());

        assertEquals(w.getEntities().size(), 0);
    }

    @Test
    void collisionPlayerPlants() {

        final Entity e = new EntityImpl(Type.HEALTH,
            Optional.empty(),
            Optional.of(new HealthComponent(1)),
            Optional.empty(),
            Optional.of(new LifePlantsPhysicsComponent(hb2)),
            X2, Y2, Optional.of(w));
        final Entity e2 = new EntityImpl(Type.PLAYER,
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.of(new PlayerPhysicsComponent(hb1)),
            X1, Y2, Optional.of(w));
        w.addEntity(e2);
        w.addEntity(e);

        assertTrue(e.getPhysicsComp().isPresent());
        assertTrue(e2.getPhysicsComp().isPresent());

        e.getPhysicsComp().orElseThrow().checkCollision();
        e.checkHealth();
        assertTrue(e.getHealthComp().orElseThrow().isDead());

        assertEquals(w.getEntities().size(), 1);
    }

    @Test
    void collisionEnemyWeapon() {
        final Entity e = new EntityImpl(Type.WEAPON,
            Optional.empty(),
            Optional.empty(),
            Optional.empty(),
            Optional.of(new EmptyPhysicsComponent(hb2)),
            X2, Y2, Optional.of(w));
        final Entity e2 = new EntityImpl(Type.ENEMY,
            Optional.empty(),
            Optional.of(new HealthComponent(1)),
            Optional.empty(),
            Optional.of(new EnemyPhysicsComponent(hb1, 0)),
            X1, Y2, Optional.of(w));
        w.addEntity(e2);
        w.addEntity(e);

        assertTrue(e.getPhysicsComp().isPresent());
        assertTrue(e2.getPhysicsComp().isPresent());

        e.getPhysicsComp().orElseThrow().setRigid(true);
        e2.getPhysicsComp().orElseThrow().checkCollision();
        e2.checkHealth();
        assertTrue(e2.getHealthComp().orElseThrow().isDead());
        assertEquals(100, w.getScore());
        assertEquals(1, w.getEntities().size());
    }
}
