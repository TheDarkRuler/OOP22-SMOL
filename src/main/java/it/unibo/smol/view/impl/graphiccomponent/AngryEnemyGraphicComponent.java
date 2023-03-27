package it.unibo.smol.view.impl.graphiccomponent;

import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.GraphicComponent;

/**
 * Class for the AngryEnemy graphic.
 */
public class AngryEnemyGraphicComponent extends GraphicComponent {

    private boolean moving;

    /**
     * 
     * @param width
     * @param height
     */
    public AngryEnemyGraphicComponent(final double width, final double height) {
        super(width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAnimation() {
        if (moving) {
            setImageName(LoadImgs.GROUND);
        } else {
            setImageName(LoadImgs.ANGRY_MOLE);
        }
        //setImageName(LoadImgs.MOLE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAnimation() {
        if (super.getEntity() != null) {
            if (super.getEntity().getInputComp().orElseThrow().isHittable()) {
                moving = false;
            } else {
                moving = true;
            }
        }
    }
}
