package it.unibo.smol.model.impl.graphiccomponent;

import it.unibo.smol.model.api.Entity;
import it.unibo.smol.model.api.GraphicComponent;
import it.unibo.smol.view.LoadImgs;

/**
 * The implementation of the {@link GraphicComponent} rappresenting the Plants graphic.
 */
public class LifePlantsGraphicComponent extends GraphicComponent{

    private boolean isHalfDead;

    public LifePlantsGraphicComponent(final Entity entity, final double width, final double height) {
        super(entity, width, height);
    }

    @Override
    public void setAnimation() {
        if (isHalfDead) {
            super.setImage(LoadImgs.getSprites(LoadImgs.MOLE));
        } else {
            super.setImage(LoadImgs.getSprites(LoadImgs.MOLE));
        }
    }

    @Override
    public void updateAnimation() {
        if (super.getEntity().getHealthComp().get().getCurrentHealth() <= 10/*meta vita */) {
            isHalfDead = true;
        } else {
            isHalfDead = false;
        }
    }
    
}
