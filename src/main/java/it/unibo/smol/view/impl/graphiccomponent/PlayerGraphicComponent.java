package it.unibo.smol.view.impl.graphiccomponent;

import it.unibo.smol.view.LoadImgs;
//import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.GraphicComponent;
/**
 * The implementation of the {@link GraphicComponent} rappresenting the Player graphic.
 */
public class PlayerGraphicComponent extends GraphicComponent {

    private boolean moving_left;
    private boolean moving_right;

    /**
     * Constructors inherited by the super-class {@link GraphicComponent}.
     * @param width : See the super-Constructor
     * @param height : See the super-Constructor
     */
    public PlayerGraphicComponent(final double width, final double height) {
        super(width, height);
        this.moving_left = false;
        this.moving_right = false;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void setAnimation() {
        //setImageName(LoadImgs.PLAYER);
        if (this.moving_left) {
            setImageName(LoadImgs.PLAYER_LEFT);
        } else if (this.moving_right) {
            setImageName(LoadImgs.PLAYER_RIGHT);
        } else {
            setImageName(LoadImgs.PLAYER);
        }
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void updateAnimation() {
        if (super.getEntity().isPresent()) {
            if (super.getEntity().orElseThrow().getPhysicsComp().orElseThrow().getX() > 0) {
                this.moving_right = true;
            } else if (super.getEntity().orElseThrow().getPhysicsComp().orElseThrow().getX() < 0) {
                this.moving_left = true;
            } else {
                this.moving_left = false;
                this.moving_right = false;
            }
        }
    }
}
