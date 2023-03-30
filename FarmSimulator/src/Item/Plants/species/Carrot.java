package Item.Plants.species;

import Item.ItemType;
import Item.Plants.PlantAbstract;

public class Carrot extends PlantAbstract {

    public Carrot(){
        super(null);
        super.type = ItemType.Plants.CARROT;
        super.price = 5;
        //TODO products
    }

}
