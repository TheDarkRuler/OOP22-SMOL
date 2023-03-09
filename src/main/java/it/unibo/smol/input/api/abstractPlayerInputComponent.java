package it.unibo.smol.input.api;

import it.unibo.smol.model.api.Entity;

public abstract class abstractPlayerInputComponent {
    
    private Entity player;

    public abstractPlayerInputComponent(Entity player){
        this.player = player;
    }

    public Entity getPlayer(){
        return this.player;
    }
}
