package it.unibo.smol.view.impl.graphiccomponent;

import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.GraphicComponent;
/**
 * The implementation of the {@link GraphicComponent} rappresenting the Weapon graphic.
 */
public class WeaponGraphicComponent extends GraphicComponent {

    //private boolean attacking;

    /**
     * Constructors inherited by the super-class {@link GraphicComponent}.
     * @param width
     * @param height
     */
    public WeaponGraphicComponent(final double width, final double height) {
        super(width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAnimation() {
        super.setImageName(LoadImgs.W_TARGET);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAnimation() {
        //not used by this component.
    }
}
