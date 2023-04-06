package model.item.plants.species;

import model.item.ItemType.Plants;
import model.item.plants.PlantAbstract;

public class Plant extends PlantAbstract {
    
    public Plant(Plants type){
            super(null);
            this.number = 1;
            this.type = type;
            this.price = 5;
            this.lifeStage = PlantLife.SEED;
    }   
}
