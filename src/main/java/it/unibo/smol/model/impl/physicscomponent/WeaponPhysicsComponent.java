package it.unibo.smol.model.impl.physicscomponent;

import it.unibo.smol.common.Constant;
import it.unibo.smol.common.Directions;
import it.unibo.smol.common.HitBox;
import it.unibo.smol.model.Type;
import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.PhysicsComponent;
import javafx.geometry.Point2D;
import it.unibo.smol.model.api.World;

/**
 * The implementation of the {@link PhysicsComponent} rappresenting the Player behaviour.
 */
public class WeaponPhysicsComponent extends PhysicsComponent {

    /**
     * Constructor inherited by the super-class {@link PhysicsComponent}.
     * @param hitBox : See the super-Constructor
     */
    public WeaponPhysicsComponent(final HitBox hitBox) {
        super(Constant.WEAPON_MOVSPD, hitBox);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void collisonEvent(final Entity entityCollided) {
        //This entity don't have any collisionEffect on himself 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveMovement(final Directions move) {
        //This component doesn't use this method
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void receiveMovement(final Point2D move, final World world) {
        final double wRange = world.getMouseInputs().orElseThrow().getWeaponRange();
        final Point2D playerLocation = world.getEntities()
            .stream()
            .filter(x -> x.getType().equals(Type.PLAYER))
            .findAny().get().getCurrentPosition();
        final Point2D weaponLocation = updateWeaponLocation(move, wRange, playerLocation);
        super.setX(weaponLocation.getX());
        super.setY(weaponLocation.getY());
    }

    private Point2D updateWeaponLocation(final Point2D move, final double wRange, final Point2D playerLocation) {
        final double r = wRange / 2;
        final double angle = Math.atan2(move.getY() - playerLocation.getY(), move.getX() - playerLocation.getX());
        final double tempX = r * Math.cos(angle);
        final double tempY = r * Math.sin(angle);
        return new Point2D(playerLocation.getX() + tempX, playerLocation.getY() + tempY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PhysicsComponent makeCopy() {
        return this;
    }
}
