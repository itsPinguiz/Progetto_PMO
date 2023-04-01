package Item.Plants;

import Item.ItemType;
import Item.Plants.species.Plant;

public class PlantFactory {
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
        return new Plant(ItemType.Plants.WEAT);
    }
}
