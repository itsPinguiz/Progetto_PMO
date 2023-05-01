package model.item.plants;

import model.item.Item;
import model.item.ItemType;
import model.item.plants.Plant.PlantLife;
import model.place.land.chunks.PlantChunk;

public interface PlantInterface{
    public void planted(PlantChunk c);
    public Item getProduct();
    public void grow();
    public int getDaysToHarvest();
    public PlantLife getLifeStage();
    public ItemType getPlantType();
    public double getGrowthLevel();
}
