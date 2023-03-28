package it.unibo.smol.view.impl;

import java.io.IOException;
import java.util.logging.Logger;

import java.util.logging.Level;

import it.unibo.smol.controller.api.GameState;
import it.unibo.smol.controller.input.KeyInputs;
import it.unibo.smol.controller.input.MouseInputs;
import it.unibo.smol.view.GameMap;
import it.unibo.smol.view.api.HealthBarTank;
import it.unibo.smol.view.api.WindowState;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Implementation of the main state, it renders the game.
 */
public class GameViewState implements WindowState {
    private static Logger logger = Logger.getLogger("myLog");

    private GraphicsDraw graphic;
    private GraphicsContext gContext;
    private boolean started;
    private final GameState gameState;
    private KeyInputs keyEventHandler;
    private MouseInputs mouseEventHandler;
    private Canvas canvas;
    private Rectangle healthBar;


    
    public GameViewState(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Stage stage) {
        if (!started) {
            try {
                started = true;
                this.start(stage);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "GameViewError::", e);
            }
        } else {
            try {
                this.repaint(stage);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "GameViewError::", e);
            }
        }
    }

    private void start(final Stage stage) throws IOException {
        keyEventHandler = new KeyInputs();
        mouseEventHandler = new MouseInputs();
        setKeyInputs();
        setMouseInputs();
        final var root = new Pane();
        final var scene = new Scene(root, GameMap.WIDTH,
            GameMap.HEIGHT, Color.BLACK);
        final var canvas = new Canvas(GameMap.WIDTH, GameMap.HEIGHT);
        this.gContext = canvas.getGraphicsContext2D();
        this.graphic = new GraphicsDraw(gContext);
        root.setBackground(null);
        scene.setFill(Color.GREEN);
        scene.setOnKeyPressed(keyEventHandler);
        scene.setOnKeyReleased(keyEventHandler);
        scene.setOnMouseMoved(mouseEventHandler);
        scene.setOnMousePressed(mouseEventHandler);
        scene.setOnMouseReleased(mouseEventHandler);
        scene.setOnMouseDragged(mouseEventHandler);
        scene.setOnMouseEntered(mouseEventHandler);
        root.getChildren().add(canvas);
        initializeHealthBar();
        root.getChildren().add(underHealthBar());
        root.getChildren().add(healthBar);
        stage.setX(0);
        stage.setY(0);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
    }

    /**
     * Repaint the graphic aspect of the view.
     * @param stage The stage where the game is running
     * @throws IOException Exception if the stage can't be rendered.
     */
    public void repaint(final Stage stage) throws IOException {
        Platform.runLater(() -> {
            gContext.clearRect(0, 0, GameMap.WIDTH, GameMap.HEIGHT);
            updateHealthBar();
            gameState.getWorld().getEntities().stream().map(x -> x.getGraphicComp()).forEach(x -> x.render(graphic));
        });
    }

    /**
     * sets the keyInput in gamestate.
     */
    public void setKeyInputs() {
        this.gameState.setKeyInputs(this.keyEventHandler);
    }

    /**
     * sets the mouseInputs in gamestate.
     */
    public void setMouseInputs() {
        this.gameState.setMouseInputs(this.mouseEventHandler);
    }

    private void initializeHealthBar() {
        HealthBarTank healthBarData = new HealthBarTankImpl(this.gameState);
        this.healthBar = new Rectangle(   healthBarData.getCenter().getX(), 
                                        healthBarData.getCenter().getY(), 
                                        healthBarData.getHealthBarWidth(), 
                                        healthBarData.getHealthBarHeight()
                                    );
        healthBar.setFill(healthBarData.healthBarColor());
    }

    private Rectangle underHealthBar() {
        HealthBarTank healthBarData = new HealthBarTankImpl(this.gameState);
        var underHealth = new Rectangle(   healthBarData.getCenter().getX(), 
                                        healthBarData.getCenter().getY(), 
                                        healthBarData.getHealthBarWidth(), 
                                        healthBarData.getHealthBarHeight()
                                    );
        underHealth.setFill(Color.RED);
        underHealth.setStrokeWidth(healthBarData.getHealthBarBorder());
        underHealth.setStroke(Color.BLACK);
        return underHealth;
    }

    private void updateHealthBar() {
        HealthBarTank healthBarData = new HealthBarTankImpl(this.gameState);
        this.healthBar.setWidth(healthBarData.getHealthBarWidth() * healthBarData.updateHealthPercentage());
    }
}

