package it.unibo.smol.view.api;

import java.io.IOException;
import javafx.stage.Stage;

public interface WindowState {
    
    public void render(Stage stage) throws IOException;

}
