package it.unibo.smol.view.impl;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.Optional;
import java.util.logging.Level;

import it.unibo.smol.common.Constant;
import it.unibo.smol.controller.api.GameState;
import it.unibo.smol.controller.input.KeyInputs;
import it.unibo.smol.controller.input.MouseInputs;
import it.unibo.smol.view.GameMap;
import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.HealthBarTank;
import it.unibo.smol.view.api.WindowState;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Implementation of the main state, it renders the game.
 */
public class GameViewState implements WindowState {

    private static final String INIT_MESSAGE = "Be careful, Moles are coming for your greens" 
        + "\n (F11 to enable and disable full screen)";
    private static Logger logger = Logger.getLogger("myLog");
    private static final int SCORE_SIZE = 18;

    private final GameState gameState;
    private final KeyInputs keyEventHandler;
    private final MouseInputs mouseEventHandler;
    private GraphicsDraw graphic;
    private GraphicsContext gContext;
    private boolean started;
    private Text score;
    private Text record;
    private Rectangle healthBar;
    private HealthBarTank healthBarData;

    /**
     * constructor made to get the gamseState.
     * @param gameState
     * @param keyInputs
     * @param mouseInputs
     */
    public GameViewState(final Optional<GameState> gameState,
        final Optional<KeyInputs> keyInputs, final Optional<MouseInputs> mouseInputs) {
        this.mouseEventHandler = mouseInputs.orElseThrow();
        this.keyEventHandler = keyInputs.orElseThrow();
        this.gameState = gameState.orElseThrow();
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
        final var root = new Pane();
        final var scene = new Scene(root, GameMap.WIDTH * GameMap.SCREEN_PROP_X - 1,
                GameMap.HEIGHT * GameMap.SCREEN_PROP_Y - 1, Color.BLACK);
        final var canvas = new Canvas(GameMap.WIDTH * GameMap.SCREEN_PROP_X - 1, GameMap.HEIGHT * GameMap.SCREEN_PROP_Y - 1);
        this.gContext = canvas.getGraphicsContext2D();
        this.gContext.setImageSmoothing(false);
        this.graphic = new GraphicsDraw(Optional.of(gContext), gameState.getSkins());
        root.setBackground(new Background(new BackgroundImage(LoadImgs.getSprites(LoadImgs.BACKGROUND, gameState.getSkins()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(GameMap.WIDTH * GameMap.SCREEN_PROP_X - 1, GameMap.HEIGHT * GameMap.SCREEN_PROP_Y - 1,
                        false, false, false, false))));
        scene.setCursor(new ImageCursor(LoadImgs.getSprites(LoadImgs.HAMMER, Constant.KEY_COMMON_FOLDER)));
        scene.setOnKeyPressed(keyEventHandler);
        scene.setOnKeyReleased(keyEventHandler);
        scene.setOnMouseMoved(mouseEventHandler);
        scene.setOnMousePressed(mouseEventHandler);
        scene.setOnMouseReleased(mouseEventHandler);
        scene.setOnMouseDragged(mouseEventHandler);
        scene.setOnMouseEntered(mouseEventHandler);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.F11)) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });
        root.getChildren().add(canvas);
        initializeHealthBar();
        initializeScore();
        initializeRecord();
        root.getChildren().add(underHealthBar());
        root.getChildren().add(healthBar);
        root.getChildren().add(score);
        root.getChildren().add(record);
        stage.setTitle("SMOL");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreenExitHint(INIT_MESSAGE);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(final WindowEvent e) {
                Platform.exit();
                Runtime.getRuntime().exit(0);
            }
        });
        stage.getIcons().add(LoadImgs.getSprites(LoadImgs.LOGO, Constant.KEY_COMMON_FOLDER));
        stage.show();
    }

    /**
     * Repaint the graphic aspect of the view.
     * 
     * @param stage The stage where the game is running
     * @throws IOException Exception if the stage can't be rendered.
     */
    public void repaint(final Stage stage) throws IOException {
        Platform.runLater(() -> {
            gContext.clearRect(0, 0, GameMap.WIDTH * GameMap.SCREEN_PROP_X - 1,
                GameMap.HEIGHT * GameMap.SCREEN_PROP_Y - 1);
            updateHealthBar();
            score.setText("Score: " + Integer.toString(gameState.getScore()));
            record.setText("Record: " + Integer.toString(gameState.getRecord()));
            gameState.getWorld().orElseThrow().getEntities().stream()
                    .filter(x -> x.getGraphicComp().isPresent())
                    .map(x -> x.getGraphicComp())
                    .forEach(x -> x.orElseThrow().render(graphic));
        });
    }

    private void initializeHealthBar() {
        this.healthBarData = new HealthBarTankImpl(this.gameState);
        this.healthBar = new Rectangle(healthBarData.getCenter().getX(), 
                                        healthBarData.getCenter().getY(), 
                                        healthBarData.getHealthBarWidth(), 
                                        healthBarData.getHealthBarHeight()
                                    );
        healthBar.setFill(healthBarData.healthBarColor());
    }

    private void initializeScore() {
        score = new Text((GameMap.MAP_WIDTH - GameMap.BORDER_WIDTH * 2) * GameMap.SCREEN_PROP_X,
                GameMap.BORDER_HEIGHT * GameMap.SCREEN_PROP_Y / 3, Integer.toString(gameState.getScore()));
        score.setFont(Font.font("Impact", FontWeight.EXTRA_BOLD, SCORE_SIZE));
        score.setFill(Color.WHITE);
        score.setTextAlignment(TextAlignment.RIGHT);
        score.setScaleX(3);
        score.setScaleY(3);
        score.setVisible(true);
    }

    private Rectangle underHealthBar() {
        this.healthBarData = new HealthBarTankImpl(this.gameState);
        final var underHealth = new Rectangle(healthBarData.getCenter().getX(), 
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
        this.healthBarData = new HealthBarTankImpl(this.gameState);
        this.healthBar.setWidth(healthBarData.getHealthBarWidth() * healthBarData.updateHealthPercentage());
    }

    private void initializeRecord() {
        record = new Text(GameMap.BORDER_WIDTH * 2 * GameMap.SCREEN_PROP_X,
            (GameMap.HEIGHT - GameMap.BORDER_HEIGHT / 3) * GameMap.SCREEN_PROP_Y,
            "Record:" + Integer.toString(gameState.getRecord()));
        record.setFont(Font.font("Impact", FontWeight.EXTRA_BOLD, SCORE_SIZE));
        record.setFill(Color.WHITE);
        record.setTextAlignment(TextAlignment.LEFT);
        record.setScaleX(3);
        record.setScaleY(3);
        record.setVisible(true);
    }

}
