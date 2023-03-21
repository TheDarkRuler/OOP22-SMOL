package it.unibo.smol.model.impl.graphiccomponent;

import it.unibo.smol.model.api.GraphicComponent;
import it.unibo.smol.view.LoadImgs;
import javafx.fxml.LoadException;
import javafx.scene.canvas.GraphicsContext;

/**
 * The implementation of the {@link GraphicComponent} rappresenting the Weapon graphic.
 */
public class WeaponGraphicComponent extends GraphicComponent{

    public WeaponGraphicComponent(double width, double height) {
        super(width, height);
        //super.setImage(LoadImgs.getSprites(LoadImgs.));
    }

    @Override
    public void render(GraphicsContext g, double x, double y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }

    @Override
    public void setAnimation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAnimation'");
    }

    @Override
    public void updateAnimation() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAnimation'");
    }
    
}
