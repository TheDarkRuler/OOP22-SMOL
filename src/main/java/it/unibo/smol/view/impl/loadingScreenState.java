package it.unibo.smol.view.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.smol.view.api.WindowState;
import javafx.stage.Stage;

public class loadingScreenState implements WindowState {
    private static Logger logger = Logger.getLogger("menuLogger");
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(Stage stage) throws IOException {
        try {
            this.start(stage);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "MenuStateError::", e);
        }
    }
    /**
     * This method generate loading screen with javafx.
     * 
     * @param stage stage were loading screen will be generated.
     * @throws IOException
     */
    private void start(final Stage primaryStage) throws IOException {

    }
    
}
