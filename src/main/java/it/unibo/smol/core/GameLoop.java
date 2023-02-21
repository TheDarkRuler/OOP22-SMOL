package it.unibo.smol.core;

public class GameLoop extends Thread{

    private static final int FPS = 100;
    private static final double drawInterval = 1000000000/FPS;

    private long lastTime=0;
    private long currentTime=0;
    private double delta=0;

    /*long timer =0;
    int drawCount=0;*/

    @Override
    public void run() {
         lastTime = System.nanoTime();
        while(this != null) {
            
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

    public void update() {

    }

    private void processInput() {

    }

    private void repaint() {

    }

    private boolean syncFrame() {
        currentTime = System.nanoTime();
        
        delta += (currentTime - lastTime) / drawInterval;
        //timer += (currentTime - lastTime);
        lastTime = currentTime;
        if(delta >= 1) {
            delta--;
            return true;
        } 
        return false;
    }  
}
