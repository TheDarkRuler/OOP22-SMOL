package it.unibo.smol.view.api;

import java.io.IOException;
import javafx.stage.Stage;

/**
 * Interface where the implementation decides the behavior of the window.
 */

public interface WindowState {
    /**
     * Method that initialize the stage.
     * 
     * @param stage The stage where our game is running.
     * @throws IOException Exeption if the stage can't be rendered.
     */
    void render(Stage stage) throws IOException;

}
