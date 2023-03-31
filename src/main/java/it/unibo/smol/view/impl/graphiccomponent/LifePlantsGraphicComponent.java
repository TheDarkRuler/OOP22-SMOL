package it.unibo.smol.view.impl.graphiccomponent;

//import it.unibo.smol.common.Constant;
import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.GraphicComponent;

/**
 * The implementation of the {@link GraphicComponent} rappresenting the Plants graphic.
 */
public class LifePlantsGraphicComponent extends GraphicComponent {

    //private boolean isHalfDead;

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
        /*if (isHalfDead) {
            super.setImageName(LoadImgs.LIFE_PLANTS);
        } else {
            super.setImageName(LoadImgs.LIFE_PLANTS);
        }*/
        super.setImageName(LoadImgs.LIFE_PLANTS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAnimation() {
        /*if (super.getEntity().isPresent()) {
            if (super.getEntity().orElseThrow().getHealthComp().get().getCurrentHealth() <= Constant.HEALTH_HP / 2) {
                isHalfDead = true;
            } else {
                isHalfDead = false;
            }
        }*/
    }
}
