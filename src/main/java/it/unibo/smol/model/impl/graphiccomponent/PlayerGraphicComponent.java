package it.unibo.smol.model.impl.graphiccomponent;

import java.io.FileNotFoundException;

import it.unibo.smol.model.api.GraphicComponent;
import it.unibo.smol.view.LoadImgs;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The implementation of the {@link GraphicComponent} rappresenting the Player graphic.
 */
public class PlayerGraphicComponent extends GraphicComponent {

    private boolean moving, attacking;

    /**
     * Constructors inherited by the super-class {@link GraphicComponent}.
     * @param width : See the super-Constructor
     * @param height : See the super-Constructor
     */
    public PlayerGraphicComponent(final double width, final double height) {
        super(width, height);
        //super.setImage(LoadImgs.getSprites(LoadImgs.PLAYER_IDLE));
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void setAnimation() {

        /*if (moving) {
            this.image = LoadImgs.getSprites(LoadImgs.PLAYER_MOVING);
        } else {
            this.image = LoadImgs.getSprites(LoadImgs.PLAYER_IDLE);
        }

        if (attacking) {
            this.image = LoadImgs.getSprites(LoadImgs.PLAYER_ATTACK);
        }*/
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void updateAnimation() {

        if(super.getEntity().getPhysicsComp().getX() == 0 &&
            super.getEntity().getPhysicsComp().getY() ==0) {
                moving = false;
            } else {
                moving = true;
            }
     
        /*if() {
            attacking = true;
        } else {
            attacking = false;
        }*/
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void render(GraphicsContext g, double x, double y) {
        g.drawImage(super.getImage(), x, y, this.getWidth(), this.getHeight());
    }
}
