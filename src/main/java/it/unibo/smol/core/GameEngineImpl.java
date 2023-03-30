package it.unibo.smol.core;

import java.util.Optional;

import it.unibo.smol.controller.impl.GameStateImpl;
import it.unibo.smol.model.impl.WorldImpl;
import it.unibo.smol.view.impl.GameViewState;
import it.unibo.smol.view.impl.WindowImpl;
import javafx.stage.Stage;
/**
 * This class is the engine of the game.
 * The purpose of the engine is to control the {@link GameLoop} thread
 */
public class GameEngineImpl implements GameEngine {

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final Stage primaryStage) {
        final var gs = new GameStateImpl(new WorldImpl());
        final var gv = new GameViewState(Optional.of(gs));
        final GameLoop gameLoop = new GameLoop(Optional.of(gs), Optional.of(gv), Optional.of(primaryStage));
        new WindowImpl(gv).launch(primaryStage);
        gameLoop.start();
    }
}
