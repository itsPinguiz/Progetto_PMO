package Plants;

import java.util.ArrayList;

import Calendar.Calendar;
import Item.Interface.Item;
import Plants.enu.PlantLife;

public abstract class PlantAbstract extends Item implements PlantInteface{
    protected Double life;
    protected PlantLife lifePhase;
    protected Double waterNeed;
    protected ArrayList<Item> products;
    protected Calendar calendar = Calendar.getInstance();
    protected int plantationDay;

    protected PlantAbstract(){
        this.life = Double.valueOf(100);
        this.waterNeed = Double.valueOf(0);
        this.lifePhase = PlantLife.SEED;
    }

    public String getPlantType() {
        /*
         * Returns the species of the plant 
         */
        return null;
    }

    public Double getLife() {
        /*
         * Returns plant life
         */
        return this.life;
    }

    public Double getWaterNeed() {
        /*
         * Returns the plant's water need
         */
        return this.waterNeed;
    }

    public ArrayList<Item> getProduct() {
        /*
         * Returns all the products the plant has produced
         */
        ArrayList<Item> temp = new ArrayList<>(this.products);
        this.products = null;
        return temp;
    }

    protected int getPlantAge(){
        /*
         * Returns plant age
         */
        return this.calendar.getDay() - this.plantationDay; 
    }

    public void turnToProduct(){
        /*
         * Turns the plant to product when harvested
         */
        this.lifePhase = PlantLife.PRODUCT;
        this.products.add(this);
    }

    public void checkLifeStage(){
        /*
         * Changes life stage if needed
         */
        if (this.life > 20){
            this.lifePhase = PlantLife.SPROUT;
        } else if (this.life > 50){
            this.lifePhase = PlantLife.SMALL_PLANT;
        } else if (this.life > 80){
            this.lifePhase = PlantLife.ADULT_PLANT;
        } else if (this.life == 100){
            this.lifePhase = PlantLife.HARVESTABLE;
            // TODO ADD HARVEST TO ACTIONS
        }
    }
}
