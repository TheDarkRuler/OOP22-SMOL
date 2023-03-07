package it.unibo.smol.view.api;

import javafx.stage.Stage;

/**
 * Interface that represents the concept of a Window (a container of scenes).
 */
public interface Window {
    /**
     * start a new window based on {@link WindowState}.
     * @param stage The stage where our window is running.
     */
    void launch(Stage stage);

}
