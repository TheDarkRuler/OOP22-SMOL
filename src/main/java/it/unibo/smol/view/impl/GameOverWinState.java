package it.unibo.smol.view.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.smol.view.GameMap;
import it.unibo.smol.view.api.WindowState;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

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
        Platform.runLater(()->{
            final var root = new Pane();
            final var scene = new Scene(root, GameMap.WIDTH, GameMap.HEIGHT, Color.AQUA);
            final Button restart = new Button();
            final Button close = new Button();
            setupButton(close, "CLOSE");
            setupButton(restart, "RESTART");
            restart.setText("RESTART");
            restart.setOnMouseClicked(e -> {
                new WindowImpl().launch(stage);;
            });
            close.setOnMouseClicked(e -> {
                Platform.exit();
                System.exit(0);
            });
            restart.setLayoutX(GameMap.WIDTH / 2 - GameMap.BORDER_WIDTH * 2);
            close.setLayoutX(GameMap.WIDTH / 2 );
            root.setOnKeyPressed(e -> {
                if (e.getCode().equals(KeyCode.F11)) {
                    if (stage.isFullScreen()) {
                        stage.setFullScreen(false);
                    } else {
                        stage.setFullScreen(true);
                    }
                }
            });
            root.getChildren().add(restart);
            root.getChildren().add(close);
            stage.setTitle("Game Over :(");
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        });
    }

    private void setupButton(final Button btn, final String btnName) {
        btn.setText(btnName);
        btn.setLayoutY(GameMap.HEIGHT / 2 + GameMap.BORDER_HEIGHT);
        btn.setPrefWidth(GameMap.BORDER_WIDTH);
        btn.setPrefHeight(GameMap.BORDER_WIDTH / 4);

    }
}
