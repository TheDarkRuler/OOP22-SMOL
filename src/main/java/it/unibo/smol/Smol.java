package it.unibo.smol;

import java.io.UnsupportedEncodingException;

import javafx.application.Application;

/**
 *  Main class of the Game.
 */
public final class Smol {

    private Smol() throws UnsupportedEncodingException {
        throw new UnsupportedEncodingException("This is a utility class");
    }

    /**
     *  Main body of the program.
     * @param args
     */
    public static void main(final String[] args) {
        Application.launch(SmolApplication.class, "");
    }
}
