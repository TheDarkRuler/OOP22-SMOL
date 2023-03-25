package it.unibo.smol.model.api;

import javafx.geometry.Point2D;

/**
 *  A factory to create different instance of Entities.
 */
public interface EntityFactory {

    /**
     * create a basic enemy entities.
     * @param initialPosition the initial position of the enemy
     * @param w the world where the entity create
     * @return a entities that rappresent the basic enemy
     */
    Entity createBasicEnemy(Point2D initialPosition, World w);

    /**
     * create a player entities.
     * @param x the x location where the entities spawn
     * @param y the y location where the entities spawn
     * @param w
     * @return a entities that rappresent the player character
     */
    Entity createPlayer(double x, double y, World w);

    /**
     * create a lifeplants entities.
     * @param x the x location where the entities spawn
     * @param y the y location where the entities spawn
     * @param w
     * @return a entities that rappresent the health of the player
     */
    Entity createLifePlants(double x, double y, World w);

    /**
     * create a weapon entities.
     * @param x the x location where the entities spawn
     * @param y the y location where the entities spawn
     * @param w
     * @return a entities that rappresent the weapon utilize by the player
     */
    Entity createWeapon(double x, double y, World w);

    /**
     * create an elmet enemy entities.
     * @param initialPosition the initial position of the enemy
     * @param w the world where the entity create
     * @return a entities that rappresent the enemy with more HP
     */
    Entity createHelmetEnemy(Point2D initialPosition, World w);

    /**
     * create an angry enemy entities.
     * @param initialPosition the initial position of the enemy
     * @param w the world where the entity create
     * @return a entities that rappresent the enemy with more movement speed
     */
    Entity createAngryEnemy(Point2D initialPosition, World w);

    /**
     * create an Bomb enemy entities.
     * @param initialPosition the initial position of the enemy
     * @param w the world where the entity create
     * @return a entities that rappresent the enemy with more movement speed
     */
    Entity createBombEnemy(Point2D initialPosition, World w);
}
