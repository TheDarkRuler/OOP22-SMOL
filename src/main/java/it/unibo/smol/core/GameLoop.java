package it.unibo.smol.core;

/**
 * This class is the responsible of the overall flow control of the game.
 */
public class GameLoop extends Thread {

    private static final int FPS = 100;
    private static final double DRAW_INTERVAL = 1000000000 / FPS;

    private long lastTime = 0;
    private long currentTime = 0;
    private double delta = 0;

    /*long timer =0;
    int drawCount=0;*/

    @Override
    public void run() {
         lastTime = System.nanoTime();
        while (this != null) {
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
        currentTime = System.nanoTime();
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
