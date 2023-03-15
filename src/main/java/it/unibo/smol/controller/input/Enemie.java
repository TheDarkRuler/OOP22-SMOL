package it.unibo.smol.controller.input;

import java.util.Random;
import it.unibo.smol.view.api.GameMap;
import javafx.geometry.Point2D;

public class Enemie {

    private static final int DEFAULT_MIN_TIME_UP = 1500;
    private static final int DEFAULT_MAX_TIME_UP = 2500;

    private final GameMap mapDimension;
    private int minTimeUp;
    private int maxTimeUp;
    private int enemieSection;
    private int enemieTimesSpawn;
    private Point2D enemiePosition;
    private Point2D enemieNextPosition;

    public Enemie(final GameMap mapDimension) {
        this.mapDimension = mapDimension;

        this.minTimeUp = DEFAULT_MIN_TIME_UP;
        this.maxTimeUp = DEFAULT_MAX_TIME_UP;

        this.enemiePosition = initialEnemiePosition();
        this.enemieNextPosition = enemieSearchPosition(new Random().nextInt(4));
    }

    private Point2D initialEnemiePosition() {
        if (new Random().nextBoolean()) {
            return new Point2D(randomBetweenTwo(mapDimension.getBorderWidth() / 2,
                ((mapDimension.getBorderWidth() / 2) + mapDimension.getMapWidth())),
                (mapDimension.getBorderHeight() / 2) + new Random()
                    .nextDouble(mapDimension.getMapHeight() - (mapDimension.getBorderHeight() / 2)));
        } else {
            return new Point2D((mapDimension.getBorderWidth() / 2) + new Random()
            .nextDouble(mapDimension.getMapWidth() - (mapDimension.getBorderWidth() / 2)),
                randomBetweenTwo(mapDimension.getBorderHeight() / 2,
                ((mapDimension.getBorderHeight() / 2) + mapDimension.getMapHeight())));
        }
    }

    private double randomBetweenTwo(final double first, final double second) {
        return new Random().nextBoolean() ? first : second;
    }

    private Point2D enemieSearchPosition(final int choosenSegment) {
        this.enemieSection = choosenSegment;
        switch (choosenSegment) {
            case 0:
                return new Point2D(choosenSegment, choosenSegment);
            case 1:
                return new Point2D(choosenSegment, choosenSegment);
            case 2:
                return new Point2D(choosenSegment, choosenSegment);
            case 3:   
                return new Point2D(choosenSegment, choosenSegment); 
            default:
                return null;
        }
    }

    private double enemieRandX() {

    }

    private double enemieRandY() {
        
    }
    
}
