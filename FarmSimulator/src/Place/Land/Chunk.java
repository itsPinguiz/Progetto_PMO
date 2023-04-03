package Place.Land;

import Item.Plants.PlantAbstract;

public interface Chunk {
    public void setWaterLevel(int value);
    public void setFertilizationLevel(int value);
    public void setPlant(PlantAbstract plant);
    public void setDirtStatus(boolean b);
    public boolean getDirtStatus();
    public float getWaterLevel();
    public float getFertilizationLevel();
    public PlantAbstract getPlant();
    public PlantLand getLand();
    
    public void update();
    public void resetActions();
}
