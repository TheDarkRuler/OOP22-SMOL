package it.unibo.smol.view.impl;

import it.unibo.smol.model.api.Entity;
import it.unibo.smol.view.api.GraphicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GraphicsDraw {

    private final GraphicsContext g;

    public GraphicsDraw(final GraphicsContext g) {
        this.g = g;
    }

    public void drawSprite(final Image image, final Entity entity, final GraphicComponent graphicComponent) {
        g.drawImage(image,
            entity.getCurrentX() - graphicComponent.getWidth() / 2,
            entity.getCurrentY() - graphicComponent.getHeight() / 2,
            graphicComponent.getWidth(),
            graphicComponent.getHeight());
    }

    public void undrawSprite(final Entity entity, final GraphicComponent graphicComponent) {
        this.g.clearRect(
            entity.getCurrentX() - graphicComponent.getWidth() / 2,
            entity.getCurrentY() - graphicComponent.getHeight() / 2,
            graphicComponent.getWidth(),
            graphicComponent.getHeight());
    }
    
}
