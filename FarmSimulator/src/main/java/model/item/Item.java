/********************
 * IMPORT AND PACKAGE
 *******************/

package model.item;

import java.io.Serializable;

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

    /**
     * Constructs a new Item with default values.
     * The default number is 1, and the default maxNumber is 64.
     */
    public Item(){
        this.number = 1;
        this.maxNumber = 64;
    }

    /**
     * Returns the type of the item.
     *
     * @return the type of the item
     */
    public ItemType getType(){
        //method to get the type of the item
        return this.type;
    }

    /**
     * Returns the status of the item.
     *
     * @return the status of the item
     */
    public double getStatus(){
        //method to get the status of the item
        return this.status;
    }

    /**
     * Returns the price of the item.
     *
     * @return the price of the item
     */
    public int getPrice(){
        //method to get the price of the item
        return this.price;
    }

    /**
     * Returns the number of the item.
     *
     * @return the number of the item
     */
    public int getNumber(){
        //method to get the number of the item
        return this.number;
    }
    
    /**
     * Returns the max number of the item.
     *
     * @return the max number of the item
     */
    public int getMaxNumber() {
        //method to get the max number of the item
    	return this.maxNumber;
    }
    
    /**
     * Sets the number of the item.
     *
     * @param newNumber the new number of the item
     */
    public void setNumber(int newNumber) {
        //method to set the number of the item
        if(newNumber < 0) {
            this.number = 0;
        }
        else{
            this.number = newNumber;
        }	
    }
    
    /**
     * Clones the item.
     *
     * @return a cloned object of the item
     * @throws CloneNotSupportedException if cloning is not supported
     */
    public Object clone() throws CloneNotSupportedException{
        //method to clone the item
    	return super.clone();
    }

    @Override
    public int compareTo(Item otherItem) {
        if (this.type.equals(otherItem.getType())) {
            return 0;
        } else if (this.type instanceof ItemType.Plants && otherItem.getType() instanceof ItemType.Animals) {
            return -1;
        } else if (this.type instanceof ItemType.Plants && otherItem.getType() instanceof ItemType.Tools) {
            return -1;
        } else if (this.type instanceof ItemType.Plants && otherItem.getType() instanceof ItemType.productsType) {
            return -1;
        } else if (this.type instanceof ItemType.Animals && otherItem.getType() instanceof ItemType.Tools) {
            return -1;
        } else if (this.type instanceof ItemType.Animals && otherItem.getType() instanceof ItemType.productsType) {
            return -1;
        } else if (this.type instanceof ItemType.Tools && otherItem.getType() instanceof ItemType.productsType) {
            return -1;
        } else {
            return 1;
        }
    }     
}
    

    
