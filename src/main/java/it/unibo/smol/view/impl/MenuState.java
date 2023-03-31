package it.unibo.smol.view.impl;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;

import it.unibo.smol.common.Constant;
import it.unibo.smol.core.GameEngine;
import it.unibo.smol.core.GameEngineImpl;
import it.unibo.smol.view.GameMap;
import it.unibo.smol.view.LoadImgs;
import it.unibo.smol.view.api.WindowState;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Implementation of the menu state, it renders the menu.
 */
public class MenuState implements WindowState {

    private static Logger logger = Logger.getLogger("menuLogger");
    private static final int MENU_ANIM_DURATION = 500;
    private final GameEngine gameEngine = new GameEngineImpl();
    private String currentSkins = Constant.KEY_PIXEL_SKINS;

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Stage primaryStage) throws IOException {
        try {
            this.start(primaryStage);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "MenuStateError::", e);
        }
    }

    /**
     * This method generate the menu from a file FXML.
     * 
     * @param primaryStage stage were menu will be generated.
     * @throws IOException
     * @throws Exception   Exception thrown if there is any problem, in particular
     *                     usefull to detect problems with the fxml file.
     */
    private void start(final Stage primaryStage) throws IOException {
        /*
         * Get fields initialization.
         */
        final URL url = new File("src/main/resources/layouts/Menu.fxml").toURI().toURL();
        final Parent root = FXMLLoader.load(url);
        final Scene scene = new Scene(root, GameMap.WIDTH * GameMap.SCREEN_PROP_X - 1,
                GameMap.HEIGHT * GameMap.SCREEN_PROP_Y - 1);
        final VBox menuBox = (VBox) scene.lookup("#box");
        // children
        //final Text title = (Text) scene.lookup("#title");
        final Button startGame = (Button) scene.lookup("#start");
        final Button gameOver = (Button) scene.lookup("#gameOver");
        final Button quitGame = (Button) scene.lookup("#quit");
        final MenuButton dropDownMenu = (MenuButton) scene.lookup("#dropDown");
        final ImageView title = (ImageView) scene.lookup("#boxImage");

        /*
         * Set fields.
         */
        /*title.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR,
                GameMap.BORDER_WIDTH * GameMap.SCREEN_PROP_X));*/
        menuBox.setSpacing(GameMap.BORDER_WIDTH / 3);

        // buttons behaviour
        title.setFitWidth(GameMap.SCREEN_PROP_X * GameMap.BORDER_WIDTH * 3);
        title.setFitHeight(GameMap.SCREEN_PROP_Y * GameMap.BORDER_HEIGHT * 3);
        startGame.setOnMouseClicked(e -> {
            gameEngine.setSkin(currentSkins);
            gameEngine.init(primaryStage);
        });
        gameOver.setOnMouseClicked(e -> {
            new WindowImpl(new GameOverWinState(0, Constant.KEY_PIXEL_SKINS)).launch(primaryStage);
        });
        quitGame.setOnMouseClicked(e -> {
            Platform.exit();
            Runtime.getRuntime().exit(0);
        });
        root.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.F11)) {
                if (primaryStage.isFullScreen()) {
                    primaryStage.setFullScreen(false);
                } else {
                    primaryStage.setFullScreen(true);
                }
            }
        });

        /*
         * Fields attachment.
         */
        buttonManagement(startGame);
        buttonManagement(gameOver);
        buttonManagement(quitGame);
        dropDownMenuManagement(dropDownMenu);
        scene.setCursor(new ImageCursor(LoadImgs.getSprites(LoadImgs.HAMMER, this.currentSkins)));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Start Menu :)");
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.getIcons().add(LoadImgs.getSprites(LoadImgs.LOGO, this.currentSkins));
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    private void buttonManagement(final Button btn) {
        setButtonBaseSize(btn);
        //Duration = 0.5 seconds
        final Duration duration = Duration.millis(MENU_ANIM_DURATION);
        final RotateTransition rotateTransition = new RotateTransition(duration, btn);
        rotateTransition.setByAngle(360);
        rotateTransition.play();
    }

    private void dropDownMenuManagement(final MenuButton menuButton) {
        menuButton.setText(Constant.KEY_PIXEL_SKINS);
        menuButton.setStyle("-fx-mark-color: green");
        setButtonBaseSize(menuButton);
        final MenuItem pixel = new MenuItem(Constant.KEY_PIXEL_SKINS);
        final MenuItem vectorial = new MenuItem(Constant.KEY_VECTORIAL_SKINS);
        menuButton.getItems().addAll(pixel, vectorial);
        menuButton.getItems().forEach(item -> {
            setDropDownImage(item);
            //TODO damoglie le immagini
            item.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent event) {
                    currentSkins = item.getText();
                    menuButton.setText(item.getText());
                }
            });
        });
    }

    private void setButtonBaseSize(final ButtonBase btnBase) {
        btnBase.setPrefWidth(GameMap.BORDER_WIDTH * GameMap.SCREEN_PROP_X * 2);
        btnBase.setPrefHeight(GameMap.BORDER_WIDTH / 3);
    }

    private void setDropDownImage(final MenuItem menuItem) {
        final ImageView skinImage = new ImageView(LoadImgs.getSprites(LoadImgs.ANGRY_MOLE, menuItem.getText()));
        skinImage.setFitWidth(GameMap.SCREEN_PROP_X * Constant.ENEMY_WIDTH / 2);
        skinImage.setFitHeight(GameMap.SCREEN_PROP_Y * Constant.ENEMY_HEIGHT / 2);
        menuItem.setGraphic(skinImage);
    }
}
