package it.unibo.smol.core;

/**
 * This class is the responsible of the overall flow control of the game.
 */
public class GameLoop extends Thread {

    private static final int FPS = 100;
    private static final double DRAW_INTERVAL = 1_000_000_000 / FPS;

    private long lastTime;
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
         lastTime = System.nanoTime();
        while (!end) {
            if (syncFrame()) {
                //drawCount++;
                processInput();
                update();
                repaint();
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
     *  Syncronize the {@link GameLoop} with the desired FPS rating ({@value #FPS}).
     * @return {@code True} if the time passed is equal or more of the desired {@link #DRAW_INTERVAL};
     * Otherwise {@code False}.
     */
    private boolean syncFrame() {
        final long currentTime = System.nanoTime();
        delta += (currentTime - lastTime) / DRAW_INTERVAL;
        //timer += (currentTime - lastTime);
        lastTime = currentTime;
        if (delta >= 1) {
            delta--;
            return true;
        } 
        return false;
    }
}
