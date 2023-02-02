package Plants;

import java.util.ArrayList;

import Item.Interface.Item;

public interface PlantInteface {
    public String getPlantType();
    public double getGrowthLevel();
    public ArrayList<Item> getProduct();
}
