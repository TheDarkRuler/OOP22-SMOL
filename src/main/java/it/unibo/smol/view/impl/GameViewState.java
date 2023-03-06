package it.unibo.smol.view.impl;

import java.io.IOException;

import it.unibo.smol.view.api.WindowState;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.awt.event.*;

/**
 * Implementation of the main state, it renders the game.
 */
public class GameViewState implements WindowState {

    private static final double SCREEN_WIDTH = Screen.getPrimary().getBounds().getWidth();
    private static final double SCREEN_HEIGHT = Screen.getPrimary().getBounds().getHeight();
    private static final int PROPORTIONS = 2;

    private EventHandler EventHandler;
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Stage stage) throws IOException {
        /*final Label message = new Label("Hello, JavaFX!"); 
        message.setFont(new Font(100));
        stage.setScene(new Scene(message));
        stage.setTitle("Hello");
        stage.show();*/
        try {
            this.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start(final Stage stage) {

        this.keyEventHandler = new Key;
        final var root = new Pane();
        final var scene = new Scene(root, SCREEN_WIDTH / PROPORTIONS,
            SCREEN_HEIGHT / PROPORTIONS, Color.BLACK);
        scene.setOnKeyPressed(EventHandler.handle());
        scene.setOnKeyReleased(EventHandler);
        scene.setOnMousePressed(EventHandler);
        scene.setOnMouseMoved(EventHandler);
        scene.setOnMouseReleased(EventHandler);
        stage.setScene(scene);
        stage.show();
    }
}
