package it.unibo.smol.input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyInputs implements EventHandler<KeyEvent> {

    @Override
    public void handle(KeyEvent event) {

        if(event.getEventType().equals(KeyEvent.KEY_PRESSED)){
            switch (event.getCode()) {
                case W:
                    break;
                case A:
                    break;
                case S:
                    break;
                case D:
                    break;
                default:
                    break;
            }

        } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
            switch (event.getCode()) {
                case W:
                case S:
                    break;
                case A:
                case D:
                    break; 
                default:
                    break;
            }
        }
    }    
}
