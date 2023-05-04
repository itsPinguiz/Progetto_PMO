package model.item.plants;

import java.util.HashSet;
import java.util.Random;

import model.Constants;
import model.actors.actions.ActionsManager.Action;
import model.calendar.Calendar;
import model.item.Item;
import model.item.ItemType;
import model.place.land.chunks.PlantChunk;

/**
 * Class that represents a basic plant
 */
public abstract class Plant extends Item implements PlantInterface{
    /**
     * Attributes
     */
    private int daysToHarvest;
    protected PlantLife lifeStage;
    private int daysToHarvestInitial; 
    private int growthRate;
    private int maxGrowthLevel;
    private int daysWithoutWater;
    private PlantChunk chunk;
    protected Calendar calendar;
    private Random random;

    /**
     * Enum that contains the life stage of the plant
     */
    public enum PlantLife {
        DEAD("Dead"),
        SEED("Seed"),
        SPROUT("Sprout"),
        SMALL_PLANT("Small Plant"),
        ADULT_PLANT("Adult Plant"),
        HARVESTABLE("Harvestable"),
        PRODUCT("Product");

        /**
         * Attributes
         */
        private String name;

        /**
         * Constructor
         * @param name
         */
        PlantLife(String name) {
            this.name = name;
        }

        /**
         * Returns the name of the lifestage
         * @return
         */
        public String getName() {
            return name;
        }

        /**
         * Returns the name of the lifestage
         * @return
         */
        @Override
        public String toString() {
            return getName();
        }
    }

    // constructor
    protected Plant(PlantChunk c){
        this.random = new Random();
        super.status = 0;
        super.price = 2;
        this.daysToHarvest = this.daysToHarvestInitial = random.nextInt(10) + 1; 
        this.growthRate = Constants.GROWTH_RATE; 
        this.chunk = c;
        this.calendar = Calendar.getInstance();
        this.maxGrowthLevel = Constants.MAX_GROWTH;
        this.daysWithoutWater = 0;
    }

    public void planted(PlantChunk c){
        /*
         * Sets the chunk where the plant was planted
         */
        this.chunk = c;
    }

    public Item getProduct() {
        /*
         * Returns all the products the plant has produced
         */
        this.turnToProduct();
        return this;
    }

    private void turnToProduct(){
        /*
         * Turns the plant to product when harvested
         */
        this.lifeStage = PlantLife.PRODUCT;
    }

    private void checklifeStage(){
        /*
         * Changes life stage if needed
         */
        if (super.status == 100){
            this.lifeStage = PlantLife.HARVESTABLE;
            this.daysToHarvest--;

            // update actions
            this.chunk.getLand().getActions().resetActions();
            this.chunk.getActions().updateActions(new HashSet<>(){{
                add(Action.HARVEST);
                }}, true);
            
            this.chunk.getLand().getActions().updateActions(new HashSet<>(){{
                add(Action.HARVEST_ALL);
                }}, true);
            
            this.daysToHarvest = this.daysToHarvestInitial;
        }  else if (super.status > 80){
            this.lifeStage = PlantLife.ADULT_PLANT;
        } else if (super.status > 50){
            this.lifeStage = PlantLife.SMALL_PLANT;
        } else if (super.status > 20){
            this.lifeStage = PlantLife.SPROUT;
        }  
    }

    public void grow() {
        /*
         * Grows the plant depending on all the conditions 
         */
        double growthFactor = 0;
        switch (this.calendar.getWeather()) {
            case CLOUDY -> growthFactor =  this.chunk.getWaterLevel() + this.chunk.getFertilizationLevel()* 2;
            case RAINY-> growthFactor = (this.chunk.getWaterLevel()*2 + this.chunk.getFertilizationLevel());
            case SNOWY -> growthFactor = (this.chunk.getWaterLevel() + this.chunk.getFertilizationLevel()) / 2;     
            default -> growthFactor =  this.chunk.getWaterLevel()/2 + this.chunk.getFertilizationLevel();
        }

         // growth update
        super.status = Math.min(this.maxGrowthLevel, 
                                super.status + ((double)(this.daysToHarvest / this.daysToHarvestInitial)/50) * growthFactor * this.growthRate);


        this.checklifeStage();

        // the plant dies if it doesn't have water for a certain amount of days
        if (this.chunk.getWaterLevel() == 0) {
            this.daysWithoutWater++;
            if (this.daysWithoutWater >= Constants.PLANT_DAYS_WITHOUT_WATER) {
                this.lifeStage = PlantLife.DEAD;
                super.status = -1;
                return;
            }
        } else {
            this.daysWithoutWater = 0;
        }
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

      public ItemType getPlantType() {
        /*
         * Returns the species of the plant 
         */
        return this.type;
    }

    public double getGrowthLevel() {
        /*
         * Returns plant life
         */
        return super.status;
    }
}
