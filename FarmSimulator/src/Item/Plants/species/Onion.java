package Plants.species;

import Item.ItemType;
import Plants.PlantAbstract;

public class Onion extends PlantAbstract {
    
    Onion(){
        super(null);
        super.type = ItemType.Plants.ONION;
        super.price = 5;
        //TODO products
    }
}
