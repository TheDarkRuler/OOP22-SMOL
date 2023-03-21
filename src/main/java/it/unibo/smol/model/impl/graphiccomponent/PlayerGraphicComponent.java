package it.unibo.smol.model.impl.graphiccomponent;

import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.GraphicComponent;
import it.unibo.smol.view.LoadImgs;
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
    public PlayerGraphicComponent(final Entity entity, final double width, final double height) {
        super(entity, width, height);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void setAnimation() {

        if (moving) {
            setImage(LoadImgs.getSprites(LoadImgs.MOLE));
        } else if (attacking){
            setImage(LoadImgs.getSprites(LoadImgs.WORLD_IMG));
        } else {
            setImage(LoadImgs.getSprites(LoadImgs.WORLD_IMG));
        }
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void updateAnimation() {

        if(super.getEntity().getPhysicsComp().getX() == 0 &&
            super.getEntity().getPhysicsComp().getY() == 0) {
            moving = false;
        }else {
            moving = true;
        }
        /*if() {
            attacking = true;
        } else {
            attacking = false;
        }*/
    }
}
