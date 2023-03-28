package it.unibo.smol.view.impl;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.net.URL;

import it.unibo.smol.core.GameEngine;
import it.unibo.smol.core.GameEngineImpl;
import it.unibo.smol.view.GameMap;
import it.unibo.smol.view.api.WindowState;
import javafx.animation.RotateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Implementation of the menu state, it renders the menu.
 */
public class MenuState implements WindowState {
    private static Logger logger = Logger.getLogger("menuLogger");
    private final GameEngine gameEngine = new GameEngineImpl();
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Stage primaryStage) throws IOException {
        try {
            this.start(primaryStage);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "MenuStateError::", e);
        }
    }
    /**
     * This method generate the menu from a file FXML.
     * 
     * @param primaryStage stage were menu will be generated.
     * @throws IOException
     * @throws Exception Exception thrown if there is any problem, in particular usefull to detect problems with the fxml file.
     */
    private void start(final Stage primaryStage) throws IOException {
        final URL url = new File("src/main/resources/layouts/Menu.fxml").toURI().toURL();
        final Parent root = FXMLLoader.load(url);
        final Scene scene = new Scene(root, GameMap.MAP_WIDTH, GameMap.MAP_HEIGHT);
        final Button startGame = (Button) scene.lookup("#start");
        final VBox menuBox = (VBox) scene.lookup("#box");
        menuBox.setSpacing(GameMap.BORDER_WIDTH / 5);
        startGame.setOnMouseClicked(e -> {
            gameEngine.init(primaryStage);
        });
        final Button gameOver = (Button) scene.lookup("#gameOver");
        gameOver.setOnMouseClicked(e -> {
            new WindowImpl(new GameOverWinState()).launch(primaryStage);
            //System.out.println("there is nothing there");
        });
        buttonManagement(startGame);
        buttonManagement(gameOver);
        primaryStage.setTitle("Start Menu :)");
        primaryStage.setScene(scene);
        primaryStage.setX(GameMap.WIDTH / 2 - GameMap.MAP_WIDTH / 2);
        primaryStage.setY(GameMap.HEIGHT / 2 - GameMap.MAP_HEIGHT / 2);
        primaryStage.show();
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
