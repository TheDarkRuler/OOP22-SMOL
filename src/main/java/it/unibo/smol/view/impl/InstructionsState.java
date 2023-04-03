package it.unibo.smol.view.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.smol.common.Constant;
import it.unibo.smol.view.GameMap;
import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.WindowState;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * A class for the instructions of the game.
 */
public final class InstructionsState implements WindowState {

    private static Logger logger = Logger.getLogger("instructionsLogger");

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Stage stage) throws IOException {
        try {
            this.start(stage);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "InstrcutionsStateError::", e);
        }
    }

   /**
    * 
    * @param stage
    * @throws IOException
    */
    private void start(final Stage stage) throws IOException {
        /*
         * Get fields initialization.
         */
        final Parent root = FXMLLoader.load(getClass().getResource("/layouts/Instructions.fxml"));
        final Scene scene = new Scene(root, GameMap.WIDTH * GameMap.SCREEN_PROP_X - 1,
                GameMap.HEIGHT * GameMap.SCREEN_PROP_Y - 1);
        // children
        final BorderPane b = (BorderPane) scene.lookup("#box");
        final VBox box = (VBox) scene.lookup("#centralBox");
        final Text title = (Text) scene.lookup("#title");
        final Button menu = (Button) scene.lookup("#menu");
        final ImageView target1 = (ImageView) scene.lookup("#target1");
        final ImageView target2 = (ImageView) scene.lookup("#target2");
        final ImageView healthBar = (ImageView) scene.lookup("#health");
        List<ImageView> list = new ArrayList<>(List.of((ImageView) scene.lookup("#WasdImage"),
                                            (ImageView) scene.lookup("#waSdImage"),
                                            (ImageView) scene.lookup("#wAsdImage"),
                                            (ImageView) scene.lookup("#wasDImage"),
                                            (ImageView) scene.lookup("#target")));
        /*
         * Set fields.
         */
        box.setSpacing(GameMap.BORDER_WIDTH / 5);
        b.getTop().maxWidth(GameMap.WIDTH);

        list.stream().forEach(l->l.setFitHeight(GameMap.SCREEN_PROP_Y * GameMap.BORDER_HEIGHT));
        list.stream().forEach(l->l.setFitWidth(GameMap.SCREEN_PROP_X * GameMap.BORDER_WIDTH));
        target1.setFitHeight(GameMap.SCREEN_PROP_Y * GameMap.BORDER_HEIGHT);
        target1.setFitWidth(GameMap.SCREEN_PROP_X * GameMap.BORDER_WIDTH * 2);
        target2.setFitHeight(GameMap.SCREEN_PROP_Y * GameMap.BORDER_HEIGHT);
        target2.setFitWidth(GameMap.SCREEN_PROP_X * GameMap.BORDER_WIDTH * 3);
        healthBar.setFitHeight(GameMap.SCREEN_PROP_Y * GameMap.BORDER_HEIGHT);
        healthBar.setFitWidth(GameMap.SCREEN_PROP_X * GameMap.BORDER_WIDTH * 3);
        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,
                GameMap.BORDER_WIDTH / 2 * GameMap.SCREEN_PROP_X));
        // buttons behaviour
        menu.setOnMouseClicked(e -> {
            new WindowImpl().launch(stage);
        });
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.F11)) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });

        scene.setCursor(new ImageCursor(LoadImgs.getSprites(LoadImgs.HAMMER, Constant.KEY_COMMON_FOLDER)));
        stage.setResizable(false);
        stage.setTitle("Instrucions");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.show();
    }
}
