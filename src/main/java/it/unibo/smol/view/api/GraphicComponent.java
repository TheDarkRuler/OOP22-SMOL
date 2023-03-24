package it.unibo.smol.view.api;

import it.unibo.smol.model.api.Entity;
import it.unibo.smol.view.impl.GraphicsDraw;
import javafx.scene.image.Image;
/**
 * Abstract class rappresenting the template of the graphics component for the {@link GameObject}.
 */
public abstract class GraphicComponent {

    private final double width;
    private final double height;
    private Entity entity;
    private Image image;

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
     * copy constructor of Graphic Component.
     * @param graphicComponent
     */
    public GraphicComponent(final GraphicComponent graphicComponent) {
        this.entity = graphicComponent.getEntity();
        //this.graphic = graphicComponent.getGraphic();
        this.height = graphicComponent.getHeight();
        this.image = graphicComponent.getImage();
        this.width = graphicComponent.getWidth();
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
     * {@inheritDoc}
     */
    public void render(GraphicsDraw graphic) {
        //if (this.entity.getInputComp().isPresent()) {
            //graphic.undrawSprite(this.entity, this);
            this.update();
            graphic.drawSprite(this.image, this.entity, this);
       // }
    }

    /**
     * Getter for the {@link #graphic} field.
     * @return {@link #graphic}
     *//*
    public GraphicsDraw getGraphic() {
        return this.graphic;
    }*/

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

    /**
     * Set the entity image.
     * @param img the entity image
     */
    public void setImage(final Image img) {
        this.image = img;
    }

    /**
    * Getter for thr entity image.
    * @return the image of the entity
    */
    public Image getImage() {
        return image;
    }
}
