package it.unibo.smol.view.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.smol.model.api.Entity;
import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.GraphicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GraphicsDraw {

    private final GraphicsContext g;
    private Map<String, Image> images;

    public GraphicsDraw(final GraphicsContext g) {
        this.g = g;
        this.images = new HashMap<>();
        storeImages();
    }

    private void storeImages() {
        images.put(LoadImgs.ANGRY_MOLE, LoadImgs.getSprites(LoadImgs.ANGRY_MOLE));
        images.put(LoadImgs.BOMB_MOLE, LoadImgs.getSprites(LoadImgs.BOMB_MOLE));
        images.put(LoadImgs.HELM_MOLE, LoadImgs.getSprites(LoadImgs.HELM_MOLE));
        images.put(LoadImgs.MOLE, LoadImgs.getSprites(LoadImgs.MOLE));
        images.put(LoadImgs.PLAYER, LoadImgs.getSprites(LoadImgs.PLAYER));
        images.put(LoadImgs.WORLD_IMG, LoadImgs.getSprites(LoadImgs.WORLD_IMG));
        images.put(LoadImgs.W_TARGET, LoadImgs.getSprites(LoadImgs.W_TARGET));
    
    }

    public void drawSprite(final String imageName, final Entity entity, final GraphicComponent graphicComponent) {
        g.drawImage(images.get(imageName),
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
