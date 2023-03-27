package it.unibo.smol.view.impl.graphiccomponent;

import it.unibo.smol.common.Constant;
import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.GraphicComponent;
/**
 * Class for the HelmetEnemy graphic.
 */
public class HelmetEnemyGraphicComponent extends GraphicComponent {

    private boolean moving;
    private boolean isHalfDead;

    /**
     * 
     * @param width
     * @param height
     */
    public HelmetEnemyGraphicComponent(final double width, final double height) {
        super(width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAnimation() {
        if (moving) {
            setImageName(LoadImgs.MOLE_DIG);
        } else {
            if (isHalfDead) {
                setImageName(LoadImgs.MOLE);
            } else {
                setImageName(LoadImgs.HELM_MOLE);
            }
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
            //System.out.println(super.getEntity().getHealthComp().orElseThrow().getCurrentHealth());
            if (super.getEntity().getHealthComp().orElseThrow().getCurrentHealth() < Constant.ENEMY_HELMET_HP) {
                isHalfDead = true;
            }
        }
    }
}
