package it.unibo.smol.input;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseInputs implements EventHandler<Event> {

    @Override
    public void handle(Event event) {
        if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {

        } else if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {

        } else if (event.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
            
        }
    }

}
