package Plants.species;

import Item.ItemType;
import Plants.PlantAbstract;

public class Potato extends PlantAbstract {
    Potato(){
        super(null);
        super.type = ItemType.Plants.POTATO;
        super.price = 5;
        //TODO products
    }
}
