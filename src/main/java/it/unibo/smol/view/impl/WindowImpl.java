package it.unibo.smol.view.impl;

import java.io.IOException;

import it.unibo.smol.view.api.*;
import javafx.stage.Stage;

public class WindowImpl implements Window {
    private final WindowState currentState;
    private final Stage stage;

    public WindowImpl(final Stage primaryStage) {
        this.stage = primaryStage;
        this.currentState = new MenuState();
    }

    @Override
    public void launch() {
        try {
            currentState.render(stage);
        } catch (IOException e) {
            System.out.println("Something went wrong " + e.toString());
        }
    }
    
}
