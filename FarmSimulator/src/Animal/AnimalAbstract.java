package Animal;

import java.util.ArrayList;

import Animal.enu.AnimalType;
import Calendar.Calendar;
import Item.ItemType;
import Item.Interface.Item;

public abstract class AnimalAbstract implements AnimalInterface {
    
    protected int life;
    protected int hunger;
    protected AnimalType name;
    protected ArrayList<Item> typeProduct;
    protected int nProduct;

    protected Calendar c = Calendar.getInstance();

     //method for changing hunger
     public void setHunger(){
        //check if life == 0
        this.hunger = (1/this.life)*c.getDay();
    }

    //method for changing life 
    public void setLife(){
        this.life = (1/this.life)*c.getDay();
    }

    
    public int getHunger() {
        return this.hunger;
    }

    
    public int getLife() {
        return this.life;
    }

    
    public String getAnimalType() {       
        return this.typeProduct;
    }

    
    //every time you call this method the number of 
    //product are set to zero
    public int getProducts() {

        int tmp = this.nProduct;
        this.nProduct = 0;        
        return tmp;
    }

}
