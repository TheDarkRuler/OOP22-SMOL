package it.unibo.smol.view.impl.graphiccomponent;

import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.GraphicComponent;

/**
 * The implementation of the {@link GraphicComponent} rappresenting the Plants graphic.
 */
public class LifePlantsGraphicComponent extends GraphicComponent {

    private boolean isHalfDead;

    /**
     * 
     * @param width
     * @param height
     */
    public LifePlantsGraphicComponent(final double width, final double height) {
        super(width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAnimation() {
        if (isHalfDead) {
            super.setImageName(LoadImgs.PLAYER);
        } else {
            super.setImageName(LoadImgs.PLAYER);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAnimation() {
        /*if (super.getEntity() != null) {
            if (super.getEntity().getHealthComp().get().getCurrentHealth() <= 10/*meta vita ) {
                isHalfDead = true;
            } else {
                isHalfDead = false;
            }
        }*/
    }
}
