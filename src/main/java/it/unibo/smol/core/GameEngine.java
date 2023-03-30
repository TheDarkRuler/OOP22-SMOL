package it.unibo.smol.core;

import javafx.stage.Stage;

/**
 * Functional interface for the Implementation of a GameEngine.
 */
public interface GameEngine {
    /**
     * This method create a and start a new {@link GameLoop}.
     * @param primaryStage : The stage of the Current view
     */
    void init(Stage primaryStage);

}
