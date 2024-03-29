package it.unibo.smol.core;

import java.util.Optional;

import it.unibo.smol.common.Constant;
import it.unibo.smol.controller.impl.GameStateImpl;
import it.unibo.smol.controller.input.KeyInputs;
import it.unibo.smol.controller.input.MouseInputs;
import it.unibo.smol.model.impl.WorldImpl;
import it.unibo.smol.view.impl.GameViewState;
import it.unibo.smol.view.impl.WindowImpl;
import javafx.stage.Stage;
/**
 * This class is the engine of the game.
 * The purpose of the engine is to control the {@link GameLoop} thread
 */
public class GameEngineImpl implements GameEngine {
    private String skin;

    /**
     * Constructor of Game Engine that sets default folder skin.
     */
    public GameEngineImpl() {
        this.skin = Constant.KEY_PIXEL_SKINS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final Stage primaryStage) {
        final KeyInputs keyEventHandler = new KeyInputs();
        final MouseInputs mouseEventHandler = new MouseInputs(Optional.of(keyEventHandler));
        final var world = new WorldImpl();
        world.setInputs(Optional.of(keyEventHandler), Optional.of(mouseEventHandler));
        final var gs = new GameStateImpl(world);
        gs.setSkins(skin);
        final var gv = new GameViewState(Optional.of(gs), Optional.of(keyEventHandler), Optional.of(mouseEventHandler));
        final GameLoop gameLoop = new GameLoop(Optional.of(gs), Optional.of(gv), Optional.of(primaryStage));
        new WindowImpl(gv).launch(primaryStage);
        gameLoop.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSkin(final String skinFolder) {
        this.skin = skinFolder;
    }
}
