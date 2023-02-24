package it.unibo.smol.core;

/**
 * This class is the engine of the game.
 * The purpose of the engine is to control the {@link GameLoop} thread
 */
public class GameEngineImpl implements GameEngine {

    private GameLoop gameLoop;

    /**
     * Rappresent the state of the {@link GameLoop}.
     * {@code True} if is running; {@code False} otherwise
     */
    private boolean state;

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        if (this.isRunning()) {
            throw new IllegalStateException("GameLoop is already running");
        }
        gameLoop.notifyAll();
        state = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
    public boolean isRunning() {
        return state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
        state = true;
        gameLoop = new GameLoop();
        gameLoop.start();
    }
}
