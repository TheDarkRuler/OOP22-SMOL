package it.unibo.smol.view.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import java.util.logging.Level;

import it.unibo.smol.common.Constant;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
    private Text score;
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
        final var canvas = new Canvas(GameMap.WIDTH*GameMap.SCREEN_PROP_X, GameMap.HEIGHT*GameMap.SCREEN_PROP_Y);
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
        initializeScore();
        root.getChildren().add(underHealthBar());
        root.getChildren().add(healthBar);
        root.getChildren().add(score);
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
            gContext.clearRect(0, 0, GameMap.WIDTH*GameMap.SCREEN_PROP_X, GameMap.HEIGHT*GameMap.SCREEN_PROP_Y);
            updateHealthBar();
            score.setText(Integer.toString(gameState.getScore()));
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

    private void initializeScore() {
        score = new Text((GameMap.MAP_WIDTH-GameMap.BORDER_WIDTH)*GameMap.SCREEN_PROP_X, GameMap.BORDER_HEIGHT * GameMap.SCREEN_PROP_Y / 4, Integer.toString(gameState.getScore()));
        //final File fontPath = new File("src/main/resources/font/ShortBaby-Mg2w.ttf");
        //Font customFont = Font.loadFont("src/main/resources/font/ShortBaby-Mg2w.ttf", 10.0);
        score.setFont(Font.font("Impact", FontWeight.BOLD, 18));
        score.setFill(Color.WHITE);
        score.setTextAlignment(TextAlignment.RIGHT);
        score.setScaleX(2);
        score.setScaleY(2);
        score.setVisible(true);


        
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

