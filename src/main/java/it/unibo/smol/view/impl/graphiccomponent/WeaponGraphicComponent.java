package it.unibo.smol.view.impl.graphiccomponent;

import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.GraphicComponent;
/**
 * The implementation of the {@link GraphicComponent} rappresenting the Weapon graphic.
 */
public class WeaponGraphicComponent extends GraphicComponent {

    //private boolean attacking;

    /**
     * 
     * @param width
     * @param height
     */
    public WeaponGraphicComponent(final double width, final double height) {
        super(width, height);
    }

    /**
     * 
     */
    @Override
    public void setAnimation() {
        super.setImageName(LoadImgs.W_TARGET);
    }

    /**
     * 
     */
    @Override
    public void updateAnimation() {
        /*if (attacking/*is Hammer SMashed == true ) {
            attacking = true;
        } else {
            attacking = false;
        }*/
    }
}
