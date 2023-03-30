package it.unibo.smol.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.logging.Logger;

import it.unibo.smol.controller.api.GameState;

/**
 * A class for saving in local the score record.
 */
public class ScoreLocalStorage {
    private final GameState gameState;
    private int record;
    private final File scoreFile;

    /**
     * Constructors for create the score file if doesn't exist.
     * @param gameState
     */
    public ScoreLocalStorage(final Optional<GameState> gameState) {
        this.gameState = gameState.orElseThrow();
        this.scoreFile = new File("ScoreFile.txt");
    }
 
    /**
     * A method that write the record in the score file.
     */
    public void writeFile() {
        try {
            if (gameState.getScore() > record) {
                final PrintWriter printWriter = new PrintWriter(scoreFile);
                printWriter.print(gameState.getScore());
                printWriter.close();
                //System.out.println("record:" + record);
            }
        } catch (IOException e) {
            Logger.getLogger(ScoreLocalStorage.class.getName()).info("IOException");
        }
    }

    /**
     * A method that read the score file and save the record.
     */
    public void readFile() {
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(scoreFile));
            String character  = reader.readLine();
            while (character != null) {
                record = Integer.parseInt(character);
                //System.out.println("leggo:" + record);
                character = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            Logger.getLogger(ScoreLocalStorage.class.getName()).info("IOException");
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
