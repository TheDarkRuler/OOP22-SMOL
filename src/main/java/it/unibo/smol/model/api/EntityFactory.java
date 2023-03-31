package it.unibo.smol.model.api;

import it.unibo.smol.common.HitBox;
import javafx.geometry.Point2D;

/**
 *  Interface for implementing a factory that create different instance of Entities.
 */
public interface EntityFactory {

    /**
     * create a basic enemy entities.
     * @param initialPosition the initial position of the enemy
     * @param w the world where the entity get create
     * @return a entities that rappresent the basic enemy
     */
    Entity createBasicEnemy(Point2D initialPosition, World w);

    /**
     * create a player entities.
     * @param x the x location where the entities spawn
     * @param y the y location where the entities spawn
     * @param w the world where the entity get create
     * @return a entities that rappresent the player character
     */
    Entity createPlayer(double x, double y, World w);

    /**
     * create a lifeplants entities.
     * @param x the x location where the entities spawn
     * @param y the y location where the entities spawn
     * @param w the world where the entity get create
     * @return a entities that rappresent the health of the player
     */
    Entity createLifePlants(double x, double y, World w);

    /**
     * create a weapon entities.
     * @param x the x location where the entities spawn
     * @param y the y location where the entities spawn
     * @param w the world where the entity get create
     * @return a entities that rappresent the weapon utilize by the player
     */
    Entity createWeapon(double x, double y, World w);

    /**
     * create an elmet enemy entities.
     * @param initialPosition the initial position of the enemy
     * @param w the world where the entity get create
     * @return a entities that rappresent the enemy with more HP
     */
    Entity createHelmetEnemy(Point2D initialPosition, World w);

    /**
     * create an angry enemy entities.
     * @param initialPosition the initial position of the enemy
     * @param w the world where the entity get create
     * @return a entities that rappresent the enemy with more movement speed
     */
    Entity createAngryEnemy(Point2D initialPosition, World w);

    /**
     * create an Bomb enemy entities.
     * @param initialPosition the initial position of the enemy
     * @param w the world where the entity get create
     * @return a entities that rappresent the enemy with more movement speed
     */
    Entity createBombEnemy(Point2D initialPosition, World w);

    /**
     * create a Wall entity.
     * @param hitBox the effective dimension of the wall
     * @param w the world where the entity get create
     * @return a entities that rappresent a wall for boundingbox
     */
    Entity createWall(HitBox hitBox, World w);
}
