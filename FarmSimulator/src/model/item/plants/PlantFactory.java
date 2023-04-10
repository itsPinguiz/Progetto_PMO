package model.item.plants;

import java.io.Serializable;

import model.item.ItemType;
import model.item.plants.species.Plant;

public class PlantFactory implements Serializable {
    public Plant createCarrot(){
        return new Plant(ItemType.Plants.CARROT);
    }
    public Plant createOnion(){
        return new Plant(ItemType.Plants.ONION);
    }
    public Plant createPotato(){
        return new Plant(ItemType.Plants.POTATO);
    }
    public Plant createWeat(){
        return new Plant(ItemType.Plants.WHEAT);
    }
}
