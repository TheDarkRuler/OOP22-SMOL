package it.unibo.smol.view;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import it.unibo.smol.common.Constant;
import javafx.scene.image.Image;
/**
 * Utility class for load images.
 */
public final class LoadImgs {

    /**name of the folders in which are the sprites to use. */
    private static final Map<String, String> SKIN_FOLDER = new HashMap<>(Map.of(Constant.KEY_PIXEL_SKINS, "pixel_moles/",
        Constant.KEY_VECTORIAL_SKINS, "vectorial_moles/"));

    /**basic mole gif. */
    public static final String MOLE = "mole1.gif";
    /**player gif. */
    public static final String PLAYER = "player.gif";
    /**player moving right gif. */
    public static final String PLAYER_RIGHT = "player_right.gif";
    /**player moving left gif. */
    public static final String PLAYER_LEFT = "player_left.gif";
    /**Helmet mole gif. */
    public static final String HELM_MOLE = "Helmet_mole.gif";
    /**Bomb mole gif. */
    public static final String BOMB_MOLE = "Bomb_mole.gif";
    /**target of the weapon png. */
    public static final String W_TARGET = "weapon_target.png";
    /**angry mole gif. */
    public static final String ANGRY_MOLE = "Angry_mole.gif";
    /**helmet mole after it got an hit gif. */
    public static final String HELM_HIT_MOLE = "Hitted_Helmet_mole.gif";
    /**plants png. */
    public static final String LIFE_PLANTS = "Life_plants.png";
    /**ground gif. */
    public static final String GROUND = "Ground.gif";
    /**background png. */
    public static final String BACKGROUND = "Background.png";
    /**hammer image for the cursor. */
    public static final String HAMMER = "hammer.png";
    /**icon for the game. */
    public static final String LOGO = "logo.png";

    private LoadImgs() throws UnsupportedEncodingException {
        throw new UnsupportedEncodingException("This is a utily class");
    }

    /**
     * This method takes a file name as input and tries to return the image.
     * Create a file input stream with the filename 
     * @param filename
     * @param folderName 
     * @return the image loaded from the input stream
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static Image getSprites(final String filename, final String folderName) {
        Image image = null;
        try (FileInputStream path = new FileInputStream("src/main/resources/images/" 
            + LoadImgs.SKIN_FOLDER.get(folderName) + filename);) {
            image = new Image(path);
        } catch (IllegalArgumentException e) {
            Logger.getLogger(LoadImgs.class.getName()).info("Illegal Argument");
        } catch (IOException e1) {
            Logger.getLogger(LoadImgs.class.getName()).info("IOException");
        }
        return image;
    }
}
