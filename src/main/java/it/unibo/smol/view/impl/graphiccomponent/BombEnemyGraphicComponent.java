package it.unibo.smol.view.impl.graphiccomponent;

import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.GraphicComponent;
/**
 * Class for the BombEnemy graphic.
 */
public class BombEnemyGraphicComponent extends GraphicComponent {

    private boolean moving;

    /**
     * Constructors inherited by the super-class {@link GraphicComponent}.
     * @param width
     * @param height
     */
    public BombEnemyGraphicComponent(final double width, final double height) {
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
            setImageName(LoadImgs.BOMB_MOLE);
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
