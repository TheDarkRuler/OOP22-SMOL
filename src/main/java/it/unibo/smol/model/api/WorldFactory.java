package it.unibo.smol.model.api;

public interface WorldFactory {
    
    /**
     * @return a World in a easy game mod
     */
    World baseDifficultyWorld();

    /**
     * @return a World in a medium game mod
     */
    World mediumDifficultyWorld();

    /**
     * @return a World in a Difficult game mod
     */
    World hardDifficultyWorld();

    /**
     * @return a World in a Insane game mod
     */
    World insaneDifficultyWorld();
}
