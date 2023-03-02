package it.unibo.smol.view.impl;

import java.io.IOException;

import it.unibo.smol.view.api.WindowState;
import it.unibo.smol.view.api.Window;
import javafx.stage.Stage;
/**
 * class representing a Window using JavaFX.
 */
public class WindowImpl implements Window {
    private final WindowState currentState;
    private final Stage stage;

    /**
     * Constructor for WindowImpl to render the first scene {@link MenuState}.
     * @param primaryStage JavaFX stage passed were we will render the behavior.
     */
    public WindowImpl(final Stage primaryStage) {
        this.stage = primaryStage;
        this.currentState = new MenuState();
    }

    /**
     * Constructor for WindowImpl to render any scene.
     * 
     * @param primaryStage JavaFX stage passed were we will render the behavior.
     * @param state scene that we will render (state to remind the application of the state pattern)
     */
    public WindowImpl(final Stage primaryStage, final WindowState state) {
        this.stage = primaryStage;
        this.currentState = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void launch() {
        try {
            currentState.render(stage);
        } catch (IOException e) {
            System.out.println("Something went wrong " + e.toString());
        }
    } 
}
