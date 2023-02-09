package Plants.species;

import Item.ItemType;
import Plants.PlantAbstract;

public class Weat extends PlantAbstract {

    Weat(){
        super(null);
        super.type = ItemType.Plants.WEAT;
        super.price = 5;
        //TODO products
    }

}
