/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item.animal;

import java.io.Serializable;
import java.util.ArrayList;

/******************
 * ANIMAL INTERFACE
 *****************/
public interface AnimalInterface extends Serializable {
    public int getHunger();
    public ArrayList<model.item.animal.products.Products> getProducts() throws CloneNotSupportedException; 
    public int getThirst();
    public boolean isAlive();
    public void update();
    public void feed(model.item.Item item) throws model.exceptions.CustomExceptions.NoFoodFoundException, model.exceptions.CustomExceptions.MinimumHungerException;
    public void waterAnimal() throws model.exceptions.CustomExceptions.MaxWaterLevelReachedException;
}