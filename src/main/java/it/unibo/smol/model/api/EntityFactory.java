package it.unibo.smol.model.api;

import javafx.geometry.Point2D;

/**
 *  A factory to create different instance of Entities.
 */
public interface EntityFactory {

    /**
     * create a basic enemy entities.
     * @param initialPosition the initial position of the enemy
     * @return a entities that rappresent the basic enemy
     */
    Entity createBasicEnemy(Point2D initialPosition);

    /**
     * create an elmet enemy entities.
     * @param initialPosition the initial position of the enemy
     * @return a entities that rappresent the enemy with more HP
     */
    Entity createHelmetEnemy(Point2D initialPosition);

    /**
     * create an angry enemy entities.
     * @param initialPosition the initial position of the enemy
     * @return a entities that rappresent the enemy with more movement speed
     */
    Entity createAngryEnemy(Point2D initialPosition);

    /**
     * create a player entities.
     * @param x the x location where the entities spawn
     * @param y the y location where the entities spawn
     * @return a entities that rappresent the player character
     */
    Entity createPlayer(double x, double y);

    /**
     * create a lifeplants entities.
     * @param x the x location where the entities spawn
     * @param y the y location where the entities spawn
     * @return a entities that rappresent the health of the player
     */
    Entity createLifePlants(double x, double y);

    /**
     * create a weapon entities.
     * @param x the x location where the entities spawn
     * @param y the y location where the entities spawn
     * @return a entities that rappresent the weapon utilize by the player
     */
    Entity createWeapon(double x, double y);
}
