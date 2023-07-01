/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.animal;

import java.io.Serializable;
import java.util.ArrayList;

/******************
 * ANIMAL INTERFACE
 *****************/
/**
 * This interface represents the basic behaviors of an animal in the model. 
 * It extends Serializable, allowing any class implementing this interface 
 * to be serialized to a byte stream.
 */
public interface AnimalInterface extends Serializable {
    /**
     * Returns the current hunger level of the animal.
     *
     * @return the hunger level of the animal.
     */
    public int getHunger();
    
    /**
     * Returns a list of the products this animal can produce.
     *
     * @return a list of the products this animal can produce.
     * @throws CloneNotSupportedException if the object's class does not support the Cloneable interface.
     */
    public ArrayList<model.item.animal.products.Products> getProducts() throws CloneNotSupportedException; 

    /**
     * Returns the current thirst level of the animal.
     *
     * @return the thirst level of the animal.
     */
    public int getThirst();

    /**
     * Checks if the animal is alive.
     *
     * @return true if the animal is alive, false otherwise.
     */
    public boolean isAlive();

    /**
     * Updates the state of the animal.
     */
    public void update();

    /**
     * Feeds the animal.
     *
     * @param item the food item to feed the animal.
     * @throws model.exceptions.CustomExceptions.NoFoodFoundException if no appropriate food is found.
     * @throws model.exceptions.CustomExceptions.MinimumHungerException if the animal is not hungry enough.
     */
    public void feed(model.item.Item item) throws model.exceptions.CustomExceptions.NoFoodFoundException, model.exceptions.CustomExceptions.MinimumHungerException;
    
    /**
     * Provides water to the animal.
     *
     * @throws model.exceptions.CustomExceptions.MaxWaterLevelReachedException if the animal has reached its maximum water level.
     */
    public void waterAnimal() throws model.exceptions.CustomExceptions.MaxWaterLevelReachedException;
}