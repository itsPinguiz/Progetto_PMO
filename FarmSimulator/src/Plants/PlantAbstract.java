package Plants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import Actors.Actions.ActionsManager.Action;
import Calendar.Calendar;
import Item.Interface.Item;
import Place.Land.PlantChunk;

public abstract class PlantAbstract extends Item implements PlantInteface{
    private int daysToHarvest;
    private PlantLife lifeStage;
    private Random random = new Random();
    private PlantChunk chunk;
    protected ArrayList<Item> products;
    protected Calendar calendar = Calendar.getInstance();

    public enum PlantLife {
        // plant life cycle state
        SEED,
        SPROUT,
        SMALL_PLANT,
        ADULT_PLANT,
        HARVESTABLE,
        PRODUCT
    }

    protected PlantAbstract(PlantChunk c){
        super.status = 0;
        this.daysToHarvest = random.nextInt(10) + 1;
        this.chunk = c;
    }

    public void planted(PlantChunk c){
        /*
         * Sets the chunk where the plant was planted
         */
        this.chunk = c;
    }

    public ArrayList<Item> getProduct() {
        /*
         * Returns all the products the plant has produced
         */
        ArrayList<Item> temp = new ArrayList<>(this.products);
        this.products = null;
        return temp;
    }

    public void turnToProduct(){
        /*
         * Turns the plant to product when harvested
         */
        this.lifeStage = PlantLife.PRODUCT;
        this.products.add(this);
    }

    private void checklifeStage(){
        /*
         * Changes life stage if needed
         */
        if (super.status > 20){
            this.lifeStage = PlantLife.SPROUT;
        } else if (super.status > 50){
            this.lifeStage = PlantLife.SMALL_PLANT;
        } else if (super.status > 80){
            this.lifeStage = PlantLife.ADULT_PLANT;
        } else if (super.status == 100){
            this.lifeStage = PlantLife.HARVESTABLE;
            
            this.chunk.getActions().updateActions(new HashSet<>(){{
                add(Action.HARVEST);
                }}, true);
        }
        this.daysToHarvest--;
    }

    public void grow() {
        /*
         * Grows the plant depending on all the conditions 
         */
        switch (this.calendar.getWeather()) {
            case CLOUDY:
                super.status += this.chunk.getWaterLevel() + this.chunk.getFertilizationLevel()* 2;
                break;
            case RAINY:
                super.status += (this.chunk.getWaterLevel() + this.chunk.getFertilizationLevel());
                break;
            case SNOWY:
                super.status += (this.chunk.getWaterLevel() + this.chunk.getFertilizationLevel()) / 2;
                break;       
            default:
                super.status += this.chunk.getWaterLevel() + this.chunk.getFertilizationLevel();
                break;
        }

        if (super.status >= this.daysToHarvest * 10) {
          super.status = this.daysToHarvest * 10;
        }

        this.checklifeStage();
    
        this.daysToHarvest--;
      }
    
      public int getDaysToHarvest() {
        /*
         * Returns the plant's remaining days until harvest
         */
        return this.daysToHarvest;
      }

      public PlantLife getLifeStage(){
        /*
         * Returns the plant's life stage
         */
        return this.lifeStage;
      }

      public String getPlantType() {
        /*
         * Returns the species of the plant 
         */
        return null;
    }

    public double getGrowthLevel() {
        /*
         * Returns plant life
         */
        return super.status;
    }
}
