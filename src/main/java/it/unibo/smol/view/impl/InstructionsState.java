package it.unibo.smol.view.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.smol.view.GameMap;
import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.WindowState;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * A class for the instructions of the game.
 */
public class InstructionsState implements WindowState{

    private static Logger logger = Logger.getLogger("instructionsLogger");

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Stage stage) throws IOException {
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
        final URL url = new File("src/main/resources/layouts/Menu.fxml").toURI().toURL();
        final Parent root = FXMLLoader.load(url);
        final Scene scene = new Scene(root, GameMap.WIDTH * GameMap.SCREEN_PROP_X - 1,
                GameMap.HEIGHT * GameMap.SCREEN_PROP_Y - 1);
        final VBox menuBox = (VBox) scene.lookup("#box");
        // children
        final Text title = (Text) scene.lookup("#title");
        final Button startGame = (Button) scene.lookup("#start");
        final Button gameOver = (Button) scene.lookup("#gameOver");
        final Button quitGame = (Button) scene.lookup("#quit");
        final MenuButton dropDownMenu = (MenuButton) scene.lookup("#dropDown");

        /*
         * Set fields.
         */
        title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,
                GameMap.BORDER_WIDTH * GameMap.SCREEN_PROP_X));
        menuBox.setSpacing(GameMap.BORDER_WIDTH / 3);

        // buttons behaviour
        gameOver.setOnMouseClicked(e -> {
            new WindowImpl(new InstructionsState()).launch(stage);
        });
        quitGame.setOnMouseClicked(e -> {
            Platform.exit();
            Runtime.getRuntime().exit(0);
        });
        root.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.F11)) {
                if (stage.isFullScreen()) {
                    stage.setFullScreen(false);
                } else {
                    stage.setFullScreen(true);
                }
            }
        });

        /*
         * Fields attachment.
         
        buttonManagement(startGame);
        buttonManagement(gameOver);
        buttonManagement(quitGame);
        dropDownMenuManagement(dropDownMenu);*/
        stage.setResizable(false);
        stage.setTitle("Start Menu :)");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
        stage.show();
    }
}
