package it.unibo.smol.view.impl;

import java.io.IOException;

import it.unibo.smol.view.api.WindowState;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Implementation of the main state, it renders the game.
 */
public class GameViewState implements WindowState {
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Stage stage) throws IOException {
        final Label message = new Label("Hello, JavaFX!"); 
        message.setFont(new Font(100));
        stage.setScene(new Scene(message));
        stage.setTitle("Hello");
        stage.show();
    }
}
