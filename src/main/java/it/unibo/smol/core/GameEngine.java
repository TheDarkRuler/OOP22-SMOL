package it.unibo.smol.core;

import it.unibo.smol.model.api.GameState;
import it.unibo.smol.model.api.World;
import it.unibo.smol.view.impl.GameViewState;

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
     * @param w the world
     * @param gs the GameState
     * @param gv the GameView
     */
    void init(World w, GameState gs, GameViewState gv);

}
