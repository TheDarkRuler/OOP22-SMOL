package it.unibo.smol.model.api;
/**
 *  A factory to create different instance of Entities.
 */
public interface EntityFactory {

    /**
     * create a basic enemy entities.
     * @param x the x location where the entities spawn
     * @param y the y location where the entities spawn
     * @return a entities that rappresent the basic enemy
     */
    Entity createBasicEnemy(double x, double y);

    /**
     * create an elmet enemy entities.
     * @param x the x location where the entities spawn
     * @param y the y location where the entities spawn
     * @return a entities that rappresent the enemy with more HP
     */
    Entity createHelmetEnemy(double x, double y);

    /**
     * create an angry enemy entities.
     * @param x the x location where the entities spawn
     * @param y the y location where the entities spawn
     * @return a entities that rappresent the enemy with more movement speed
     */
    Entity createAngryEnemy(double x, double y);

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
