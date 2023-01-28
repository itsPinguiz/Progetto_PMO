package Plants;

import java.util.ArrayList;

import Item.Interface.Item;

public interface PlantInteface {
    public String getPlantType();
    public Double getLife();
    public Double getWaterNeed();
    public ArrayList<Item> getProduct();
}
