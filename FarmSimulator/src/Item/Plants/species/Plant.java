package Item.Plants.species;

import Item.ItemType.Plants;
import Item.Plants.PlantAbstract;

public class Plant extends PlantAbstract {
    
    public Plant(Plants type){
            super(null);
            this.number = 1;
            this.type = type;
            this.price = 5;
            this.lifeStage = PlantLife.SEED;
    }   
}
