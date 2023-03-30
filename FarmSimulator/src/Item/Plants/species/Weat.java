package Item.Plants.species;

import Item.ItemType;
import Item.Plants.PlantAbstract;

public class Weat extends PlantAbstract {

    Weat(){
        super(null);
        super.type = ItemType.Plants.WEAT;
        super.price = 5;
    }

}
