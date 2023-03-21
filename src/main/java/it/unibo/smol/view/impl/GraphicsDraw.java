package it.unibo.smol.view.impl;

import it.unibo.smol.model.api.Entity;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GraphicsDraw {

    private final GraphicsContext g;

    public GraphicsDraw(final GraphicsContext g) {
        this.g = g;
    }

    public void drawSprite(final Image image, final Entity entity) {
        g.drawImage(image,
            entity.getCurrentX() - entity.getGraphicComponent().getWidth() / 2,
            entity.getCurrentY() - entity.getGraphicComponent().getHeight() / 2,
            entity.getGraphicComponent().getWidth(),
            entity.getGraphicComponent().getHeight());
    }
    
}
