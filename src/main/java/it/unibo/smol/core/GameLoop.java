package it.unibo.smol.core;

/**
 * This class is the responsible of the overall flow control of the game.
 */
public class GameLoop extends Thread {

    private static final int FPS = 100;
    private static final double FPS_INTERVAL = 1_000_000_000 / FPS;

    private static final int UPS = 100;
    private static final double UPS_INTERVAL = 1_000_000_000 / UPS;

    private long lastTimeFPS, lastTime;
    private double deltaFPS=0, deltaUPS=0;
    private boolean end;

    // ALL commented code is for FPS checking
    long timer =0;
    int drawCount=0;

    /**
     * Override of the {@code run()} method in the {@link Thread} class.
     * This implements the main body of the GameLoop
     */
    @Override
    public void run() {
         lastTimeFPS = System.nanoTime();
         lastTime = System.nanoTime();
        while (!end) {

            if (syncTime(UPS_INTERVAL, deltaUPS)) {
                drawCount++;
                processInput();
                update();
            }

            /*if (syncTime(FPS_INTERVAL, deltaFPS, lastTimeFPS)) {
                repaint();
            }*/
                
            if (timer >= 1000000000) {
                System.out.println("UPS: "+ drawCount);
                drawCount=0;
                timer=0;
            }
        }
    }

    /**
     * update.
     */
    public void update() {
        end = false;
    }

    /**
     * process.
     */
    private void processInput() {

    }

    /**
     * repaint.
     */
    private void repaint() {

    }

    /**
     *  Syncronize the {@link GameLoop} with the desired FPS rating ({@value #FPS}).
     * @return {@code True} if the time passed is equal or more of the desired {@link #DRAW_INTERVAL};
     * Otherwise {@code False}.
     */
    private boolean syncTime(final double interval, double delta) {
        final long currentTime = System.nanoTime();
        delta += (currentTime - lastTime) / interval;
        timer += (currentTime - lastTime);
        lastTime = currentTime;
        System.out.println(delta);
        System.out.println(deltaUPS);
        if (delta >= 1) {
            delta--;
            return true;
        }
        return false;
    }
}
