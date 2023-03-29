package it.unibo.smol.view.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.smol.view.GameMap;
import it.unibo.smol.view.api.WindowState;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameOverWinState implements WindowState {
    private static Logger logger = Logger.getLogger("gameOverLogger");
    private final int finalScore;
    
    public GameOverWinState(final int currentScore) {
        this.finalScore = currentScore;
    }
    //TODO delete me on production
    public GameOverWinState() {
        this.finalScore = 23500;
    }
    @Override
    public void render(Stage stage) throws IOException {
        try {
            this.start(stage);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "GameOverStateError::", e);
        }
    }
    /**
     * This method generate game over with javafx.
     * 
     * @param primaryStage stage were game over will be generated.
     * @throws IOException
     * @throws Exception Exception thrown if there is any problem, in particular usefull to detect problems with the fxml file.
     */
    private void start(final Stage stage) throws IOException {
        Platform.runLater(() -> {
            try {
                URL url;
                url = new File("src/main/resources/layouts/GameOver.fxml").toURI().toURL();
                final Parent root = FXMLLoader.load(url);
                final Scene scene = new Scene(root, GameMap.MAP_WIDTH, GameMap.MAP_HEIGHT);
                final Button restartGame = (Button) scene.lookup("#restartGame");
                final Button closeGame = (Button) scene.lookup("#closeGame");
                final VBox gameOverBox = (VBox) scene.lookup("#box");
                try {
                    Image moleCursour = new Image(new FileInputStream("src/main/resources/images/Angry_mole.gif"));
                    
                    scene.setCursor(new ImageCursor(moleCursour));
                } catch (FileNotFoundException e) {
                    Logger.getLogger(MenuState.class.getName()).info("Illegal Argument");
                }
                gameOverBox.setSpacing(GameMap.BORDER_WIDTH / 3);
                buttonManagement(restartGame);
                buttonManagement(closeGame);
                restartGame.setOnMouseClicked(e -> {
                    new WindowImpl().launch(stage);
                });
                closeGame.setOnMouseClicked(e -> {
                    Platform.exit();
                    System.exit(0);
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
                stage.setTitle("GAME OVER :(");
                stage.setScene(scene);
                stage.setFullScreen(true);
                stage.show();
            } catch (MalformedURLException e) {
                logger.log(Level.SEVERE, "badUrlOnGameOver::", e);
            } catch (IOException e1) {
                logger.log(Level.SEVERE, "GameOverStateErroraaa::", e1);
            }
        });
    }

    private void buttonManagement(Button btn) {
        btn.setPrefWidth(GameMap.BORDER_WIDTH);
        btn.setPrefHeight(GameMap.BORDER_WIDTH / 3);
        //Duration = 2.5 seconds
        Duration duration = Duration.millis(500);
        RotateTransition rotateTransition = new RotateTransition(duration, btn);
        rotateTransition.setByAngle(360);
        rotateTransition.play();
    }
}
