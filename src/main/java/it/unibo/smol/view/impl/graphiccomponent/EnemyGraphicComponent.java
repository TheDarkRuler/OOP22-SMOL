package it.unibo.smol.view.impl.graphiccomponent;

import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.GraphicComponent;

/**
 * The implementation of the {@link GraphicComponent} rappresenting the Enemy graphic.
 */
public class EnemyGraphicComponent extends GraphicComponent {

    private boolean moving;
    private String enemyName;

    /**
     * 
     * @param width
     * @param height
     */
    public EnemyGraphicComponent(final double width, final double height, final String enemyName) {
        super(width, height);
        this.enemyName = enemyName;
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
        switch (enemyName) {
            case "Mole":
                setImageName(LoadImgs.MOLE);
                break;
            case "Helmet_mole":
                setImageName(LoadImgs.HELM_MOLE);
                break;
            case "Angry_mole":
                setImageName(LoadImgs.MOLE);
                break;
            case "Bomb_mole":
                setImageName(LoadImgs.BOMB_MOLE);
                break;
        
            default:
                break;
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
