package model.place.land.chunks;

import model.item.plants.PlantAbstract;
import model.place.land.PlantLand;

public interface Chunk {
    public void increaseWaterLevel(int value);
    public void increaseFertilizationLevel(int value);
    public void setPlant(PlantAbstract plant);
    public void setDirtStatus(boolean b);
    public boolean isPlowed();
    public float getWaterLevel();
    public float getFertilizationLevel();
    public PlantAbstract getPlant();
    public PlantLand getLand();
    
    public void update();
    public void resetActions();
}
