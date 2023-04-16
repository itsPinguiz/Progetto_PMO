/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.animal;

// Import
import java.util.ArrayList;

import model.Constants;
import model.calendar.Calendar;
import model.exceptions.CustomExceptions.MaxWaterLevelReachedException;
import model.exceptions.CustomExceptions.MinimumHungerException;
import model.exceptions.CustomExceptions.NoFoodFoundException;
import model.item.Item;
import model.item.plants.species.Plant;
import model.item.products.Products;

/*****************
 * ANIMAL ABSTRACT
 ****************/
public abstract class AnimalAbstract extends Item implements AnimalInterface {

    //fields
    protected int hunger;
    protected int thirst;
    protected ArrayList<Products> typeProduct;
    protected Calendar c;
    protected int creationDay;
    protected int lastUpdatedDay;

    //constructor
    public AnimalAbstract() {
        this.hunger = 0;
        this.thirst = 0;
        this.c = Calendar.getInstance();
        this.creationDay = c.getDay();
        this.lastUpdatedDay = this.creationDay;
	}
    
    //method for changing hunger
    private void updateHunger(){
        int dayPassed = c.getDay() - this.creationDay;
        this.hunger = dayPassed * 2;
    }

    //method for changing thirst
    private void updateThirst(){
        int dayPassed = c.getDay() - this.creationDay;
        this.thirst = dayPassed * 2;
    }

    //method for getting thirst
    public int getThirst() {
        return this.thirst;
    }

    //method for getting hunger
    public int getHunger() {
        return this.hunger;
    }

    //method for getting the products
    public ArrayList<Products> getProducts() {
        ArrayList<Products> tmp = new ArrayList<>();
        for (Item item : typeProduct) {
            try {
                tmp.add((Products)item.clone());
            } catch (CloneNotSupportedException e) {
                // gestione dell'eccezione
            }
        }
        for (int i = 0; i < this.typeProduct.size(); i++){
            this.typeProduct.get(i).setNumber(0);
        }
        return tmp;
    }

    //method for getting the products
    public boolean areProductsAvailable(){
        for (int i = 0; i < this.typeProduct.size(); i++){
            if (this.typeProduct.get(i).getNumber() > 0){
                System.out.println(this.typeProduct.get(i).toString() + this.typeProduct.get(i).getNumber());
                return true;
            }
        }
        return false;
    }

    //method for updating the life
    private void updateLife() {
        int daysPassed = c.getDay() - lastUpdatedDay; // calcola quanti giorni sono passati dall'ultimo aggiornamento
        if (daysPassed > 0) { // se sono passati almeno 1 giorno
            super.status -= 1 * daysPassed; // diminuisci la vita di uno per ogni giorno trascorso
            this.lastUpdatedDay = c.getDay(); // aggiorna il campo "lastUpdatedDay"
        }
    }

    //method for updating the products
    private void updateProducts(){

        //if the hunger is less than 30, the animal will produce more products
        if (this.hunger > 30 || this.thirst > 30){
            for (int i = 0; i < this.typeProduct.size(); i++){
                this.typeProduct.get(i).setNumber(this.typeProduct.get(i).getNumber() + 10);
            }
        }
        //if the hunger is less than 60, the animal will produce less products
        else if (this.hunger > 60 || this.thirst > 60){
            for (int i = 0; i < this.typeProduct.size(); i++){
                this.typeProduct.get(i).setNumber(this.typeProduct.get(i).getNumber() + 5);
            }
        }
        //if the hunger is less than 90, the animal will lose some products
        else if (this.hunger > 90 || this.thirst > 90){
            for (int i = 0; i < this.typeProduct.size(); i++){
                this.typeProduct.get(i).setNumber(this.typeProduct.get(i).getNumber() - 5);
            }
        }

    }

    //method for updating the animal
    public void update() {
        this.updateLife();
        this.updateHunger();
        this.updateThirst();
        this.updateProducts();
    }

    //method for feeding the animal
    public void feed(Item item) throws NoFoodFoundException, MinimumHungerException{
        try {
            if(item instanceof Plant){
                if(this.hunger >= Constants.FEED_MAX){
                    this.hunger -= Constants.FEED_INDEX;
                }
                else{
                    throw new MinimumHungerException();
                }
            }
            else{
                throw new NoFoodFoundException();
            }
        } catch (NoFoodFoundException | MinimumHungerException e) {
            e.getStackTrace();
        }
    }

    //method for watering the animal
    public void waterAnimal() throws MaxWaterLevelReachedException{

        try {
            if (this.thirst > Constants.GIVE_WATER_MAX){
                this.thirst -= Constants.GIVE_WATER_INDEX;
            }
            else {
                throw new MaxWaterLevelReachedException();
            }
        } catch (MaxWaterLevelReachedException e) {
            e.getStackTrace();
        }
    }
}
