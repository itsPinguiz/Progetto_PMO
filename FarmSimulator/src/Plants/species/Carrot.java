package Plants.species;

import Item.ItemType;
import Plants.PlantAbstract;

public class Carrot extends PlantAbstract {

    Carrot(){
        super(null);
        super.type = ItemType.Plants.CARROT;
        super.price = 5;
        //TODO products
    }

}
