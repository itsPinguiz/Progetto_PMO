package model.item.plants;

import java.io.Serializable;

import model.item.Item;
import model.item.ItemType;
import model.item.plants.PlantAbstract.PlantLife;
import model.place.land.chunks.PlantChunk;

public interface PlantInteface  extends Serializable{
    public void grow();
    public void planted(PlantChunk c);

    public ItemType getPlantType();
    public PlantLife getLifeStage();
    public int getDaysToHarvest() ;
    public double getGrowthLevel();
    public Item getProduct();
}
