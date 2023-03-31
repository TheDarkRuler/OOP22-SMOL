package it.unibo.smol.view.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.smol.view.GameMap;
import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.WindowState;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * game over window impl.
 */
public class GameOverWinState implements WindowState {

    private static final int BUTTON_ANIM_DURATION = 500;

    private static Logger logger = Logger.getLogger("gameOverLogger");
    private final int finalScore;
    private final String folderName;

    /**
     * gets the final score.
     * 
     * @param currentScore
     * @param folderName
     */
    public GameOverWinState(final int currentScore, final String folderName) {
        this.finalScore = currentScore;
        this.folderName = folderName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Stage stage) throws IOException {
        try {
            this.start(stage);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "GameOverStateError::", e);
        }
    }

    /**
     * This method generate game over with javafx.
     * 
     * @param stage stage were game over will be generated.
     * @throws IOException
     * @throws Exception   Exception thrown if there is any problem, in particular
     *                     usefull to detect problems with the fxml file.
     */
    private void start(final Stage stage) throws IOException {
        Platform.runLater(() -> {
            try {
                URL url;
                url = new File("src/main/resources/layouts/GameOver.fxml").toURI().toURL();
                final Parent root = FXMLLoader.load(url);
                final Scene scene = new Scene(root, GameMap.WIDTH * GameMap.SCREEN_PROP_X - 1,
                        GameMap.HEIGHT * GameMap.SCREEN_PROP_Y - 1);
                final Button restartGame = (Button) scene.lookup("#restartGame");
                final Button closeGame = (Button) scene.lookup("#closeGame");
                final Text score = (Text) scene.lookup("#score");
                final VBox gameOverBox = (VBox) scene.lookup("#box");
                scene.setCursor(new ImageCursor(LoadImgs.getSprites(LoadImgs.ANGRY_MOLE, folderName)));
                gameOverBox.setSpacing((GameMap.BORDER_WIDTH * GameMap.SCREEN_PROP_X) / 3);
                buttonManagement(restartGame);
                buttonManagement(closeGame);
                restartGame.setOnMouseClicked(e -> {
                    new WindowImpl().launch(stage);
                });
                closeGame.setOnMouseClicked(e -> {
                    Platform.exit();
                    Runtime.getRuntime().exit(0);
                });
                score.setText("score: " + this.finalScore);
                root.setOnKeyPressed(e -> {
                    if (e.getCode().equals(KeyCode.F11)) {
                        if (stage.isFullScreen()) {
                            stage.setFullScreen(false);
                        } else {
                            stage.setFullScreen(true);
                        }
                    }
                });
                score.setFont(Font.font("wavy", FontWeight.BOLD, FontPosture.REGULAR,
                        (GameMap.BORDER_WIDTH / 3) * GameMap.SCREEN_PROP_X));
                stage.setTitle("GAME OVER :(");
                stage.setScene(scene);
                stage.setFullScreen(true);
                stage.setResizable(false);
                stage.getIcons().add(LoadImgs.getSprites(LoadImgs.LOGO, folderName));
                stage.show();
            } catch (MalformedURLException e) {
                logger.log(Level.SEVERE, "badUrlOnGameOver::", e);
            } catch (IOException e1) {
                logger.log(Level.SEVERE, "GameOverStateError::", e1);
            }
        });
    }

    private void buttonManagement(final Button btn) {
        btn.setPrefWidth(GameMap.BORDER_WIDTH * GameMap.SCREEN_PROP_X);
        btn.setPrefHeight(GameMap.BORDER_HEIGHT / 3 * GameMap.SCREEN_PROP_Y);
        //Duration = 0.5 seconds
        final RotateTransition rotateTransition = new RotateTransition(Duration.millis(BUTTON_ANIM_DURATION), btn);
        rotateTransition.setByAngle(360);
        rotateTransition.play();
    }
}
