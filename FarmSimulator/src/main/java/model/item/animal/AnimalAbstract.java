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
import model.item.animal.products.Products;
import model.item.plants.GamePlant;

/*****************
 * ANIMAL ABSTRACT
 ****************/
/**
 * This abstract class represents an Animal in the game. It extends from the Item class and implements the AnimalInterface.
 * It includes attributes such as hunger, thirst, and the type of products an animal can produce. 
 */
public abstract class AnimalAbstract extends Item implements AnimalInterface {

    //fields
    protected int hunger;
    protected int thirst;
    protected ArrayList<Products> typeProduct;
    protected boolean isAlive;

    /**
     * Constructs an AnimalAbstract instance, initializing the animal as alive and setting hunger and thirst to zero.
     */
    public AnimalAbstract() {
        this.isAlive = true;
        this.hunger = 0;
        this.thirst = 0;
	}
    
    /**
     * Updates the hunger value of the animal.
     */
    private void updateHunger(){
        this.hunger += Constants.HUNGER_INCREASE;
        if(this.hunger >= 100){
            this.hunger = 100;
            this.isAlive = false;
        }
    }

    /**
      * Updates the thirst value of the animal.
      */
    private void updateThirst(){
        this.thirst += Constants.THIRST_INCREASE;
        if(this.thirst >= 100){
            this.thirst = 100;
            this.isAlive = false;
        }
    }

    /**
     * Checks if the animal is alive.
     * 
     * @return true if the animal is alive, false otherwise.
     */
    public boolean isAlive(){
        return this.isAlive;
    }

    /**
     * Gets the thirst value of the animal.
     * 
     * @return The current thirst level.
     */
    public int getThirst() {
        return this.thirst;
    }

     /**
     * Gets the hunger value of the animal.
     * 
     * @return The current hunger level.
     */
    public int getHunger() {
        return this.hunger;
    }

    /**
     * Gets a list of products produced by the animal.
     *
     * @return A list of Products.
     * @throws CloneNotSupportedException if the cloning operation is not supported.
     */
    public ArrayList<Products> getProducts() throws CloneNotSupportedException {

        ArrayList<Products> tmp = new ArrayList<>();

        for (Item item : typeProduct) {
            if(this.isAlive && !(item.getType() == ItemType.productsType.MEAT) ){
                tmp.add((Products)item.clone());
                item.setNumber(0);
            }
            else if(!this.isAlive){
                tmp.add((Products)item.clone());
                item.setNumber(0);
            }  
        }
        return tmp;
    }

    /**
     * Checks if there are any products available from the animal.
     *
     * @return true if products are available, false otherwise.
     */
    public boolean areProductsAvailable(){
        for (int i = 0; i < this.typeProduct.size(); i++){
            if (this.typeProduct.get(i).getNumber() > 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Updates the products produced by the animal based on its current hunger and thirst.
     */
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

    /**
     * Updates the state of the animal including its hunger, thirst, and products.
     */
    public void update() {

        if(!this.isAlive){
            this.status = 0;
        }
        else{
            super.status -= 1;
            this.updateHunger();
            this.updateThirst();
        }
        this.updateProducts();
    }

    /**
     * Feeds the animal to decrease its hunger level.
     *
     * @param item The food item to feed the animal.
     * @throws NoFoodFoundException If the provided item is not food.
     * @throws MinimumHungerException If the hunger level is already at minimum.
     */
    public void feed(Item item) throws NoFoodFoundException, MinimumHungerException{
        try {
            if(item instanceof GamePlant){
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

    /**
     * Gives water to the animal to decrease its thirst level.
     *
     * @throws MaxWaterLevelReachedException If the thirst level is already at minimum.
     */
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
