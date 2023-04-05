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
import javafx.geometry.Insets;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    * This method generate the instructions from a file FXML.
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
        final BorderPane borderPane = (BorderPane) scene.lookup("#borderpane");
        final HBox boxTitle = (HBox) scene.lookup("#hboxtitle");
        final HBox boxButton = (HBox) scene.lookup("#boxbutton");
        final VBox vboxMovement = (VBox) scene.lookup("#vboxleft");
        final VBox vboxHit = (VBox) scene.lookup("#vboxcenter");
        final VBox vboxEnemies = (VBox) scene.lookup("#boxenemies");
        final VBox vboxLife = (VBox) scene.lookup("#boxlife");
        final Text title = (Text) scene.lookup("#title");
        final Button menu = (Button) scene.lookup("#menu");
        final ImageView target1 = (ImageView) scene.lookup("#target1");
        final ImageView target2 = (ImageView) scene.lookup("#target2");
        final ImageView healthBar = (ImageView) scene.lookup("#health");
        final List<ImageView> imagesList = new ArrayList<>(List.of(
                                            (ImageView) scene.lookup("#WasdImage"),
                                            (ImageView) scene.lookup("#waSdImage"),
                                            (ImageView) scene.lookup("#wAsdImage"),
                                            (ImageView) scene.lookup("#wasDImage"),
                                            (ImageView) scene.lookup("#target"),
                                            (ImageView) scene.lookup("#normalplants"),
                                            (ImageView) scene.lookup("#choppedplants")));
        final List<ImageView> enemiesList = new ArrayList<>(List.of(
                                            (ImageView) scene.lookup("#basicmole"),
                                            (ImageView) scene.lookup("#hmole"),
                                            (ImageView) scene.lookup("#amole"),
                                            (ImageView) scene.lookup("#bmole"),
                                            (ImageView) scene.lookup("#ground")));
        /*
         * Set fields.
         */
        vboxMovement.setPadding(new Insets(10 * GameMap.SCREEN_PROP_Y, 10 * GameMap.SCREEN_PROP_X,
                                        10 * GameMap.SCREEN_PROP_Y, 100 * GameMap.SCREEN_PROP_X));
        vboxEnemies.setPadding(new Insets(10 * GameMap.SCREEN_PROP_Y, GameMap.HEIGHT / 2  * GameMap.SCREEN_PROP_X,
                                        10 * GameMap.SCREEN_PROP_Y, 10 * GameMap.SCREEN_PROP_X));
        vboxLife.setPadding(new Insets(10 * GameMap.SCREEN_PROP_Y, GameMap.HEIGHT / 2 * GameMap.SCREEN_PROP_X,
                                        10 * GameMap.SCREEN_PROP_Y, 10 * GameMap.SCREEN_PROP_X));

        borderPaneLayoutSize(borderPane);
        vBoxLayoutSize(vboxMovement);
        vBoxLayoutSize(vboxHit);
        vBoxLayoutSize(vboxEnemies);
        vBoxLayoutSize(vboxLife);
        hBoxLayoutSize(boxTitle);
        hBoxLayoutSize(boxButton);

        //resize images
        imagesList.stream().forEach(l -> l.setFitHeight(GameMap.BORDER_HEIGHT * GameMap.SCREEN_PROP_Y));
        imagesList.stream().forEach(l -> l.setFitWidth(GameMap.BORDER_HEIGHT * GameMap.SCREEN_PROP_X));

        enemiesList.stream().forEach(l -> l.setFitHeight(GameMap.BORDER_WIDTH * GameMap.SCREEN_PROP_Y / 2));
        enemiesList.stream().forEach(l -> l.setFitWidth(GameMap.BORDER_WIDTH * GameMap.SCREEN_PROP_X / 2));

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

    /**
     * Set the size of the HBox with proportion.
     * @param hbox
     */
    private void hBoxLayoutSize(final HBox hbox) {
        hbox.setPrefSize(GameMap.WIDTH * GameMap.SCREEN_PROP_X - 1, GameMap.BORDER_HEIGHT * GameMap.SCREEN_PROP_Y / 2);
        hbox.setMaxSize(GameMap.WIDTH * GameMap.SCREEN_PROP_X - 1, GameMap.BORDER_HEIGHT * GameMap.SCREEN_PROP_Y / 2);
        hbox.setMinSize(GameMap.WIDTH * GameMap.SCREEN_PROP_X - 1, GameMap.BORDER_HEIGHT * GameMap.SCREEN_PROP_Y / 2);
        hbox.autosize();
    }

    /**
     * Set the size of the VBox with proportion.
     * @param vbox
     */
    private void vBoxLayoutSize(final VBox vbox) {
        vbox.setSpacing(GameMap.SCREEN_PROP_X * GameMap.BORDER_WIDTH / 4);
        vbox.setPrefSize(GameMap.BORDER_WIDTH * GameMap.SCREEN_PROP_X, GameMap.MAP_HEIGHT * GameMap.SCREEN_PROP_Y);
        vbox.setMaxSize(GameMap.BORDER_WIDTH * GameMap.SCREEN_PROP_X, GameMap.MAP_HEIGHT * GameMap.SCREEN_PROP_Y);
        vbox.setMinSize(GameMap.BORDER_WIDTH * GameMap.SCREEN_PROP_X, GameMap.MAP_HEIGHT * GameMap.SCREEN_PROP_Y);
        vbox.autosize();
    }

    /**
     * Set the size of the BorderPane with proportion.
     * @param pane
     */
    private void borderPaneLayoutSize(final BorderPane pane) {
        pane.setPrefSize(GameMap.MAP_WIDTH * GameMap.SCREEN_PROP_X / 2, GameMap.MAP_HEIGHT * GameMap.SCREEN_PROP_Y);
        pane.setMaxSize(GameMap.MAP_WIDTH * GameMap.SCREEN_PROP_X / 2, GameMap.MAP_HEIGHT * GameMap.SCREEN_PROP_Y);
        pane.setMinSize(GameMap.MAP_WIDTH * GameMap.SCREEN_PROP_X / 2, GameMap.MAP_HEIGHT * GameMap.SCREEN_PROP_Y);
        pane.autosize();
    }
}
