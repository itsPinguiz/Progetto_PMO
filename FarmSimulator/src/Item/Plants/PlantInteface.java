package Item.Plants;

import java.io.Serializable;
import java.util.ArrayList;

import Item.ItemType;
import Item.Interface.Item;
import Place.Land.PlantChunk;
import Item.Plants.PlantAbstract.PlantLife;

public interface PlantInteface  extends Serializable{
    public void grow();
    public void planted(PlantChunk c);

    public ItemType getPlantType();
    public PlantLife getLifeStage();
    public int getDaysToHarvest() ;
    public double getGrowthLevel();
    public ArrayList<Item> getProduct();
    
    public void turnToProduct();
}
