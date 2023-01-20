package Plants;

import java.util.ArrayList;

import Calendar.Calendar;
import Item.Interface.Item;

public abstract class PlantAbstract extends Item implements PlantInteface{
    protected Double life;
    protected Double waterNeed;
    protected int age;
    protected ArrayList<Item> products;

    protected PlantAbstract(){
        this.life = Double.valueOf(100);
        this.waterNeed = Double.valueOf(0);
    }

    public String getPlantType() {
        /*
         * Returns the species of the plant 
         */
        return null;
    }

    public Double getLife() {
        return this.life;
    }

    public Double getWaterNeed() {
        return this.waterNeed;
    }

    public ArrayList<Item> getProduct() {
        ArrayList<Item> temp = new ArrayList<>(this.products);
        this.products = null;
        return temp;
    }

    public void plantAge(){
        //this.age = giorno corrente - this.age; 
    }
}
