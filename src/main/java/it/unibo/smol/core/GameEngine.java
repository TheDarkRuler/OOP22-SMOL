package it.unibo.smol.core;

/**
 * This class is the engine of the game.
 * The purpose of the engine is to control the {@link GameLoop} thread
 */
public class GameEngine {

    private GameLoop gameLoop;

    /**
     * Rappresent the state of the {@link GameLoop}.
     * {@code True} if is running; {@code False} otherwise
     */
    private boolean state;

    /**
     * Awake the {@link GameLoop} thread with a {@link #notify()}.
     * @throws IllegalStateException if the thread was already running when the method was called
     */
    public void run() {
        if (this.isRunning()) {
            throw new IllegalStateException("GameLoop is already running");
        }
        gameLoop.notifyAll();
        state = true;
    }

    /**
     * Stop the {@link GameLoop} thread with a {@link #wait()}.
     * @throws IllegalStateException if the thread was already stopped when the method was called
     * @throws InterruptedException if any thread interrupted the current thread before or
     *         while the current thread was waiting.
     */
    public void stop() throws InterruptedException {
        if (!this.isRunning()) {
            throw new IllegalStateException("GameLoop is alredy stopped");
        }
        do {
            gameLoop.wait();
            state = false;
        } while (state);
    }

    /**
     * Test if the {@link GameLoop} Thread is running.
     * @return {@code True} if is running; {@code False} otherwise
     */
    public boolean isRunning() {
        return state;
    }

    /**
     * This method create a and start a new {@link GameLoop}.
     */
    public void init() {
        state = true;
        gameLoop = new GameLoop();
        gameLoop.start();
    }
}
