package it.unibo.smol.model.api;

import java.util.List;

public interface EntityFactory {
    List<Entity> Moles();

    Entity Player();

    List<Entity> LifePlants();

    Entity Weapon();
    
}
