package it.unibo.smol.view.impl.graphiccomponent;

import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.GraphicComponent;

/**
 * Class for the AngryEnemy graphic.
 */
public class AngryEnemyGraphicComponent extends GraphicComponent {

    private boolean moving;

    /**
     * Constructors inherited by the super-class {@link GraphicComponent}.
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
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAnimation() {
        if (super.getEntity() != null) {
            if (super.getEntity().orElseThrow().getInputComp().orElseThrow().isHittable()) {
                moving = false;
            } else {
                moving = true;
            }
        }
    }
}
