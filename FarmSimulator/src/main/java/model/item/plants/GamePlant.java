package model.item.plants;

import model.item.ItemType.Plants;

public class GamePlant extends Plant {
    
    public GamePlant(Plants type){
            super(null);
            this.number = 1;
            this.type = type;
            this.lifeStage = PlantLife.SEED;

            switch (type) {
                case WHEAT -> this.price = 10;
                case ONION -> this.price = 15;
                case POTATO -> this.price = 20;
                case CARROT -> this.price = 25;
            }
    }   
}
