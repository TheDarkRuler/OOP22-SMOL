package it.unibo.smol.view.impl.graphiccomponent;

import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.GraphicComponent;
/**
 * The implementation of the {@link GraphicComponent} rappresenting the Player graphic.
 */
public class PlayerGraphicComponent extends GraphicComponent {

    private boolean movingRight;
    private boolean movingLeft;

    /**
     * Constructors inherited by the super-class {@link GraphicComponent}.
     * @param width : See the super-Constructor
     * @param height : See the super-Constructor
     */
    public PlayerGraphicComponent(final double width, final double height) {
        super(width, height);
        this.movingLeft = false;
        this.movingRight = false;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void setAnimation() {
        if (this.movingLeft) {
            setImageName(LoadImgs.PLAYER_LEFT);
        } else if (this.movingRight) {
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
                this.movingLeft = false;
                this.movingRight = true;
            } else if (super.getEntity().orElseThrow().getPhysicsComp().orElseThrow().getX() < 0) {
                this.movingRight = false;
                this.movingLeft = true;
            } else {
                this.movingLeft = false;
                this.movingRight = false;
            }
        }
    }
}
