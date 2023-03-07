package it.unibo.smol.view.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.smol.view.api.WindowState;
import it.unibo.smol.view.api.Window;
import javafx.stage.Stage;
/**
 * class representing a Window using JavaFX.
 */
public class WindowImpl implements Window {
    private final WindowState currentState;
    private static Logger logger = Logger.getLogger("myLog");
    /**
     * Constructor for WindowImpl to render the first scene {@link MenuState}.
     */
    public WindowImpl() {
        this.currentState = new MenuState();
    }

    /**
     * Constructor for WindowImpl to render any scene.
     * 
     * @param state scene that we will render (state to remind the application of the state pattern)
     */
    public WindowImpl(final WindowState state) {
        this.currentState = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void launch(final Stage primaryStage) {
        try {
            currentState.render(primaryStage);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "WindowImplError::", e);
        }
    } 
}
