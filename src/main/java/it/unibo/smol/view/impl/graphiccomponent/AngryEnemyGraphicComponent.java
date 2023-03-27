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
        /*if (moving) {
            setImage(LoadImgs.getSprites(LoadImgs.MOLE));
        } else {
            setImage(LoadImgs.getSprites(LoadImgs.MOLE));
        }*/
        setImageName(LoadImgs.ANGRY_MOLE);
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
