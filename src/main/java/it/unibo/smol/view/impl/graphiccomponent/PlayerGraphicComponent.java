package it.unibo.smol.view.impl.graphiccomponent;

import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.GraphicComponent;
/**
 * The implementation of the {@link GraphicComponent} rappresenting the Player graphic.
 */
public class PlayerGraphicComponent extends GraphicComponent {

    private boolean moving;
    private boolean attacking;

    /**
     * Constructors inherited by the super-class {@link GraphicComponent}.
     * @param width : See the super-Constructor
     * @param height : See the super-Constructor
     */
    public PlayerGraphicComponent(final double width, final double height) {
        super(width, height);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void setAnimation() {

        if (moving) {
            setImageName(LoadImgs.PLAYER);
        } else if (attacking) {
            setImageName(LoadImgs.PLAYER);
        } else {
            setImageName(LoadImgs.PLAYER);
        }
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void updateAnimation() {
        if (super.getEntity() != null) {
                if (super.getEntity().getPhysicsComp().getX() == 0 
                    && super.getEntity().getPhysicsComp().getY() == 0) {
                    moving = false;
                } else {
                    moving = true;
                }
                /*if() {
                    attacking = true;
                } else {
                    attacking = false;
                }*/
        }
    }
}
