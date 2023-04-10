package model.item.plants;

import java.util.HashSet;
import java.util.Random;

import model.Constants;
import model.actors.actions.ActionsManager.Action;
import model.calendar.Calendar;
import model.item.Item;
import model.item.ItemType;
import model.place.land.chunks.PlantChunk;

public abstract class PlantAbstract extends Item implements PlantInteface{
    // attributes
    private int daysToHarvest;
    protected PlantLife lifeStage;
    private int daysToHarvestInitial; 
    private int growthRate;
    private int maxGrowthLevel;
    private PlantChunk chunk;
    protected Calendar calendar;
    private Random random;

    public enum PlantLife {
        // plant life cycle state
        SEED("Seed"),
        SPROUT("Sprout"),
        SMALL_PLANT("Small Plant"),
        ADULT_PLANT("Adult Plant"),
        HARVESTABLE("Harvestable"),
        PRODUCT("Product");

        private String name;

        PlantLife(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    // constructor
    protected PlantAbstract(PlantChunk c){
        this.random = new Random();
        super.status = 0;
        super.price = 2;
        this.daysToHarvest = this.daysToHarvestInitial = random.nextInt(10) + 1; 
        this.growthRate = Constants.GROWTH_RATE; 
        this.chunk = c;
        this.calendar = Calendar.getInstance();
        this.maxGrowthLevel = Constants.MAX_GROWTH;
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
            this.chunk.getActions().updateActions(new HashSet<>(){{
                add(Action.HARVEST);
                }}, true);
            this.chunk.getLand().getActions().updateActions(new HashSet<>(){{
                add(Action.HARVEST_ALL);
                }}, false);
            
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
            case CLOUDY:
                growthFactor =  this.chunk.getWaterLevel() + this.chunk.getFertilizationLevel()* 2;
                break;
            case RAINY:
                growthFactor = (this.chunk.getWaterLevel()*2 + this.chunk.getFertilizationLevel());
                break;
            case SNOWY:
                growthFactor = (this.chunk.getWaterLevel() + this.chunk.getFertilizationLevel()) / 2;
                break;       
            default:
                 growthFactor =  this.chunk.getWaterLevel()/2 + this.chunk.getFertilizationLevel();
                break;
        }

         // percentuale di crescita in base ai giorni rimanenti alla raccolta
        double growthPercentage = (double)(this.daysToHarvest / this.daysToHarvestInitial)/50;
        
        // calcolo della crescita effettiva
        super.status += growthPercentage * growthFactor * this.growthRate;

        // controllo del massimo livello di crescita
        if (super.status > this.maxGrowthLevel) {
            super.status = this.maxGrowthLevel;
        }
        this.checklifeStage();
    
        // decremento giorni alla raccolta solo quando la pianta è pronta
        if (this.lifeStage == PlantLife.HARVESTABLE) {
            this.daysToHarvest--;
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
