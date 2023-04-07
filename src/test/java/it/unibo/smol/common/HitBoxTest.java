package it.unibo.smol.common;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.unibo.smol.common.hitbox.CircleHB;
import it.unibo.smol.common.hitbox.RectangleHB;
import javafx.geometry.Point2D;

/**
 * test for the hitboxes.
 */
class HitBoxTest {

    /**
     * Tests if the method isColliding works for between Rect-Circle.
     */
    @Test
    void collisionEventRectCircle() {
        assertTrue(new RectangleHB(Constant.PLAYER_WIDTH, Constant.PLAYER_HEIGHT, new Point2D(0, 0))
            .isColliding(new CircleHB(new Point2D(0, 0), Constant.WEAPON_RADIUS)));
    }

    /**
     * Tests if the method isColliding works for between Rect-Rect.
     */
    @Test
    void collisionEventRectRect() {
        assertTrue(new RectangleHB(Constant.PLAYER_WIDTH, Constant.PLAYER_HEIGHT, new Point2D(0, 0))
            .isColliding(new RectangleHB(Constant.PLAYER_WIDTH, Constant.PLAYER_HEIGHT, new Point2D(0, 0))));
    }

    /**
     * Tests if the method isColliding works for between Circle-Rect.
     */
    @Test
    void collisionEventCircleRect() {
        assertTrue(new CircleHB(new Point2D(0, 0), Constant.WEAPON_RADIUS)
            .isColliding(new RectangleHB(Constant.PLAYER_WIDTH, Constant.PLAYER_HEIGHT, new Point2D(0, 0))));
    }

    /**
     * Tests if the method isColliding works for between Circle-Circle.
     */
    @Test
    void collisionEventCircleCircle() {
        assertTrue(new CircleHB(new Point2D(0, 0), Constant.WEAPON_RADIUS)
            .isColliding(new CircleHB(new Point2D(0, 0), Constant.WEAPON_RADIUS)));
    }
}
