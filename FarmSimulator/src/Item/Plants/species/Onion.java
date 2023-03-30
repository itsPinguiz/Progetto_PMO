package Item.Plants.species;

import Item.ItemType;
import Item.Plants.PlantAbstract;

public class Onion extends PlantAbstract {
    
    Onion(){
        super(null);
        super.type = ItemType.Plants.ONION;
        super.price = 5;
    }
}
