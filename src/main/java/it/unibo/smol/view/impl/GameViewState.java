package it.unibo.smol.view.impl;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import it.unibo.smol.controller.input.KeyInputs;
import it.unibo.smol.controller.input.MouseInputs;
import it.unibo.smol.view.api.GameMap;
import it.unibo.smol.view.api.WindowState;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Implementation of the main state, it renders the game.
 */
public class GameViewState implements WindowState {
    private static Logger logger = Logger.getLogger("myLog");

    private GameMap map;

    /**
     * constructor for Game View window state.
     */
    public GameViewState() {
        this.map = new GameMapImpl();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Stage stage) throws IOException {
        try {
            this.start(stage);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "GameViewError::", e);
        }
    }

    private void start(final Stage stage) throws IOException {
        final EventHandler<KeyEvent> keyEventHandler = new KeyInputs();
        final EventHandler<MouseEvent> mouseEventHandler = new MouseInputs();
        final var root = new Pane();
        final var scene = new Scene(root, map.getWidth(),
            map.getHeight(), Color.BLACK);

        scene.setOnKeyPressed(keyEventHandler);
        scene.setOnKeyReleased(keyEventHandler);
        scene.setOnMouseMoved(mouseEventHandler);
        scene.setOnMousePressed(mouseEventHandler);
        scene.setOnMouseReleased(mouseEventHandler);
        scene.setOnMouseDragged(mouseEventHandler);
        scene.setOnMouseEntered(mouseEventHandler);
        stage.setScene(scene);
        stage.show();
    }
}
