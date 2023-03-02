package it.unibo.smol;

import it.unibo.smol.view.*;
import it.unibo.smol.view.impl.WindowImpl;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX application of the game.
 */
public class SmolApplication extends Application {

    @Override
    public void start(final Stage primaryStage) {
        new WindowImpl(primaryStage).launch();
    }
}
