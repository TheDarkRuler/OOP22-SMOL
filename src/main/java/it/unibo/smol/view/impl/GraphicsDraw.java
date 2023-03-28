package it.unibo.smol.view.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.smol.model.api.Entity;
import it.unibo.smol.view.GameMap;
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
        images.put(LoadImgs.HELM_HIT_MOLE, LoadImgs.getSprites(LoadImgs.HELM_HIT_MOLE) );
        images.put(LoadImgs.MOLE, LoadImgs.getSprites(LoadImgs.MOLE));
        images.put(LoadImgs.PLAYER, LoadImgs.getSprites(LoadImgs.PLAYER));
        images.put(LoadImgs.WORLD_IMG, LoadImgs.getSprites(LoadImgs.WORLD_IMG));
        images.put(LoadImgs.W_TARGET, LoadImgs.getSprites(LoadImgs.W_TARGET));
        images.put(LoadImgs.GROUND, LoadImgs.getSprites(LoadImgs.GROUND));
        images.put(LoadImgs.GROUND, LoadImgs.getSprites(LoadImgs.GROUND));
        images.put(LoadImgs.LIFE_PLANTS, LoadImgs.getSprites(LoadImgs.LIFE_PLANTS));
    
    }

    public void drawSprite(final String imageName, final Entity entity, final GraphicComponent graphicComponent) {
        System.out.println("Proporzioni x: "+GameMap.SCREEN_PROP_X);
        System.out.println("Proporzioni y: "+GameMap.SCREEN_PROP_Y);
        System.out.println(imageName);
        System.out.println("\t\t^"+entity.getType());
        g.drawImage(images.get(imageName),
            ((entity.getCurrentX() - graphicComponent.getWidth() / 2)*GameMap.SCREEN_PROP_X),
            ((entity.getCurrentY() - graphicComponent.getHeight() / 2)*GameMap.SCREEN_PROP_Y),
            (graphicComponent.getWidth()*GameMap.SCREEN_PROP_X),
            (graphicComponent.getHeight()*GameMap.SCREEN_PROP_Y));
    }

    public void undrawSprite(final Entity entity, final GraphicComponent graphicComponent) {
        this.g.clearRect(
            entity.getCurrentX() - graphicComponent.getWidth() / 2,
            entity.getCurrentY() - graphicComponent.getHeight() / 2,
            graphicComponent.getWidth(),
            graphicComponent.getHeight());
    }
    
}
