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

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
        startGame.setOnMouseClicked(e -> {
            gameEngine.init(primaryStage);
        });
        primaryStage.setTitle("Start Menu :)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
