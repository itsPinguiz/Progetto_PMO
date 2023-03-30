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
    
    protected int hunger;
    protected ArrayList<ItemType.Products> typeProduct;
    protected int nProduct;

    protected Calendar c = Calendar.getInstance();
    
    public AnimalAbstract() {
    	this.typeProduct = new ArrayList<ItemType.Products>(); 
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

    
    public int getHunger() {
        return this.hunger;
    }

    public void updateProduct() {
        if (this.nProduct == 0){
            if(this.hunger <= 50){
                this.nProduct += (int)(Math.random() * 10);
            }
            else{
                this.nProduct += (int)(Math.random() * 20) + 10;
            }
        }
    }

    // every time you call this method the number of 
    // product are set to zero
    
    public int getProducts() {

        int tmp = this.nProduct;
        this.nProduct = 0;        
        return tmp;
    }

}
