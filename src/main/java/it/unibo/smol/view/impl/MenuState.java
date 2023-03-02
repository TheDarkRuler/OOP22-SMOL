package it.unibo.smol.view.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import it.unibo.smol.view.api.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MenuState implements WindowState {

    @Override
    public void render(Stage primaryStage) throws IOException {
        try {
            this.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void start(final Stage primaryStage) throws Exception {
        URL url = new File("src/main/resources/layouts/Menu.fxml").toURI().toURL();
        final Parent root = FXMLLoader.load(url);
        final Scene scene = new Scene(root, 1280, 720);
        final Label lbl = (Label) scene.lookup("#myLabel");
        lbl.setText(".........................");
        primaryStage.setTitle("Start Menu :)");
        primaryStage.setScene(scene);
        primaryStage.show();
    
    }

    

}



    
    
    

