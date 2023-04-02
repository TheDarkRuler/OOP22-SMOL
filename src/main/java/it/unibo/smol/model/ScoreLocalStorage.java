package it.unibo.smol.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.logging.Logger;

import it.unibo.smol.controller.api.GameState;

/**
 * A class for saving in local the score record.
 */
public class ScoreLocalStorage {
    private final GameState gameState;
    private int record;
    private final File directory = new File(PATH);
    private final File scoreFile;
    private static final String PATH = System.getProperty("user.home") + File.separator + "Smol";
    private static final String FILE_NAME = PATH + File.separator + "ScoreFile.txt";

    /**
     * Constructors for create the score file if doesn't exist.
     * @param gameState
     */
    public ScoreLocalStorage(final Optional<GameState> gameState) {
        this.gameState = gameState.orElseThrow();
        if (!directory.exists() && !this.directory.mkdir()) {
            try {
                throw new IOException();
            } catch (IOException e) {
                Logger.getLogger(ScoreLocalStorage.class.getName()).info("IOException mkdir");
            }
        }
        this.scoreFile = new File(FILE_NAME);
    }

    /**
     * A method that write the record in the score file.
     */
    public void writeFile() {
        try {
            if (gameState.getScore() > record) {
                final PrintWriter printWriter = new PrintWriter(scoreFile, StandardCharsets.UTF_8);
                printWriter.print(gameState.getScore());
                printWriter.close();
            }
        } catch (IOException e) {
            Logger.getLogger(ScoreLocalStorage.class.getName()).info("IOException write");
        }
    }

    /**
     * A method that read the score file and save the record.
     */
    public void readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(scoreFile, StandardCharsets.UTF_8));) {
            String character  = reader.readLine();
            while (character != null) {
                record = Integer.parseInt(character);
                character = reader.readLine();
            }
        } catch (IOException e) {
            Logger.getLogger(ScoreLocalStorage.class.getName()).info("IOException read");
        } catch (NumberFormatException e) {
            Logger.getLogger(ScoreLocalStorage.class.getName()).info("NumberFormatException in ScoreFile.txt");
        }
    }

    /**
    * Getter for file.
    * @return the score file
    */
    public File getScoreFile() {
        return this.scoreFile;
    }

    /**
     * Getter for record score.
     * @return the record
     */
    public int getRecord() {
        return this.record;
    }
}
