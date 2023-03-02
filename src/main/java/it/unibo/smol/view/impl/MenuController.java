package it.unibo.smol.view.impl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MenuController {
    @FXML
    private Label myLabel;

    @FXML
    private Button myButton;

    /**
     * Event handler for `myButton`.
     * @param evt
     */
    @FXML
    public final void myButtonOnClickHandler(final MouseEvent evt) {
        myLabel.setText("Go to game scene ");
    }
}
