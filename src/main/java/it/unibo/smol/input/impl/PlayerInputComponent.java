package it.unibo.smol.input.impl;

import it.unibo.smol.common.Directions;
import it.unibo.smol.input.api.InputComponent;

public class PlayerInputComponent implements InputComponent {
    private KeyInputs input;

    public PlayerInputComponent(){
        input = new KeyInputs();
    }


    @Override
    public PlayerInputComponent update() {
        //TODO verify if is usefull
       return new PlayerInputComponent();
    }


    @Override
    public Directions getDirection() {
        return input.getMovement();
    }
    
}
