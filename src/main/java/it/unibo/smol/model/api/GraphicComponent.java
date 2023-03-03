package it.unibo.smol.model.api;

import java.awt.Graphics;

/**
 * Abstract class rappresenting the template of the graphics component for the {@link GameObject}.
 */
public abstract class GraphicComponent {

    private Graphics graphic;
    private final double width;
    private final double height;

    /**
     * Basic constructor for the {@link GraphicComponent}
     * @param width
     * @param height
     */
    public GraphicComponent(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Update the current state of the {@link GraphicComponent}.
     */
    public abstract void update();

    /**
     * Generate the 2D model from the {@link resources} file.
     * @param g : The object that encapsulate the information for the rendering operations
     */
    public void render(final Graphics g) {
        g.drawImage(null, (int) width, (int) height, null);
        //TODO img: have to be changend
    }

    /**
     * Setter for the {@link #graphic} field.
     * @param graphics : The value that's gonna be set
     */
    public void setGraphic(final Graphics g) {
        this.graphic = g;
    }

    /**
     * Getter for the {@link #graphic} field.
     * @return {@link #graphic}
     */
    public Graphics getGraphic() {
        return graphic;
    }

    /**
     * Getter for the {@link #width} field.
     * @return {@link #width}
     */
    public double getWidth() {
        return width;
    }

    /**
     * Getter for the {@link #height} field.
     * @return {@link #height}
     */
    public double getHeight() {
        return height;
    } 
}
