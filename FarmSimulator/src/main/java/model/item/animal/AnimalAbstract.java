/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.animal;

// Import
import java.util.ArrayList;

import model.Constants;
import model.exceptions.CustomExceptions.MaxWaterLevelReachedException;
import model.exceptions.CustomExceptions.MinimumHungerException;
import model.exceptions.CustomExceptions.NoFoodFoundException;
import model.item.Item;
import model.item.ItemType;
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
    protected boolean isAlive;

    //constructor
    public AnimalAbstract() {
        this.isAlive = true;
        this.hunger = 0;
        this.thirst = 0;
	}
    
    //method for changing hunger
    private void updateHunger(){
        this.hunger += Constants.HUNGER_INCREASE;
        if(this.hunger >= 100){
            this.hunger = 100;
            this.isAlive = false;
        }
    }

    //method for changing thirst
    private void updateThirst(){
        this.thirst += Constants.THIRST_INCREASE;
        if(this.thirst >= 100){
            this.thirst = 100;
            this.isAlive = false;
        }
    }

    //method isAlive
    public boolean isAlive(){
        return this.isAlive;
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
                if(this.isAlive && !(item.getType() == ItemType.productsType.MEAT) ){
                    tmp.add((Products)item.clone());
                }
                else if(!this.isAlive){
                    tmp.add((Products)item.clone());
                }  
            } catch (CloneNotSupportedException e) {
                // gestione dell'eccezione
            }
        }

        for (int i = 0; i < this.typeProduct.size(); i++){
            if(this.isAlive && !(this.typeProduct.get(i).getType() == ItemType.productsType.MEAT)){
                this.typeProduct.get(i).setNumber(0);
            }
            else if(!this.isAlive){
                this.typeProduct.get(i).setNumber(0);
            }
            
        }
        return tmp;
    }

    //method for getting the products
    public boolean areProductsAvailable(){
        for (int i = 0; i < this.typeProduct.size(); i++){
            if (this.typeProduct.get(i).getNumber() > 0){
                return true;
            }
        }
        return false;
    }

    //method for updating the products
    private void updateProducts(){
        if(this.isAlive){
        //if the hunger is low
        if(this.hunger >= 0 && this.thirst >= 0){
            for(int i = 0; i < this.typeProduct.size()- 1; i++){
                this.typeProduct.get(i).setNumber(this.typeProduct.get(i).getNumber() + Constants.HIGH_PRODUCTIVITY);
            }
        }

        //if the hunger is less than 30, the animal will produce more products
        if (this.hunger > 30 && this.thirst > 30){
            for (int i = 0; i < this.typeProduct.size()- 1; i++){
                this.typeProduct.get(i).setNumber(this.typeProduct.get(i).getNumber() + Constants.MEDIUM_PRODUCTIVITY);
            }
        }
        //if the hunger is less than 60, the animal will produce less products
        else if (this.hunger > 60 && this.thirst > 60){
            for (int i = 0; i < this.typeProduct.size() - 1; i++){
                this.typeProduct.get(i).setNumber(this.typeProduct.get(i).getNumber() + Constants.LOW_PRODUCTIVITY);
            }
        }
        //if the hunger is less than 90, the animal will lose some products
        else if (this.hunger > 90 && this.thirst > 90){
            for (int i = 0; i < this.typeProduct.size()- 1; i++){
                this.typeProduct.get(i).setNumber(this.typeProduct.get(i).getNumber() - Constants.NEGATIVE_PRODUCTIVITY);
            }
        }
    }else{
        this.typeProduct.get(this.typeProduct.size() - 1).setNumber(30);
    }

    }

    //method for updating the animal
    public void update() {
        super.status -= 1;
        this.updateHunger();
        this.updateThirst();
        this.updateProducts();
        if(!this.isAlive){
            this.status = 0;
        }
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
