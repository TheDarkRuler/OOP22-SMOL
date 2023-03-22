package it.unibo.smol.model.impl.graphiccomponent;

import it.unibo.smol.model.api.GraphicComponent;
import it.unibo.smol.view.LoadImgs;

/**
 * The implementation of the {@link GraphicComponent} rappresenting the Enemy graphic.
 */
public class EnemyGraphicComponent extends GraphicComponent {

    private boolean moving;

    /**
     * 
     * @param width
     * @param height
     */
    public EnemyGraphicComponent(final double width, final double height) {
        super(width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAnimation() {
        if (moving) {
            setImage(LoadImgs.getSprites(LoadImgs.MOLE));
        } else {
            setImage(LoadImgs.getSprites(LoadImgs.MOLE));
        }
    }

    /**
     * {@inheritDoc}
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
        }
    }
}
