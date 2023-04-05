/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Interface;

import java.io.Serializable;
import Item.ItemType;

/****************
 * ITEM INTERFACE
 ***************/
public abstract class Item implements Cloneable,Serializable,ItemInterface,Comparable<Item>{
	//fields
    protected ItemType type;
    protected double status;
    protected int price;
    protected int number;
    protected int maxNumber;

    public Item(){
        this.number = 1;
        this.maxNumber = 64;
    }

    public ItemType getType(){
        //method to get the type of the item
        return this.type;
    }

    public double getStatus(){
        //method to get the status of the item
        return this.status;
    }
    public int getPrice(){
        //method to get the price of the item
        return this.price;
    }
    public int getNumber(){
        //method to get the number of the item
        return this.number;
    }
    
    public int getMaxNumber() {
        //method to get the max number of the item
    	return this.maxNumber;
    }
    
    public void setNumber(int newNumber) {
        //method to set the number of the item
    	this.number = newNumber;
    }
    
    public Object clone() throws CloneNotSupportedException{
        //method to clone the item
    	return super.clone();
    }

    @Override
    public int compareTo(Item otherItem) {
        if (this.type.equals(otherItem.getType())) {
            return 0;
        } else if (this.type instanceof ItemType.Plants) {
            return -1;
        } else if (this.type instanceof ItemType.Animals && !(otherItem.getType() instanceof ItemType.Plants)) {
            return -1;
        } else if (this.type instanceof ItemType.Tools && !(otherItem.getType() instanceof ItemType.Plants) && !(otherItem.getType() instanceof ItemType.Animals)) {
            return -1;
        } else if (this.type instanceof ItemType.productsType && !(otherItem.getType() instanceof ItemType.Plants) && !(otherItem.getType() instanceof ItemType.Animals) && !(otherItem.getType() instanceof ItemType.Tools)) {
            return -1;
        } else {
            return 1;
        }
    }
    
}
    

    
