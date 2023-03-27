package it.unibo.smol.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import javafx.scene.image.Image;
/**
 * Utility class for load images.
 */
public final class LoadImgs {
  /**
   * file name of images.
   */
    public static final String WORLD_IMG = "garden.jpg";
    public static final String MOLE = "mole1.gif";
    public static final String PLAYER = "player.gif";
    public static final String HELM_MOLE = "Helmet_mole.gif";
    public static final String BOMB_MOLE = "Bomb_mole.gif";
    public static final String W_TARGET = "weapon_target.png";
    public static final String ANGRY_MOLE = "Angry_mole.gif";
    public static final String HELM_HIT_MOLE = "Hitted_Helmet_mole.gif";
    public static final String LIFE_PLANTS = "Life_plants.png";
    public static final String GROUND = "Ground.gif";

    private LoadImgs() throws UnsupportedEncodingException {
        throw new UnsupportedEncodingException("This is a utily class");
    }

    /**
     * This method takes a file name as input and tries to return the image.
     * Create a file input stream with the filename 
     * @param filename 
     * @return the image loaded from the input stream
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static Image getSprites(final String filename) {
        Image image = null;
        try (FileInputStream path = new FileInputStream("src/main/resources/images/" + filename);) {
            image = new Image(path);
        } catch (IllegalArgumentException e) {
            Logger.getLogger(LoadImgs.class.getName()).info("Illegal Argument");
        } catch (IOException e1) {
            Logger.getLogger(LoadImgs.class.getName()).info("IOException");
        }
        return image;
    }
}
