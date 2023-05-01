package model.item.plants;

import java.io.Serializable;

import model.item.ItemType;

public class PlantFactory implements Serializable {
    public GamePlant createCarrot(){
        return new GamePlant(ItemType.Plants.CARROT);
    }
    public GamePlant createOnion(){
        return new GamePlant(ItemType.Plants.ONION);
    }
    public GamePlant createPotato(){
        return new GamePlant(ItemType.Plants.POTATO);
    }
    public GamePlant createWeat(){
        return new GamePlant(ItemType.Plants.WHEAT);
    }
}
