package it.unibo.smol.model.api;

import javafx.scene.canvas.GraphicsContext;
/**
 * Abstract class rappresenting the template of the graphics component for the {@link GameObject}.
 */
public abstract class GraphicComponent {

    private GraphicsContext graphic;
    private final double width;
    private final double height;
    private Entity entity;

	/**
     * Basic constructor for the {@link GraphicComponent}.
     * @param width : the width of the image
     * @param height : the height of the image
     */
    public GraphicComponent(final double width, final double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Set the correct animation.
     */
    public abstract void setAnimation();

    /**
     * Update the animation of the entity.
     */
    public abstract void updateAnimation();

    /**
     * Update the current state of the {@link GraphicComponent}.
     */
    public void update() {
        updateAnimation();
        setAnimation();
    }

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
    * Getter for thr entity field.
    * @return the entity that use this component
    */  
    public Entity getEntity() {
		return entity;
	}

    /**
     * Set the entity associated with this component.
     * @param entity the entity that use this component
     */
	public void setEntity(final Entity entity) {
		this.entity = entity;
	}
}
