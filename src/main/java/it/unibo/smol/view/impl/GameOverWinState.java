package it.unibo.smol.view.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.smol.view.GameMap;
import it.unibo.smol.view.api.WindowState;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameOverWinState implements WindowState {
    private static Logger logger = Logger.getLogger("gameOverLogger");

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
        final var root = new Pane();
        final var scene = new Scene(root, GameMap.MAP_WIDTH, GameMap.MAP_HEIGHT, Color.AQUA);
        final Button restart = new Button();
        restart.setText("RESTART");
        restart.setTranslateX(GameMap.MAP_WIDTH / 2);
        restart.setTranslateY(GameMap.MAP_HEIGHT / 2);
        restart.setPrefWidth(GameMap.BORDER_WIDTH);
        restart.setPrefHeight(GameMap.BORDER_WIDTH / 2);
        restart.setOnMouseClicked(e -> {
            new WindowImpl(new MenuState()).launch(stage);;
        });
        root.getChildren().add(restart);
        stage.setTitle("Game Over :(");
        stage.setScene(scene);
        stage.show();
    }
}
