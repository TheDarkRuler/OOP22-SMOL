package it.unibo.smol.core;

import javafx.stage.Stage;

/**
 * Functional interface for the Implementation of a GameEngine.
 */
public interface GameEngine {

    /**
     * Awake the {@link GameLoop} thread with a {@link #notify()}.
     * @throws IllegalStateException if the thread was already running when the method was called
     */
    void run();

    /**
     * Stop the {@link GameLoop} thread with a {@link #wait()}.
     * @throws IllegalStateException if the thread was already stopped when the method was called
     * @throws InterruptedException if any thread interrupted the current thread before or
     *         while the current thread was waiting.
     */
    void stop() throws InterruptedException;

    /**
     * Test if the {@link GameLoop} Thread is running.
     * @return {@code True} if is running; {@code False} otherwise
     */
    boolean isRunning();

    /**
     * This method create a and start a new {@link GameLoop}.
     * @param primaryStage : The stage of the Current view
     */
    void init(Stage primaryStage);

}
