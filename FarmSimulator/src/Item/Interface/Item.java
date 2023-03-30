/********************
 * IMPORT AND PACKAGE
 *******************/

package Item.Interface;

import java.io.Serializable;

import Item.ItemType;

/****************
 * ITEM INTERFACE
 ***************/
public abstract class Item implements Cloneable,Serializable,ItemInterface{
	
    protected ItemType type;
    protected double status;
    protected int price;
    protected int number;
    protected int maxNumber;

    public ItemType getType(){
        return this.type;
    }
    public double getStatus(){
        return this.status;
    }
    public int getPrice(){
        return this.price;
    }
    public int getNumber(){
        return this.number;
    }
    
    public void useItem() {
    	this.status--;
    }
    
    public int getMaxNumber() {
    	return this.maxNumber;
    }
    
    public void setNumber(int newNumber) {
    	this.number = newNumber;
    }
    
    public Object clone() throws CloneNotSupportedException{
    	return super.clone();
    }
    
}
