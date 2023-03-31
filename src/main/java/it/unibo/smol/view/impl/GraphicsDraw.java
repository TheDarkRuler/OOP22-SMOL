package it.unibo.smol.view.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.smol.model.api.Entity;
import it.unibo.smol.view.GameMap;
import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.GraphicComponent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * class that draws the images.
 */
public class GraphicsDraw {

    private final GraphicsContext g;
    private final Map<String, Image> images;

    /**
     * constructor that gets the graphicContext used to draw and stores the images.
     * 
     * @param g
     * @param folderName
     */
    public GraphicsDraw(final Optional<GraphicsContext> g, final String folderName) {
        this.g = g.orElseThrow();
        this.images = new HashMap<>();
        storeImages(folderName);
    }

    /**
     * stores the images.
     * @param folderName set folder name
     */
    private void storeImages(final String folderName) {
        images.put(LoadImgs.ANGRY_MOLE, LoadImgs.getSprites(LoadImgs.ANGRY_MOLE, folderName));
        images.put(LoadImgs.BOMB_MOLE, LoadImgs.getSprites(LoadImgs.BOMB_MOLE, folderName));
        images.put(LoadImgs.HELM_MOLE, LoadImgs.getSprites(LoadImgs.HELM_MOLE, folderName));
        images.put(LoadImgs.HELM_HIT_MOLE, LoadImgs.getSprites(LoadImgs.HELM_HIT_MOLE, folderName));
        images.put(LoadImgs.MOLE, LoadImgs.getSprites(LoadImgs.MOLE, folderName));
        images.put(LoadImgs.PLAYER, LoadImgs.getSprites(LoadImgs.PLAYER, folderName));
        images.put(LoadImgs.W_TARGET, LoadImgs.getSprites(LoadImgs.W_TARGET, folderName));
        images.put(LoadImgs.GROUND, LoadImgs.getSprites(LoadImgs.GROUND, folderName));
        images.put(LoadImgs.LIFE_PLANTS, LoadImgs.getSprites(LoadImgs.LIFE_PLANTS, folderName));
        images.put(LoadImgs.PLAYER_LEFT, LoadImgs.getSprites(LoadImgs.PLAYER_LEFT, folderName));
        images.put(LoadImgs.PLAYER_RIGHT, LoadImgs.getSprites(LoadImgs.PLAYER_RIGHT, folderName));
    }

    /**
     * draws an image from the previous stored images.
     * 
     * @param entity           the entity to draw
     * @param graphicComponent the graphic component of the entity to draw
     */
    public void drawSprite(final Entity entity, final GraphicComponent graphicComponent) {
        g.drawImage(images.get(graphicComponent.getImageName()),
                (entity.getCurrentX() - graphicComponent.getWidth() / 2) * GameMap.SCREEN_PROP_X,
                (entity.getCurrentY() - graphicComponent.getHeight() / 2) * GameMap.SCREEN_PROP_Y,
                graphicComponent.getWidth() * GameMap.SCREEN_PROP_X,
                graphicComponent.getHeight() * GameMap.SCREEN_PROP_Y);
    }
}
