package it.unibo.smol.core;

/**
 * This class is the responsible of the overall flow control of the game.
 */
public class GameLoop extends Thread {

    private static final int FPS = 144;
    private static final double FPS_INTERVAL = 1_000_000_000 / FPS;

    private static final int UPS = 200;
    private static final double UPS_INTERVAL = 1_000_000_000 / UPS;

    private long pastTime;
    private double delta;
    private boolean end;

    // ALL commented code is for FPS checking
    /*long timer =0;
    int drawCount=0;*/

    /**
     * Override of the {@code run()} method in the {@link Thread} class.
     * This implements the main body of the GameLoop
     */
    @Override
    public void run() {
        long now;
        long lastFrame = System.nanoTime();
        pastTime = System.nanoTime();
        while (!end) {
            now = System.nanoTime();

            if (syncTime(UPS_INTERVAL)) {
                processInput();
                update();
            }

            if ((now - lastFrame) >= FPS_INTERVAL) {
                repaint();
                lastFrame = now;
                //drawCount++;
            }
            /*if (timer >= 1000000000) {
                System.out.println("FPS: "+ drawCount);
                drawCount=0;
                timer=0;
            }*/
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
     *  Syncronize the {@link GameLoop} with the desired refresh rating ({@value #UPS}).
     * @param interval : the time span of the refresh rate expressed in nanosecond
     * @return {@code True} if the time passed is equal or more of the desired {@link #DRAW_INTERVAL};
     * Otherwise {@code False}.
     */
    private boolean syncTime(final double interval) {
        final long currentTime = System.nanoTime();
        delta += (currentTime - pastTime) / interval;
        //timer += (currentTime - lastTime);
        pastTime = currentTime;
        if (delta >= 1) {
            delta--;
            return true;
        }
        return false;
    }
}
