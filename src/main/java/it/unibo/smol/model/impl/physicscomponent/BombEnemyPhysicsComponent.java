package it.unibo.smol.model.impl.physicscomponent;

import it.unibo.smol.common.Constant;
import it.unibo.smol.common.HitBox;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;

/**
 * The implementation of the {@link PhysicsComponent} rappresenting the Bomb Enemy behaviour.
 */
public class BombEnemyPhysicsComponent extends EnemyPhysicsComponent {

    /**
     * Constructor inherited by the super-class {@link PhysicsComponent}.
     * @param hitBox : See the super-Constructor
     * @param movSpeed
     */
    public BombEnemyPhysicsComponent(final HitBox hitBox, final double movSpeed) {
        super(hitBox, movSpeed);
    }
    /**
     * Whenever this entity collide with a Weapon {@link Type} entity, it takes 1 damage.
     * @param entityCollided
     */
    @Override
    protected void collisonEvent(final Entity entityCollided) {
        if (entityCollided.getType() == Type.WEAPON) {
            super.getEntity().orElseThrow().getInputComp().orElseThrow().freezeInput(Constant.BOMB_EXPLOSION);
            super.getEntity().orElseThrow().getHealthComp().orElseThrow().setHealth(Constant.WEAPON_DMG);
            super.getEntity().orElseThrow().getWorld().orElseThrow().incScore(-Constant.ENEMY_SCORE);
        }

        if (entityCollided.getType() == Type.PLAYER) {
            entityCollided.setX(entityCollided.getCurrentX() - entityCollided.getPhysicsComp().orElseThrow().getX());
            entityCollided.setY(entityCollided.getCurrentY() - entityCollided.getPhysicsComp().orElseThrow().getY());
        }
    }
}
