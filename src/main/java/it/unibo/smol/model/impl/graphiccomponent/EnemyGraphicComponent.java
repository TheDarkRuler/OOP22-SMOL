package it.unibo.smol.model.impl.graphiccomponent;

import it.unibo.smol.model.api.GraphicComponent;
import it.unibo.smol.view.LoadImgs;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
    public EnemyGraphicComponent(double width, double height) {
        super(width, height);
        //super.setImage(LoadImgs.getSprites(LoadImgs.MOLE_MOVING));
    }
    
    /**
     * {@inheritDoc}.
     */
    @Override
    public void render(GraphicsContext g, double x, double y) {
        g.drawImage(super.getImage(), x, y, this.getWidth(), this.getHeight());
    }

    @Override
    public void setAnimation() {
        /*if (moving) {
            this.image = LoadImgs.getSprites(LoadImgs.MOLE_MOVING);
        } else {
            this.image = LoadImgs.getSprites(LoadImgs.MOLE_IDLE);
        }*/
    }

    @Override
    public void updateAnimation() {
        if(super.getEntity().getPhysicsComp().getX() == 0 &&
            super.getEntity().getPhysicsComp().getY() ==0) {
                moving = false;
            } else {
                moving = true;
            }
    }
    
}
