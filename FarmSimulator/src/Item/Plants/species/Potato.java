package Item.Plants.species;

import Item.ItemType;
import Item.Plants.PlantAbstract;

public class Potato extends PlantAbstract {
    public Potato(){
        super(null);
        super.type = ItemType.Plants.POTATO;
        super.price = 5;
    }
}
