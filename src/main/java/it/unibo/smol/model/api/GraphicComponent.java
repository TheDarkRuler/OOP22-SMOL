package it.unibo.smol.model.api;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
/**
 * Abstract class rappresenting the template of the graphics component for the {@link GameObject}.
 */
public abstract class GraphicComponent {

    private GraphicsContext graphic;
    private final double width;
    private final double height;

    /**
     * Basic constructor for the {@link GraphicComponent}.
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
     * @param x : The X coordinate on the destination for the upper left of the image
     * @param y : The Y coordinate on the destination for the upper left of the image
     */
    public abstract void render(GraphicsContext g, double x, double y); 

    /**
     * Setter for the {@link #graphic} field.
     * @param g : The value that's gonna be set
     */
    public void setGraphic(final GraphicsContext g) {
        this.graphic = g;
    }

    /**
     * Getter for the {@link #graphic} field.
     * @return {@link #graphic}
     */
    public GraphicsContext getGraphic() {
        return this.graphic;
    }

    /**
     * Getter for the {@link #width} field.
     * @return {@link #width}
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Getter for the {@link #height} field.
     * @return {@link #height}
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Getter for a subImage.
     * @param img
     * @param x
     * @param y
     * @param width
     * @param height
     * @return  the subImage 
     */
    public WritableImage getSubImage(final Image img, final int x, final int y, final int width, final int height) {
        final PixelReader reader = img.getPixelReader();
        return new WritableImage(reader, x, y, width, height);
    }
}
