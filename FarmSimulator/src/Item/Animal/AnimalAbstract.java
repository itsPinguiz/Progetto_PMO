/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Animal;

import java.util.ArrayList;
import Calendar.Calendar;
import Item.ItemType;
import Item.Interface.Item;

/*****************
 * ANIMAL ABSTRACT
 ****************/
public abstract class AnimalAbstract extends Item implements AnimalInterface {
    //fields
    protected int hunger;
    protected ArrayList<ItemType.Products> typeProduct;
    protected Calendar c = Calendar.getInstance();

    public AnimalAbstract() {
        super();
    	this.typeProduct = new ArrayList<ItemType.Products>();
        this.hunger = 100;
	}
    
    //method for changing hunger
    public void setHunger(){
        //check if life == 0
        this.hunger = (int)((1/super.status)*c.getDay());
    }

    //method for changing life 
    public void setLife(){
        super.status = (1/super.status)*c.getDay();
    }

    //method for getting hunger
    public int getHunger() {
        return this.hunger;
    }

    //method for getting the products
    public void getProducts() {
        //void momentaneo
    }

}
