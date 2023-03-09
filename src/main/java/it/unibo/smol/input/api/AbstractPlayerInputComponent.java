package it.unibo.smol.input.api;

import it.unibo.smol.model.api.Entity;

public abstract class AbstractPlayerInputComponent {

    private Entity player;

    public AbstractPlayerInputComponent(Entity player){
        this.player = player;
    }

    public Entity getPlayer(){
        return this.player;
    }
}
